package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsDaoV3;
import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.client.MessageSender;
import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.JSONObjectMapper;
import com.servinglynk.hmis.warehouse.core.model.Parameters;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.model.AMQEvent;

@Service
public class EligibleClientServiceImpl implements EligibleClientService {

	@Autowired
	EligibleClientsRepository eligibleClientsRepository;

	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;
	
	
	@Autowired
	ISearchServiceClient searchServiceClient;
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	@Autowired
	MessageSender messageSender;

	
/*	public List<EligibleClientModel> getEligibleClientsBack(int num , String programType) {
		List<EligibleClientModel> eligibleClientModels = new ArrayList<>();
		List<EligibleClient> eligibleClients = eligibleClientsRepository
				.findTopEligibleClients(programType ,new PageRequest(0, num, eligibleClientSortClause()));
		for (EligibleClient eligibleClient : eligibleClients) {
			eligibleClientModels.add(eligibleClientsTranslator.translate(eligibleClient));
		}

		return eligibleClientModels;
	}


	public List<EligibleClient> getEligibleClients(int num , String programType) {
		List<EligibleClient> eligibleClients = eligibleClientsRepository
				.findTopEligibleClients(programType ,new PageRequest(0, num, eligibleClientSortClause()));

		return eligibleClients;
	}*/
	
