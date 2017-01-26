package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.entity.MatchStatus;
import com.hserv.coordinatedentry.housingmatching.entity.MatchStatusLevels;
import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.external.NotificationService;
import com.hserv.coordinatedentry.housingmatching.external.ProjectService;
import com.hserv.coordinatedentry.housingmatching.helper.InvalidMatchStatus;
import com.hserv.coordinatedentry.housingmatching.model.ClientDEModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchStatusModel;
import com.hserv.coordinatedentry.housingmatching.service.BatchProcessService;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.service.ServiceFactory;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;
import com.hserv.coordinatedentry.housingmatching.util.MatchProcessLogger;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.Account;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.BaseProject;
import com.servinglynk.hmis.warehouse.core.model.Parameter;
import com.servinglynk.hmis.warehouse.core.model.Parameters;
import com.servinglynk.hmis.warehouse.core.model.Recipients;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Service
public class MatchReservationsServiceImpl implements MatchReservationsService {
	
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	@Autowired
	ServiceFactory serviceFactory;

	@Autowired
	MatchReservationTranslator matchReservationTranslator;

	@Autowired
	NotificationService notificationService;
	
	@Autowired
	EligibleClientService eligibleClientService;
	
	@Autowired
	HousingUnitService housingUnitService;
		
	@Autowired
	CommunityServiceLocator communityServiceLocator;
	
	@Autowired
	EligibilityValidator eligibilityValidator;
	
	
	@Autowired
	ISearchServiceClient searchServiceClient;
	
	@Autowired
	BatchProcessService batchProcessService;
	
	@Autowired
	MatchProcessLogger logger;
		
	@Autowired
	Environment env;
	
	Map<Integer, Integer[]> statusMap =new HashMap<Integer,Integer[]>();
			
	@Override
	public boolean createMatchReservation(MatchReservationModel matchReservationModel) {
	    repositoryFactory.getMatchReservationsRepository().saveAndFlush(matchReservationTranslator.translate(matchReservationModel));
		return true;
	}

