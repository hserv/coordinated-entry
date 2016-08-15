package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.ClientSurveyScore;
import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
import com.hserv.coordinatedentry.housingmatching.translator.SurveyScoreTranslator;
import com.servinglynk.hmis.warehouse.client.model.Session;



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
	public Page<EligibleClient> getScores(Pageable pageable) {
		return eligibleClientsRepository.findAll(pageable);
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
	@Transactional
	public void calculateScore(Session session) throws Exception {
		
		ClientsSurveyScores surveyResponseModel = surveyMSService.fetchSurveyResponse(session);
		
		List<EligibleClient> eligibleClients = new ArrayList<EligibleClient>();
		MatchStrategy strategy;
		for(ClientSurveyScore clientSurveyScore : surveyResponseModel.getClientsSurveyScores()){
			EligibleClient eligibleClient = new EligibleClient();
			eligibleClient.setClientId(clientSurveyScore.getClientId());
			strategy = communityServiceLocator.locate(CommunityType.MONTEREY);
			int additionalScore = strategy.getAdditionalScore(true, false, 19, false, true, false);
			eligibleClient.setClientId(clientSurveyScore.getClientId());
			eligibleClient.setMatched(false);
			eligibleClient.setProgramType(strategy.getProgramType(clientSurveyScore.getSurveyScore().intValue(), true, false));
			
			// Hard coded value 
			eligibleClient.setSpdatLabel("YOUTH");
			eligibleClient.setSurveyScore(clientSurveyScore.getSurveyScore().intValue()+additionalScore);
			eligibleClientsRepository.save(eligibleClient);
			//eligibleClients.add(eligibleClient);
			
		}

	}
}
