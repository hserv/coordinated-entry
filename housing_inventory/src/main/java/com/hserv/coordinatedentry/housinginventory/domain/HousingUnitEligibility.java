package com.hserv.coordinatedentry.housinginventory.domain;

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
public class HousingUnitEligibility extends HousingInventoryBaseEntity {

	@Id
	@Column(name = "eligibility_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID eligibilityId;
	
	@Column(name="housing_unit_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID housingUnitId;
	
	@Column(name="project_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID projectId;
	
	@Column(name="eligibility")
	private String eligibility;
	
	
	public UUID getEligibilityId() {
		return eligibilityId;
	}
	public void setEligibilityId(UUID eligibilityId) {
		this.eligibilityId = eligibilityId;
	}
	public UUID getHousingUnitId() {
		return housingUnitId;
	}
	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
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
}