	@Override
	public EligibleClientModel getEligibleClientDetail(UUID clientID,String version) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		List<UUID> sharedClients = SecurityContextUtil.getSharedClients();
		EligibleClient eligibleClient = null;
		if(sharedClients.contains(clientID)) {
			 eligibleClient = eligibleClientsRepository.findByClientIdAndDeleted(clientID,false);			
		}else {
		 eligibleClient = eligibleClientsRepository.findByClientIdAndProjectGroupCodeAndDeleted(clientID,projectGroup,false);
		}
		if(eligibleClient==null) throw new ResourceNotFoundException("Eligible not found "+clientID);
		if(version!=null && version.equalsIgnoreCase("v2"))
			return eligibleClientsTranslator.translateV2(eligibleClient);
		EligibleClientModel eligibleClientModel=  eligibleClientsTranslator.translate(eligibleClient);
		return eligibleClientModel;
	}
	
	@Autowired EligibleClientsDaoV3 eligibleClientsDaoV3;
	
	@Override
	public Page<EligibleClient> getEligibleClients(String projectGroupCode, Pageable pageable, String filter) {
		List<EligibleClient> clients  =new ArrayList<EligibleClient>(); 
		 Long count =0L;
		 List<UUID> sharedClientsList = SecurityContextUtil.getSharedClients();
		 String sharedClients = "'"+StringUtils.join(sharedClientsList.toArray(), ',').replaceAll(",", "','")+"'";
		 if(sharedClientsList.isEmpty()) {
					if(filter.equalsIgnoreCase("inactive")) {
						clients = eligibleClientsRepository.getInactiveEligibleClients(projectGroupCode,pageable.getPageSize(),pageable.getOffset());
						count = eligibleClientsRepository.getInactiveEligibleClientsCount(projectGroupCode);
					}else if(filter.equalsIgnoreCase("active")) {
						clients = eligibleClientsRepository.getActiveEligibleClients(projectGroupCode,pageable.getPageSize(),pageable.getOffset());
						count = eligibleClientsRepository.getActiveEligibleClientsCount(projectGroupCode);
					}else {
						clients = eligibleClientsRepository.getActiveEligibleClients(projectGroupCode,pageable.getPageSize(),pageable.getOffset());
						count = eligibleClientsRepository.getAllEligibleClientsCount(projectGroupCode);
					}
		 }else {
			 
				if(filter.equalsIgnoreCase("inactive")) {
					clients = eligibleClientsDaoV3.getInactiveEligibleClientsWithSharedClients(projectGroupCode,sharedClientsList,pageable.getPageSize(),pageable.getOffset());
					count = eligibleClientsDaoV3.getInactiveEligibleClientsCountWithSharedClients(projectGroupCode,sharedClientsList);
				}else if(filter.equalsIgnoreCase("active")) {
					clients = eligibleClientsDaoV3.getActiveEligibleClientsWithSharedClients(projectGroupCode,sharedClientsList,pageable.getPageSize(),pageable.getOffset());
					count = eligibleClientsDaoV3.getActiveEligibleClientsCountWithSharedClients(projectGroupCode,sharedClientsList);
				}else {
					clients = eligibleClientsDaoV3.getActiveEligibleClientsWithSharedClients(projectGroupCode,sharedClientsList,pageable.getPageSize(),pageable.getOffset());
					count = eligibleClientsDaoV3.getAllEligibleClientsCountWithSharedClients(projectGroupCode,sharedClientsList);
				}

			 
		 }

		return new PageImpl<>(clients,pageable,count);
	}

	@Override
	public boolean updateEligibleClient(UUID clientID, EligibleClientModel eligibleClientModel) throws Exception  {
		boolean status = false;
		EligibleClient eligibleClient = eligibleClientsRepository.findOne(eligibleClientModel.getClientId());
		if(eligibleClient.getMatched() == false) {			
				eligibleClientsRepository.saveAndFlush(eligibleClientsTranslator.translate(eligibleClientModel,eligibleClient));
				status = true;
		}else{
			 if( eligibleClient.getStatus()!=null && eligibleClient.getStatus() == 10 &&  eligibleClientModel.isIgnoreMatchProcess()==false){				 
				 	eligibleClient.setMatched(false); 
				 	eligibleClient.setStatus(null);
				 	eligibleClient.setRemarks(eligibleClientModel.getRemarks());
				 	eligibleClientsRepository.saveAndFlush(eligibleClient);
/*					List<Match> matchs = repositoryFactory.getMatchReservationsRepository().findByEligibleClientAndDeletedOrderByDateCreatedDesc(eligibleClient, false);
					if(matchs.isEmpty()) return false;
						HousingInventory housingUnit = repositoryFactory.getHousingUnitsRepository().findOne(matchs.get(0).getHousingUnitId());
						housingUnit.setVacant(true);
						repositoryFactory.getHousingUnitsRepository().save(housingUnit);*/
				 	status = true;
			 } else{
				 throw new AccessDeniedException(" Matched client connot be updated");
			 }

		}

		return status;
	}

	@Override
	public boolean deleteEligibleClientById(UUID clientID) {
		boolean status = false;
		EligibleClient client =	repositoryFactory.getEligibleClientsRepository().findOne(clientID);
		if(client!=null && !client.isDeleted()){
				eligibleClientsRepository.delete(client);
			status = true;
			
			// creating active mq request
			AMQEvent amqEvent = new AMQEvent();
	
			amqEvent.setEventType("house.matching.activelist");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("clientId", client.getClientId());
			data.put("dedupClientId", client.getClientDedupId());
			data.put("deleted", true);
			data.put("projectGroupCode", client.getProjectGroupCode());
			data.put("userId", client.getUserId());
			data.put("clientId",client.getClientId());
			amqEvent.setPayload(data);
			amqEvent.setModule("ces");
			amqEvent.setSubsystem("housematching");
			messageSender.sendAmqMessage(amqEvent);
		}
		return status;
	}

	@Override
	public boolean createEligibleClient(EligibleClientModel eligibleClientModel) {
		boolean status = false;
		if(!StringUtils.isEmpty(eligibleClientModel.getClientId()+"") &&
				!eligibleClientsRepository.exists(eligibleClientModel.getClientId())){
			eligibleClientsRepository.saveAndFlush(eligibleClientsTranslator.translate(eligibleClientModel,null));
			status = true;
		}
		return status;
	}

	@Override
	public boolean deleteAll() {
		eligibleClientsRepository.deleteAll();
		return true;
	}

	@Override
	public boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels) throws Exception {
		if (eligibleClientModels != null && !eligibleClientModels.isEmpty()) {
			for (EligibleClientModel clientModel : eligibleClientModels) {
				updateEligibleClient(clientModel.getClientId(), clientModel);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels) {
		if (eligibleClientModels != null && !eligibleClientModels.isEmpty()) {
			for (EligibleClientModel clientModel : eligibleClientModels) {
				createEligibleClient(clientModel);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEligibleClientScore(UUID clientID, int scoreTotal) {
		boolean status = false;
		EligibleClient client = eligibleClientsRepository.findByClientIdAndDeleted(clientID,false);
		if(client!=null){
			client.setSurveyScore(scoreTotal);
			eligibleClientsRepository.save(client);
		}
			
		return status;	
	}
	
	private Sort eligibleClientSortClause() {
		return sortBySurveyDate().and(sortByScore());
	}

	private Sort sortByScore() {
		return new Sort(Sort.Direction.DESC, "surveyScore");
	}

	private Sort sortBySurveyDate() {
		return new Sort(Sort.Direction.ASC, "surveyDate");
	}
	
	
	public List<EligibleClient> getEligibleClients(Integer programType, String projectGroup,String spdatLabel) {
		
		Specification<EligibleClient> specification = Specifications.where(new Specification<EligibleClient>() {

			@Override
			public Predicate toPredicate(Root<EligibleClient> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
			return criteriaBuilder.and(
							criteriaBuilder.equal(root.get("programType"),programType+""),
							criteriaBuilder.equal(root.get("projectGroupCode"),projectGroup),
						criteriaBuilder.equal(root.get("spdatLabel"),spdatLabel),
						criteriaBuilder.equal(root.get("deleted"), false),
						criteriaBuilder.equal(root.get("ignoreMatchProcess"),false),
						criteriaBuilder.equal(root.get("matched"),false));
			}	
		});
		
		Sort sort = new Sort(Direction.ASC,"cocScore","surveyDate");
		return repositoryFactory.getEligibleClientsRepository().findAll(specification,sort);
	}
	
	@SuppressWarnings("unchecked")
	public BaseClient getClientInfo(UUID clientId,String trustedAppId,String sessionToken)  throws Exception {
		SearchRequest request = new SearchRequest();
		request.setTrustedAppId(trustedAppId);
		request.setSearchEntity("clients");
		request.setSessionToken(sessionToken);
		request.addSearchParam("q", clientId);
		List<BaseClient> clients=new ArrayList<BaseClient>();
		try {
			clients = (List<BaseClient>) searchServiceClient.search(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		if(!clients.isEmpty()) return clients.get(0);
		return null;
	}	
	
	public BaseClient getClientInfoByDedupId(UUID clientDedupId,String trustedAppId,String sessionToken)  throws Exception {
		SearchRequest request = new SearchRequest();
		request.setTrustedAppId(trustedAppId);
		request.setSearchEntity("clients");
		request.setSessionToken(sessionToken);
		request.addSearchParam("q", clientDedupId);
		List<BaseClient> clients=new ArrayList<BaseClient>();
		try {
			clients = (List<BaseClient>) searchServiceClient.search(request);
		} catch (Exception e) {
			throw e;
		}
		if(!clients.isEmpty()) return clients.get(0);
		return null;
	}
	
	public Parameters getClientDataElements(UUID clientId,String trustedAppId,String sessionToken) throws Exception {
		HttpHeaders headers = getHttpHeaders();
		headers.add("X-HMIS-TrustedApp-Id", trustedAppId);
		headers.add("Authorization", "HMISUserAuth session_token=" + sessionToken);

		RestTemplate restTemplate = new RestTemplate();

		HttpEntity entity = new HttpEntity(headers);

		StringBuffer URI = new StringBuffer(
				"http://hmiselb.aws.hmislynk.com/hmis-globalapi/rest/clients/" + clientId+"/dataelements");
	
		ResponseEntity<String> response = restTemplate.exchange(URI.toString(), HttpMethod.GET, entity, String.class);
		JSONObjectMapper mapper = new JSONObjectMapper();
		Parameters parameters = null;
		try{
		 parameters = mapper.readValue(response.getBody(), Parameters.class);
		}catch (Exception e) {
			e.printStackTrace();
			parameters = new Parameters();
			throw e;
		}
		return parameters;
	}
	
	protected HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		// Surya 04/17/2015 - You can add any headers here like user session, Authorization token etc
		headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return headers;
	}
}