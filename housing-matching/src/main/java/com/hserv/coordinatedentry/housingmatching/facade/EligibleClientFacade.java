package com.hserv.coordinatedentry.housingmatching.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;

@Component
public class EligibleClientFacade {

	@Autowired
	EligibleClientService eligibleClientService;

	public EligibleClientModel getEligibleClientDetail(String clientID) {
		EligibleClientModel eligibleClientModel = eligibleClientService.getEligibleClientDetail(clientID);
		return eligibleClientModel;
	}

	public List<EligibleClientModel> getEligibleClients() {
		return eligibleClientService.getEligibleClients();
	}

	public boolean updateEligibleClient(String clientID ,EligibleClientModel eligibleClientModel) {
		return eligibleClientService.updateEligibleClient(clientID, eligibleClientModel);
	}

	public boolean deleteEligibleClientById(String clientID) {
		return eligibleClientService.deleteEligibleClientById(clientID);
	}

	public boolean createEligibleClient(EligibleClientModel eligibleClientModel) {
		return eligibleClientService.createEligibleClient(eligibleClientModel);
	}
	
	public boolean deleteAll() {
		return eligibleClientService.deleteAll();
	}
	
	public boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels) {
		return eligibleClientService.updateEligibleClients(eligibleClientModels);
	}
	
	public boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels) {
		return eligibleClientService.createEligibleClients(eligibleClientModels);
	}

}
