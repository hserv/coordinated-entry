package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

public interface EligibleClientService {

	List<EligibleClientModel> getEligibleClients(int num);

	EligibleClientModel getEligibleClientDetail(UUID clientID);

	List<EligibleClientModel> getEligibleClients();

	boolean updateEligibleClient(UUID clientID , EligibleClientModel eligibleClientModel);

	boolean deleteEligibleClientById(UUID clientID);
	
	boolean createEligibleClient(EligibleClientModel eligibleClientModel);
	
	boolean deleteAll();
	
	boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels);
	
	boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels);

	boolean updateEligibleClientScore(UUID clientID, int scoreTotal);
}
