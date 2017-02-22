package com.hserv.coordinatedentry.housingmatching.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Parameters;

public interface EligibleClientService {

//	List<EligibleClient> getEligibleClients( Pageable pageable);

	EligibleClientModel getEligibleClientDetail(UUID clientID);

	Page<EligibleClient> getEligibleClients(String projectGroupCode ,Pageable pageable);

	boolean updateEligibleClient(UUID clientID , EligibleClientModel eligibleClientModel) throws Exception ;

	boolean deleteEligibleClientById(UUID clientID);
	
	boolean createEligibleClient(EligibleClientModel eligibleClientModel);
	
	boolean deleteAll();
	
	boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels) throws Exception;
	
	boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels);

	boolean updateEligibleClientScore(UUID clientID, int scoreTotal);
	
	List<EligibleClient> getEligibleClients(Integer programType ,String projectGroup,String spdatLabel);
	
	BaseClient getClientInfo(UUID clientId,String trustedAppId,String sessionToken);
	
	Parameters getClientDataElements(UUID clientId,String trustedAppId,String sessionToken);
}
