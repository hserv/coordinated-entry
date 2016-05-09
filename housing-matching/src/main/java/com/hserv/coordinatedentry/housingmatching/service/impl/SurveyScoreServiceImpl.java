package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
import com.hserv.coordinatedentry.housingmatching.task.ComputeScoreTask;
import com.hserv.coordinatedentry.housingmatching.translator.SurveyScoreTranslator;



@Service
public class SurveyScoreServiceImpl implements SurveyScoreService {

	@Autowired
	SurveyScoreTranslator surveyScoreTranslator;

	@Autowired
	EligibleClientsRepository eligibleClientsRepository;
	
	@Autowired
	CommunityServiceLocator communityServiceLocator;
	
	@Autowired
	SurveyMSService surveyMSService;
	
	@Autowired
	EligibleClientService eligibleClientService;
	
	
	@Override
	public List<EligibleClientModel> getScores() {
		return surveyScoreTranslator.translate(eligibleClientsRepository.findAll());
	}

	@Override
	public boolean deleteScores() {
		eligibleClientsRepository.deleteScores();
		return true;
	}

	@Override
	public int getScoreByClientId(String clientId) {
		if (clientId != null && !clientId.isEmpty() && eligibleClientsRepository.exists(UUID.fromString(clientId))) {
			EligibleClients eligibleClients = eligibleClientsRepository.findByClientId(UUID.fromString(clientId));
			return eligibleClients.getSurveyScore();
		}
		return 0;
	}

	@Override
	public boolean deleteScoreByClientId(String clientId) {
		if (clientId != null && !clientId.isEmpty() && eligibleClientsRepository.exists(UUID.fromString(clientId))) {
			eligibleClientsRepository.deleteScoreByClientId(UUID.fromString(clientId));
			return true;
		}
		return false;
	}

	@Override
	public boolean updateScoreByClientId(int score, String clientId) {
		if (clientId != null && !clientId.isEmpty() && eligibleClientsRepository.exists(UUID.fromString(clientId))) {
			eligibleClientsRepository.updateScoreByClientId(score, UUID.fromString(clientId));
			return true;
		}
		return false;
	}

	@Override
	public void calculateScore() {
		
		SurveyResponseModel surveyResponseModel = surveyMSService.fetchSurveyResponse();
		
		//Iterate and total the sectional scores into a map
		Map<String,Integer> scoreMap = new HashMap<String,Integer>();
		for(SurveySectionModel m : surveyResponseModel.getSurveySectionList()){
			String clientId = m.getClientId();
			Integer sectionalScore = m.getSectionScore();
			if(scoreMap.get(clientId)!=null){
				Integer updatedScore = scoreMap.get(clientId)+sectionalScore;
				scoreMap.put(clientId, updatedScore);
			}else{
				scoreMap.put(clientId, sectionalScore);
			}
		}
		
		//Iterate and create eligible client entities
		List<EligibleClients> eligibleClients = new ArrayList<EligibleClients>();
		MatchStrategy strategy;
		for(SurveySectionModel m : surveyResponseModel.getSurveySectionList()){
			EligibleClients eligibleClient = new EligibleClients();
			int spdatScore = scoreMap.get(m.getClientId());
			//hardcoding community for now;should be fetched from user/client
			strategy = communityServiceLocator.locate(CommunityType.MONTEREY);
			//hardcoding params for now;should be fetched from client info service
			int additionalScore = strategy.getAdditionalScore(true, false, 19, false, true, false);
			eligibleClient.setClientId(UUID.fromString(m.getClientId()));
			eligibleClient.setMatched(false);
			eligibleClient.setProgramType(strategy.getProgramType(spdatScore, true, false));
			eligibleClient.setSpdatLabel(m.getSpdatLabel());
			eligibleClient.setSurveyScore(spdatScore+additionalScore);
			eligibleClients.add(eligibleClient);
		}
		
	}
	
	public void updateClientScore(String clientId, DeferredResult<String> deferredResult) {
		Timer timer = new Timer();
		ComputeScoreTask task = new ComputeScoreTask(clientId, deferredResult, surveyMSService ,eligibleClientService);
		timer.schedule(task, 0);
	}

}