	@Override
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels) {
		Set<Match> matchReservations = matchReservationTranslator
				.translates(new HashSet<MatchReservationModel>(matchReservationModels));
		for (Match matchReservation : matchReservations) {
			repositoryFactory.getMatchReservationsRepository().saveAndFlush(matchReservation);
		}
		return true;
	}

	@Override
	public boolean deleteAll() {
		repositoryFactory.getMatchReservationsRepository().deleteAll();
		return true;
	}

	@Override
	public Page<Match> findAll(String q,Pageable pageable,String projectGroupCode) {
		String[] status ={};
		if(q!=null)
			status=	q.split(",");
		
		if(status.length>0){
			return repositoryFactory.getMatchReservationsRepository().findByProgramTypeAndMatchStatusInAndDeleted(projectGroupCode,status,false,pageable);
		}else{
		return repositoryFactory.getMatchReservationsRepository().findByProgramTypeAndDeleted(projectGroupCode,false,pageable);
		}
	}

	@Override
	public boolean deleteByClientId(UUID clientId) {
		EligibleClient client = repositoryFactory.getEligibleClientsRepository().findByClientIdAndDeleted(clientId, false);
		if(client!=null)
		{
				List<Match> matches = repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeleted(client, false);
				repositoryFactory.getMatchReservationsRepository().delete(matches);
			return true;
		}
		return false;
	}

	@Override
	public MatchReservationModel findByClientId(UUID clientId) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		EligibleClient eligibleClient = repositoryFactory.getEligibleClientsRepository().findByClientIdAndProjectGroupCodeAndDeleted(clientId, projectGroup,false);	
		if (eligibleClient==null) throw new ResourceNotFoundException("Client not found "+clientId);
		List<Match> matchs = repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeleted(eligibleClient,false);
		if(matchs.isEmpty()) throw new ResourceNotFoundException("macth reservation not found for the client "+clientId);
		return matchReservationTranslator.translate(matchs.get(0));
	
	}

	@Override
	public boolean updateByClientId(UUID clientId, MatchReservationModel matchReservationModel) {
	
			EligibleClient eligibleClient = repositoryFactory.getEligibleClientsRepository().findOne(clientId);
			if(eligibleClient==null)
				return false;
			
		Match matchReservations = matchReservationTranslator.translate(matchReservationModel);
			matchReservations.setEligibleClient(eligibleClient);
		repositoryFactory.getMatchReservationsRepository().saveAndFlush(matchReservations);
		return true;
	}
	
	@Override
	public void updateMatchStatus(UUID clientId, MatchStatusModel statusModel,String auditUser,Session session,String trustedApp) throws Exception {
		EligibleClient client = new EligibleClient();
		client.setClientId(clientId);
		List<Match> matches = (List<Match>) repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeleted(client,false);
		if(matches.isEmpty()) throw new ResourceNotFoundException("No match reservation found for this client");
		Match match = matches.get(0);
		List<MatchStatusLevels> nextLevels = repositoryFactory.getMatchStatuLevelsRepository().findByProjectGroupCodeAndStatusCode(match.getProgramType(), match.getMatchStatus()+"");
		
		
		List newStatus = new ArrayList();
				for(MatchStatusLevels level : nextLevels){
					newStatus.addAll(Arrays.asList(level.getNextStatusCode().split(",")));
				}
		if(!newStatus.contains(statusModel.getStatus()+"")){
			throw new InvalidMatchStatus("Invalid status. Current status "+match.getMatchStatus());
		}
		
		
		MatchStatus matchStatus = new MatchStatus();
		BeanUtils.copyProperties(statusModel, matchStatus,"recipients");
		if(!statusModel.getRecipients().getToRecipients().isEmpty())
			matchStatus.setRecipients(statusModel.getRecipients().toJSONString());
		matchStatus.setClientId(clientId);
		repositoryFactory.getMatchStatusRepository().save(matchStatus);
		match.setMatchStatus(statusModel.getStatus());
		repositoryFactory.getMatchReservationsRepository().save(match);
		Account loggedinUser = SecurityContextUtil.getUserAccount();
		statusModel.getRecipients().getCcRecipients().add(loggedinUser.getEmailAddress());
//		if(!statusModel.getRecipients().getToRecipients().isEmpty())
			notificationService.notifyStatusUpdate(match, statusModel.getRecipients(),session,trustedApp);
	}
	
	public List<MatchStatusModel> getMatchStatusHistory(UUID clientId,String projectGroupCode) throws Exception {
		List<MatchStatusModel> history = new ArrayList<MatchStatusModel>();
		List<MatchStatus> statusHistory = repositoryFactory.getMatchStatusRepository().findByClientIdOrderByDateCreatedDesc(clientId);
		for(MatchStatus matchStatus : statusHistory){
			MatchStatusModel matchStatusModel = new MatchStatusModel();
			BeanUtils.copyProperties(matchStatus, matchStatusModel,"recipients");
			List<MatchStatusLevels> matchStatusLevels = repositoryFactory.getMatchStatuLevelsRepository().findByProjectGroupCodeAndStatusCode(projectGroupCode,(matchStatus.getStatus()+""));
			if(!matchStatusLevels.isEmpty())
			   matchStatusModel.setStatusDescription(matchStatusLevels.get(0).getStatusDescription());
			//matchStatusModel.setStatusCode(MatchStatusUpdateEnum.lookupEnum(matchStatus.getStatus()+"").getValue());
		//	if(matchStatus.getRecipients()!=null)
		//			matchStatusModel.setRecipients(objectMapper.readValue(matchStatus.getRecipients(), Recipients.class));
			history.add(matchStatusModel);
			
		}
		return history;
	}
	
	public MatchStatusModel getMatchStatusById(UUID id) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		MatchStatusModel matchStatusModel = new MatchStatusModel();
		MatchStatus matchStatus =	repositoryFactory.getMatchStatusRepository().findOne(id);
		BeanUtils.copyProperties(matchStatus, matchStatusModel,"recipients");
	/*	if(matchStatus.getRecipients()!=null)
				matchStatusModel.setRecipients(objectMapper.readValue(matchStatus.getRecipients(), Recipients.class));*/
		return matchStatusModel;
	}
	
	@Autowired
	ProjectService projectService;
	
	
	@Async
	public void matchingProcess(Integer maxClients,Session session , String trustedAppId,UUID processId) {
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
		logger.setProcessId(processId);
		
		String projectGroup = session.getAccount().getProjectGroup().getProjectGroupCode();
		List<HousingInventory> housingInventories = repositoryFactory.getHousingUnitsRepository().findByProjectGroupCodeAndVacantAndDeleted(projectGroup,true,false);
//		match.process.housingInventories= {"step":"FETCH vacant housing units.","statusMessage":"SUCCESS - {} housing unit(s) loaded"}
		logger.log("match.process.housingInventories",new Object[]{housingInventories.size()},housingInventories.size()>0 ? true :false,null,null,null);
		
		Integer matchCount =0;
		int housingUnitIndex=1;
		for(HousingInventory housingInventory : housingInventories ){
			logger.log("macth.process.selectedHousingUnit", new Object[]{housingUnitIndex,housingInventory.getHousingInventoryId(),housingInventory.getFamilyUnit() == true ? "FAMILY" : "SINGLE_ADULT"}, true, housingInventory.getHousingInventoryId(), null, null);
		
			BaseProject project = projectService.getProjectInfo(housingInventory.getProjectId(), session, trustedAppId);

			logger.log("match.process.project.loaded",new Object[]{project!=null? "SUCCESS":"FAILED",housingInventory.getHousingInventoryId(),project!=null? project.getProjectType(): "Project not found in HMIS"}, project!=null ? true : false,housingInventory.getHousingInventoryId(),null,null);
			if(project!=null) {
					List<EligibleClient> clients= new ArrayList<>();
						if(housingInventory.getFamilyUnit()){
							  clients = eligibleClientService.getEligibleClients(project.getProjectType(),projectGroup, "FAMILY");
						}else{
							 clients = eligibleClientService.getEligibleClients(project.getProjectType(),projectGroup, "SINGLE_ADULT");					
						}
						
						logger.log("match.process.clients.loaded",new Object[]{clients.size()>0? "SUCCESS" : "FAILED",clients.size(),project.getProjectType(),housingInventory.getFamilyUnit() == true ? "FAMILY" : "SINGLE_ADULT"},clients.size()>0 ? true : false,housingInventory.getHousingInventoryId(),project.getProjectId(),null);
						int clientIndex = 1;
						for(EligibleClient client : clients) {
							logger.log("match.process.selectedClient", new Object[]{clientIndex,client.getClientId()}, true, housingInventory.getHousingInventoryId(), project.getProjectId(), client.getClientId());
							boolean validClient= false;
							BaseClient baseClient = eligibleClientService.getClientInfo(client.getClientId(), trustedAppId, session.getToken());							
							if(baseClient!=null){
									Parameters clientDEs = eligibleClientService.getClientDataElements(baseClient.getClientId(), trustedAppId, session.getToken());
									ClientDEModel model = new ClientDEModel();
										model.setEligibleClientService(eligibleClientService);
										model.populateValues(baseClient,clientDEs);
										model.populateValues(client,clientDEs);
										model.populateValues(repositoryFactory.getHouseholdMembershipRepository().findByGlobalClientIdAndDeleted(baseClient.getClientId(), false),trustedAppId,session.getToken(),clientDEs);
										logger.log("match.process.clientInfo.loaded",new Object[]{client.getClientId(),baseClient.getFirstName(),baseClient.getLastName(),model.getAge(),client.getCocScore(),client.getSurveyScore()},true,housingInventory.getHousingInventoryId(),project.getProjectId(),baseClient.getClientId());
										Integer bedsRequired = eligibilityValidator.validateBedsAvailability(baseClient.getClientId(), housingInventory.getBedsCurrent(),housingInventory.getHousingInventoryId(),project.getProjectId());
										Map<String, Object> clientDataElements = map(clientDEs.getParameters());
										if(clientDataElements.get("disabilityRequired".toLowerCase())==null) clientDataElements.put("disabilityRequired".toLowerCase(), false);
										if(clientDataElements.get("mentalHealthProblem".toLowerCase())==null) clientDataElements.put("mentalHealthProblem".toLowerCase(), false);
										if(bedsRequired!=0) validClient = eligibilityValidator.validateProjectEligibility(clientDataElements,baseClient.getClientId() ,project.getProjectId(),housingInventory.getHousingInventoryId());						
										if(validClient){
											this.matchClient(client, housingInventory,projectGroup,processId,trustedAppId);
											logger.log("match.process.matchsuccess", new Object[]{housingInventory.getHousingInventoryId(),client.getClientId()}, true, housingInventory.getHousingInventoryId(),project.getProjectId(),model.getClientId());
											matchCount++;
												break;
										}
							}
							clientIndex++;
						}
						if(matchCount==maxClients) break;
			}
			housingUnitIndex++;
		}
		batchProcessService.endBatch(processId);
	}
	
	public Map<String, Object> map(List<Parameter> parameters) {
		Map<String, Object> map = new HashMap<String,Object>();
		for (Parameter parameter : parameters) {
			Object value = parameter.getValue();		
/*			if(value){
				map.put(parameter.getKey(), (Boolean)value);		
			}else{
*/				map.put(parameter.getKey().toLowerCase(), value);
//			}
		
		}
		return map;
	}

	
	public void matchClient(EligibleClient client,HousingInventory housingInventory,String projectGroupCode, UUID processId,String trustedAppId){
		List<Match> matches =	repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeleted(client,false);
		Match match =null;
		if(matches.isEmpty()) {
			 match = new Match();
			 match.setEligibleClient(client);
		}else{
			match = matches.get(0);
		}
	
		match.setHousingUnitId(housingInventory.getHousingInventoryId());
		match.setManualMatch(false);
		match.setMatchDate(new Date());
		match.setMatchStatus(0);
		match.setProcessId(processId);
		match.setProgramType(projectGroupCode);
		repositoryFactory.getMatchReservationsRepository().save(match);
		client.setMatched(true);
		repositoryFactory.getEligibleClientsRepository().save(client);
		housingInventory.setVacant(false);
//		housingInventory.setBedsCurrent(housingInventory.getBedsCurrent()-1);							
		repositoryFactory.getHousingUnitsRepository().save(housingInventory);
		Recipients recipients = new Recipients();
		String defaultEmails = env.getProperty("defaultMatchEmail-"+projectGroupCode);
		recipients.getCcRecipients().addAll(Arrays.asList(defaultEmails.split(";")));
		SecurityContext context =  SecurityContextHolder.getContext();
		Authentication authentication =  context.getAuthentication();
	//	notificationService.notifyStatusUpdate(match, recipients,(Session) authentication.getPrincipal(),trustedAppId);
	}
}