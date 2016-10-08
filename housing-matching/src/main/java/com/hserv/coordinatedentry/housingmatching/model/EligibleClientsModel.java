package com.hserv.coordinatedentry.housingmatching.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class EligibleClientsModel {

	@Valid
	public List<EligibleClientModel> eligibleClients = new ArrayList<>();

	public List<EligibleClientModel> getEligibleClients() {
		return eligibleClients;
	}

	public void setEligibleClients(List<EligibleClientModel> eligibleClients) {
		this.eligibleClients = eligibleClients;
	}
}