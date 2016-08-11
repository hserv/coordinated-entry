package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
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
	public int getScoreByClientId(UUID clientId) {
			EligibleClient eligibleClients = eligibleClientsRepository.findByClientId(clientId);
			if(eligibleClients!=null)
				return eligibleClients.getSurveyScore();
		     return 0;
	}

	@Override
	public boolean deleteScoreByClientId(UUID clientId) {
			eligibleClientsRepository.deleteScoreByClientId(clientId);
			return true;
	}

	@Override
	public boolean updateScoreByClientId(int score, UUID clientId) {
			eligibleClientsRepository.updateScoreByClientId(score, clientId);
			return true;
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
		List<EligibleClient> eligibleClients = new ArrayList<EligibleClient>();
		MatchStrategy strategy;
		for(SurveySectionModel m : surveyResponseModel.getSurveySectionList()){
			EligibleClient eligibleClient = new EligibleClient();
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
}
