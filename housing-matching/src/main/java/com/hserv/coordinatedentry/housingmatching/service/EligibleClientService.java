package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

public interface EligibleClientService {

	List<EligibleClient> getEligibleClients(int num , String programType);

	EligibleClientModel getEligibleClientDetail(UUID clientID);

	Page<EligibleClient> getEligibleClients(Pageable pageable);

	boolean updateEligibleClient(UUID clientID , EligibleClientModel eligibleClientModel);

	boolean deleteEligibleClientById(UUID clientID);
	
	boolean createEligibleClient(EligibleClientModel eligibleClientModel);
	
	boolean deleteAll();
	
	boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels);
	
	boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels);

	boolean updateEligibleClientScore(UUID clientID, int scoreTotal);
	
	List<EligibleClient> getEligibleClients(String projectGroup,String spdatLabel);
	
	BaseClient getClientInfo(UUID clientId,String trustedAppId,String sessionToken) throws Exception;
}
