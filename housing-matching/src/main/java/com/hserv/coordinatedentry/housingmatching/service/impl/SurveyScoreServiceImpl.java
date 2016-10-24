package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
import com.hserv.coordinatedentry.housingmatching.util.DateUtil;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
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
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		return eligibleClientsRepository.findByProjectGroupCode(projectGroup,pageable);
	}

	@Override
	public boolean deleteScores() {
		eligibleClientsRepository.deleteScores();
		return true;
	}

	@Override
	public int getScoreByClientId(UUID clientId) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
			EligibleClient eligibleClients = eligibleClientsRepository.findByClientIdAndProjectGroupCode(clientId,projectGroup);
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
			EligibleClient client =	eligibleClientsRepository.findOne(clientId);
			if(client==null) throw new ResourceNotFoundException("client not found "+clientId);
			client.setSurveyScore(score);
			eligibleClientsRepository.save(client);
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
			int additionalScore =0;
			if(client!=null && client.getDob()!=null){
					additionalScore = strategy.getAdditionalScore(DateUtil.calculateAge(client.getDob()),clientSurveyScore.getSurveyTagvalue());
			}
		 
			eligibleClient.setClientId(clientSurveyScore.getClientId());
			//  Get survey tag value : SINGLE_AUDULT pass individual true
			//                         FAMILY pass family true
			eligibleClient.setProgramType(strategy.getProgramType(clientSurveyScore.getSurveyScore().intValue(),clientSurveyScore.getSurveyTagvalue()));
			eligibleClient.setSurveyDate(clientSurveyScore.getSurveyDate());
			eligibleClient.setSpdatLabel(clientSurveyScore.getSurveyTagvalue());
			eligibleClient.setSurveyScore(clientSurveyScore.getSurveyScore().intValue());
			eligibleClient.setCocScore(clientSurveyScore.getSurveyScore().intValue()+additionalScore);
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
