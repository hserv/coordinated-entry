package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.helper.ProcessAlreadyRunningException;
import com.hserv.coordinatedentry.housingmatching.model.ClientSurveyScore;
import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.service.BatchProcessService;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
import com.hserv.coordinatedentry.housingmatching.translator.SurveyScoreTranslator;
import com.hserv.coordinatedentry.housingmatching.util.Constants;
import com.hserv.coordinatedentry.housingmatching.util.DateUtil;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.client.MessageSender;
import com.servinglynk.hmis.warehouse.core.model.Account;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.ProjectGroup;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.model.AMQEvent;



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
	
	@Autowired
	BatchProcessService batchProcessService;
	
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	Environment env;
	
	
	@Override
	public Page<EligibleClient> getScores(Pageable pageable) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		return eligibleClientsRepository.findByProjectGroupCodeAndDeletedOrderBySurveyDateDesc(projectGroup,false,pageable);
	}

	@Override
	public boolean deleteScores() {
		eligibleClientsRepository.deleteScores();
		return true;
	}

	@Override
	public int getScoreByClientId(UUID clientId) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
			EligibleClient eligibleClients = eligibleClientsRepository.findByClientIdAndProjectGroupCodeAndDeleted(clientId,projectGroup,false);
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
	
	
	public void checkAnyProcessRunning(String projectGroupCode){
		
		List<BatchProcessEntity> entities = repositoryFactory.getBatchProcessRepository().findByProjectGroupCodeAndStatus(projectGroupCode, Constants.BATCH_STATUS_INPROGRESS);
			if(!entities.isEmpty())
				throw new ProcessAlreadyRunningException();
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	@Async
	public void calculateScore(Session session,UUID processId) {
		try{
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
		
		ClientsSurveyScores surveyResponseModel = surveyMSService.fetchSurveyResponses(session.getAccount().getProjectGroup().getProjectGroupCode(),null);
		
		List<EligibleClient> eligibleClients = new ArrayList<EligibleClient>();
		MatchStrategy strategy;
		for(ClientSurveyScore clientSurveyScore : surveyResponseModel.getClientsSurveyScores()){
				this.processClientsScore(clientSurveyScore,session);
		}
		batchProcessService.endBatch(processId, true);	
		}catch (Exception e) {
				e.printStackTrace();
				batchProcessService.endBatch(processId, false);			
		}

	}
	
	@SuppressWarnings("unused")
	@Transactional
	@Async
	public void calculateScoreOld(Session session,UUID processId) {
		try{
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
		
		ClientsSurveyScores surveyResponseModel = surveyMSService.fetchSurveyResponses(session.getAccount().getProjectGroup().getProjectGroupCode(),null);
		
		List<EligibleClient> eligibleClients = new ArrayList<EligibleClient>();
		MatchStrategy strategy;
		for(ClientSurveyScore clientSurveyScore : surveyResponseModel.getClientsSurveyScores()){
			EligibleClient eligibleClient = eligibleClientsRepository.findOne(clientSurveyScore.getClientId());
			if(eligibleClient==null || ( eligibleClient!=null &&  eligibleClient.getMatched() == false )){
					if(eligibleClient==null){
							eligibleClient = new EligibleClient();
							eligibleClient.setMatched(false);
							eligibleClient.setProjectGroupCode(clientSurveyScore.getProjectGroupCode());
							eligibleClient.setRemarks("Ignore match flag auto set by system to false");
							eligibleClient.setBonusScore(0);
					}
						eligibleClient.setSurveyDate(clientSurveyScore.getSurveyDate());
					
						LocalDateTime responseSubmissionDate = surveyMSService.getSurveyDate(clientSurveyScore.getClientId(),clientSurveyScore.getSurveyId());
						if(responseSubmissionDate==null) responseSubmissionDate = surveyMSService.getSurveyScoreDate(clientSurveyScore.getClientId(),clientSurveyScore.getSurveyId());
						if(responseSubmissionDate!=null)eligibleClient.setSurveySubmissionDate(responseSubmissionDate);
						
						eligibleClient.setClientId(clientSurveyScore.getClientId());
						BaseClient client = eligibleClientService.getClientInfo(clientSurveyScore.getClientId(), "MASTER_TRUSTED_APP", session.getToken());
						strategy = communityServiceLocator.locate(CommunityType.MONTEREY);
						int additionalScore =0;
						if(client!=null && client.getDob()!=null){
								additionalScore = strategy.getAdditionalScore(DateUtil.calculateAge(client.getDob()),clientSurveyScore.getSurveyTagValue());
								eligibleClient.setClientDedupId(client.getDedupClientId());
						}
							
						eligibleClient.setClientId(clientSurveyScore.getClientId());
						//  Get survey tag value : SINGLE_AUDULT pass individual true
						//                         FAMILY pass family true
						eligibleClient.setCocScore(clientSurveyScore.getSurveyScore().intValue()+additionalScore);
						eligibleClient.setProgramType(strategy.getProgramType(clientSurveyScore.getSurveyScore().intValue(),clientSurveyScore.getSurveyTagValue()));
						eligibleClient.setSpdatLabel(clientSurveyScore.getSurveyTagValue());
						eligibleClient.setSurveyScore(clientSurveyScore.getSurveyScore().intValue());
						Long surveyScore = clientSurveyScore.getSurveyScore();
						int finalSurveyScore = 0;
						Integer bonusScore = eligibleClient.getBonusScore();
						if(surveyScore != null) {
							finalSurveyScore=surveyScore.intValue();
						}
						if(bonusScore != null) {
							finalSurveyScore = finalSurveyScore + bonusScore.intValue();
						}
						
						eligibleClient.setTotalScore(finalSurveyScore);

						if(client!=null)
							eligibleClient.setClientLink(client.getLink());		
			
						eligibleClientsRepository.save(eligibleClient);	
						

						// creating active mq request
						AMQEvent amqEvent = new AMQEvent();
				
						amqEvent.setEventType("eligibleClients");
						Map<String, Object> data = new HashMap<String, Object>();
						data.put("clientId", client.getClientId());
						data.put("dedupClientId", eligibleClient.getClientDedupId());
						data.put("deleted", false);
						data.put("projectGroupCode", eligibleClient.getProjectGroupCode());
						data.put("userId", eligibleClient.getUserId());
						data.put("clientId",eligibleClient.getClientId());
						amqEvent.setPayload(data);
						amqEvent.setModule("ces");
						amqEvent.setSubsystem("housematching");
						messageSender.sendAmqMessage(amqEvent);
		
					    List<Match> matches = repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeletedOrderByDateCreatedDesc(eligibleClient,false);
						if(matches.isEmpty()){
							Match  match = new Match();
							match.setEligibleClient(eligibleClient);
							match.setManualMatch(false);
					//		match.setMatchDate(new Date());
							match.setMatchStatus(0);
							match.setProgramType(session.getAccount().getProjectGroup().getProjectGroupCode());
				//			repositoryFactory.getMatchReservationsRepository().save(match);

			}
			}
			//eligibleClients.add(eligibleClient);
		
		}
		batchProcessService.endBatch(processId, true);	
		}catch (Exception e) {
				e.printStackTrace();
				batchProcessService.endBatch(processId, false);			
		}

	}

	
	public void processClientsScore(ClientSurveyScore clientSurveyScore,Session session) throws Exception {
		System.out.println(clientSurveyScore.getClientId()+" processing started");
		MatchStrategy strategy;
		EligibleClient eligibleClient = eligibleClientsRepository.findOne(clientSurveyScore.getClientId());
		if(eligibleClient==null || ( eligibleClient!=null &&  eligibleClient.getMatched() == false )){
				if(eligibleClient==null){
					System.out.println("Eligible client not found. Creating new eligible client");
						eligibleClient = new EligibleClient();
						eligibleClient.setMatched(false);
						eligibleClient.setProjectGroupCode(clientSurveyScore.getProjectGroupCode());
						eligibleClient.setRemarks("Ignore match flag auto set by system to false");
						eligibleClient.setBonusScore(0);
				}
					eligibleClient.setSurveyDate(clientSurveyScore.getSurveyDate());
				
					LocalDateTime responseSubmissionDate = surveyMSService.getSurveyDate(clientSurveyScore.getClientId(),clientSurveyScore.getSurveyId());
					if(responseSubmissionDate==null) responseSubmissionDate = surveyMSService.getSurveyScoreDate(clientSurveyScore.getClientId(),clientSurveyScore.getSurveyId());
					if(responseSubmissionDate!=null)eligibleClient.setSurveySubmissionDate(responseSubmissionDate);
					
					eligibleClient.setClientId(clientSurveyScore.getClientId());
					BaseClient client = eligibleClientService.getClientInfo(clientSurveyScore.getClientId(), "MASTER_TRUSTED_APP", session.getToken());
					strategy = communityServiceLocator.locate(CommunityType.MONTEREY);
					int additionalScore =0;
						if(client!=null) System.out.println("client search complated "+client.getDedupClientId());
					if(client!=null && client.getDob()!=null){
							additionalScore = strategy.getAdditionalScore(DateUtil.calculateAge(client.getDob()),clientSurveyScore.getSurveyTagValue());
							eligibleClient.setClientDedupId(client.getDedupClientId());
					}
						
					eligibleClient.setClientId(clientSurveyScore.getClientId());
					//  Get survey tag value : SINGLE_AUDULT pass individual true
					//                         FAMILY pass family true
					eligibleClient.setCocScore(clientSurveyScore.getSurveyScore().intValue()+additionalScore);
					eligibleClient.setProgramType(strategy.getProgramType(clientSurveyScore.getSurveyScore().intValue(),clientSurveyScore.getSurveyTagValue()));
					eligibleClient.setSpdatLabel(clientSurveyScore.getSurveyTagValue());
					eligibleClient.setSurveyScore(clientSurveyScore.getSurveyScore().intValue());
					Long surveyScore = clientSurveyScore.getSurveyScore();
					int finalSurveyScore = 0;
					Integer bonusScore = eligibleClient.getBonusScore();
					if(surveyScore != null) {
						finalSurveyScore=surveyScore.intValue();
					}
					if(bonusScore != null) {
						finalSurveyScore = finalSurveyScore + bonusScore.intValue();
					}
					
					eligibleClient.setTotalScore(finalSurveyScore);

					if(client!=null)
						eligibleClient.setClientLink(client.getLink());		
		
					eligibleClientsRepository.save(eligibleClient);	
					System.out.println("Eligible client creating process completed");

					// creating active mq request
					AMQEvent amqEvent = new AMQEvent();
			
					amqEvent.setEventType("eligibleClients");
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("clientId", eligibleClient.getClientId());
					data.put("dedupClientId", eligibleClient.getClientDedupId());
					data.put("deleted", false);
					data.put("projectGroupCode", eligibleClient.getProjectGroupCode());
					data.put("userId", eligibleClient.getUserId());
					data.put("clientId",eligibleClient.getClientId());
					amqEvent.setPayload(data);
					amqEvent.setModule("ces");
					amqEvent.setSubsystem("housematching");
					messageSender.sendAmqMessage(amqEvent);
	
				    List<Match> matches = repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeletedOrderByDateCreatedDesc(eligibleClient,false);
					if(matches.isEmpty()){
						Match  match = new Match();
						match.setEligibleClient(eligibleClient);
						match.setManualMatch(false);
				//		match.setMatchDate(new Date());
						match.setMatchStatus(0);
						match.setProgramType(session.getAccount().getProjectGroup().getProjectGroupCode());
			//			repositoryFactory.getMatchReservationsRepository().save(match);

		}
		}
		//eligibleClients.add(eligibleClient);
	
	}

	@Transactional
    public void processClientScore(AMQEvent amqEvent) throws Exception {
		
		System.out.append("Processing client Id"+amqEvent.toJSONString());
    	
    	Map<String, Object> payload = amqEvent.getPayload();
		
    	Account account = new Account();
    	ProjectGroup projectGroup = new ProjectGroup();
    	projectGroup.setProjectGroupCode(payload.get("projectGroupCode").toString());
    	account.setAccountId(UUID.fromString(payload.get("userId").toString()));
    	account.setProjectGroup(projectGroup);
    	Session session = new Session();
    	session.setToken(payload.get("token").toString());
    	session.setAccount(account);
    	
    	UUID clientId =UUID.fromString(payload.get("clientId").toString());
    	
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
		
		ClientsSurveyScores surveyResponseModel = surveyMSService.fetchSurveyResponses(session.getAccount().getProjectGroup().getProjectGroupCode(),clientId);
		ClientSurveyScore score =	surveyResponseModel.getClientsSurveyScores().get(0);
		this.processClientsScore(score, session);
    }
}
