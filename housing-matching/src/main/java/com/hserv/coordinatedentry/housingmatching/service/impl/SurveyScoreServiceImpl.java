package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.ClientSurveyScore;
import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
import com.hserv.coordinatedentry.housingmatching.translator.SurveyScoreTranslator;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Session;



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
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	
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

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public void calculateScore(Session session) throws Exception {
		
		ClientsSurveyScores surveyResponseModel = surveyMSService.fetchSurveyResponse(session);
		
		List<EligibleClient> eligibleClients = new ArrayList<EligibleClient>();
		MatchStrategy strategy;
		for(ClientSurveyScore clientSurveyScore : surveyResponseModel.getClientsSurveyScores()){
			EligibleClient eligibleClient = eligibleClientsRepository.findOne(clientSurveyScore.getClientId());
			if(eligibleClient==null){
				eligibleClient = new EligibleClient();
				eligibleClient.setMatched(false);
			}
			eligibleClient.setClientId(clientSurveyScore.getClientId());
			BaseClient client = eligibleClientService.getClientInfo(clientSurveyScore.getClientId(), "MASTER_TRUSTED_APP", session.getToken());
			strategy = communityServiceLocator.locate(CommunityType.MONTEREY);
			int additionalScore = strategy.getAdditionalScore(19,clientSurveyScore.getSurveyTagvalue());
			eligibleClient.setClientId(clientSurveyScore.getClientId());
			//  Get survey tag value : SINGLE_AUDULT pass individual true
			//                         FAMILY pass family true
			eligibleClient.setProgramType(strategy.getProgramType(clientSurveyScore.getSurveyScore().intValue(),clientSurveyScore.getSurveyTagvalue()));
			eligibleClient.setSurveyDate(clientSurveyScore.getSurveyDate());
			eligibleClient.setSpdatLabel(clientSurveyScore.getSurveyTagvalue());
			eligibleClient.setSurveyScore(clientSurveyScore.getSurveyScore().intValue());
			eligibleClient.setCocScore(clientSurveyScore.getSurveyScore().intValue()+additionalScore);
			eligibleClient.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
			if(client!=null)
				eligibleClient.setClientLink(client.getLink());		
			if(!eligibleClient.getMatched())
				eligibleClientsRepository.save(eligibleClient);
			
		    List<Match> matches = repositoryFactory.getMatchReservationsRepository().findByEligibleClient(eligibleClient);
			if(matches.isEmpty()){
				Match  match = new Match();
				match.setEligibleClient(eligibleClient);
				match.setManualMatch(false);
		//		match.setMatchDate(new Date());
				match.setMatchStatus(0);
				match.setProgramType(session.getAccount().getProjectGroup().getProjectGroupCode());
	//			repositoryFactory.getMatchReservationsRepository().save(match);
			}
			//eligibleClients.add(eligibleClient);
			
		}

	}
}
