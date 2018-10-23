package com.hserv.coordinatedentry.housingmatching.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EligibleClientAuditsModel {

	@Valid
	@JsonProperty("eligibleClientHistory")
	public List<EligibleClientAuditModel> eligibleClientAudits = new ArrayList<>();

	public List<EligibleClientAuditModel> getEligibleClientAudits() {
		return eligibleClientAudits;
	}

	public void setEligibleClientAudits(List<EligibleClientAuditModel> eligibleClientAudits) {
		this.eligibleClientAudits = eligibleClientAudits;
	}
	
	public void addEligibleClientAudit(EligibleClientAuditModel eligibleClientAuditModel) {
		this.eligibleClientAudits.add(eligibleClientAuditModel);
	}
}