package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

public interface EligibleClientService {

	List<EligibleClientModel> getEligibleClients(int num);

	EligibleClientModel getEligibleClientDetail(String clientID);

	List<EligibleClientModel> getEligibleClients();

	boolean updateEligibleClient(String clientID , EligibleClientModel eligibleClientModel);

	boolean deleteEligibleClientById(String clientID);
	
	boolean createEligibleClient(EligibleClientModel eligibleClientModel);
	
	boolean deleteAll();
	
	boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels);
	
	boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels);
}
