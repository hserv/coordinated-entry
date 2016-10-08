package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;
import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

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

	
	public List<EligibleClientModel> getEligibleClientsBack(int num , String programType) {
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
	}
	
	@Override
	public EligibleClientModel getEligibleClientDetail(UUID clientID) {
		EligibleClient eligibleClient = eligibleClientsRepository.findByClientId(clientID);
		if(eligibleClient==null) throw new ResourceNotFoundException("Eligible not found "+clientID);
		EligibleClientModel eligibleClientModel=  eligibleClientsTranslator.translate(eligibleClient);
		return eligibleClientModel;
	}
	
	@Override
	public Page<EligibleClient> getEligibleClients(String projectGroupCode, Pageable pageable) {
		Page<EligibleClient> clients = eligibleClientsRepository.findByProjectGroupCode(projectGroupCode , pageable);
		return clients;
	}

	@Override
	public boolean updateEligibleClient(UUID clientID, EligibleClientModel eligibleClientModel) {
		boolean status = false;
		EligibleClient eligibleClient = eligibleClientsRepository.findOne(eligibleClientModel.getClientId());
	
			eligibleClientsRepository.saveAndFlush(eligibleClientsTranslator.translate(eligibleClientModel,eligibleClient));
			status = true;
		return status;
	}

	@Override
	public boolean deleteEligibleClientById(UUID clientID) {
		boolean status = false;
		if(!StringUtils.isEmpty(clientID)&& eligibleClientsRepository.exists(clientID)){
			eligibleClientsRepository.deleteByClientId(clientID);
			status = true;
		}
		return status;
	}

	@Override
	public boolean createEligibleClient(EligibleClientModel eligibleClientModel) {
		boolean status = false;
		if(!StringUtils.isEmpty(eligibleClientModel.getClientId()) &&
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
	public boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels) {
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
		if(!StringUtils.isEmpty(clientID)&&
				eligibleClientsRepository.exists(clientID)){
			eligibleClientsRepository.updateScoreByClientId(scoreTotal, clientID);
			status = true;
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
						criteriaBuilder.equal(root.get("matched"),false));
			}	
		});
		
		Sort sort = new Sort(Direction.ASC,"cocScore","surveyDate");
		return repositoryFactory.getEligibleClientsRepository().findAll(specification,sort);
	}
	
	@SuppressWarnings("unchecked")
	public BaseClient getClientInfo(UUID clientId,String trustedAppId,String sessionToken)  {
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
		}
		if(!clients.isEmpty()) return clients.get(0);
		return null;
	}	
}