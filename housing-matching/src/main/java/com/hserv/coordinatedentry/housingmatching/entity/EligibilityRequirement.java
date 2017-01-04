package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="housing_unit_eligibility",schema="housing_inventory")
public class EligibilityRequirement extends BaseEntity {

	@Id
	@Column(name = "eligibility_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID eligibilityId;
		
	@Column(name="project_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID projectId;
	
	@Column(name="eligibility")
	private String eligibility;
	
	@Column(name="inactive")
	private Boolean inactive;	
	

	
	@Column(name="deleted")
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	
	
	public UUID getEligibilityId() {
		return eligibilityId;
	}
	public void setEligibilityId(UUID eligibilityId) {
		this.eligibilityId = eligibilityId;
	}
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	public String getEligibility() {
		return eligibility;
	}
	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}
	public Boolean getInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}
}