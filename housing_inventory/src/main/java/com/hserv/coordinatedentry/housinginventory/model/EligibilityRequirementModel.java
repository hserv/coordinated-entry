package com.hserv.coordinatedentry.housinginventory.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EligibilityRequirementModel {
	
	private UUID eligibilityRequirementId;
	private UUID projectId;
	
	List<Requirement> requirements = new ArrayList<Requirement>();
	
	public UUID getEligibilityRequirementId() {
		return eligibilityRequirementId;
	}
	public void setEligibilityRequirementId(UUID eligibilityRequirementId) {
		this.eligibilityRequirementId = eligibilityRequirementId;
	}
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	public List<Requirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public void addRequirement(String name,Object value){
		Requirement req = new Requirement();
			req.setName(name);
			req.setValue(value);
		this.requirements.add(req);
	}
}