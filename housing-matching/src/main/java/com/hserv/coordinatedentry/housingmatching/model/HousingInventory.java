package com.hserv.coordinatedentry.housingmatching.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HousingInventory {

	@JsonProperty(value="housing_unit_id")
	private String housingUnitId;
	
	@JsonProperty(value="project_uuid")
	private String projectUuid;
	
	@JsonProperty(value="address")
	private Address address;
	
	@JsonProperty(value="beds_current")
	private String bedsCurrent;
	
	@JsonProperty(value="beds_capacity")
	private String bedsCapacity;
	
	@JsonProperty(value="in_service")
	private boolean inService;
	
	@JsonProperty(value="family_unit")
	private boolean familyUnit;
	
	@JsonProperty(value="vacant")
	private boolean vacant;
	
	@JsonProperty(value="inactive")
	private boolean inactive;
	
	public String getHousingUnitId() {
		return housingUnitId;
	}

	public void setHousingUnitId(String housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getBedsCurrent() {
		return bedsCurrent;
	}

	public void setBedsCurrent(String bedsCurrent) {
		this.bedsCurrent = bedsCurrent;
	}

	public String getBedsCapacity() {
		return bedsCapacity;
	}

	public void setBedsCapacity(String bedsCapacity) {
		this.bedsCapacity = bedsCapacity;
	}

	public boolean isInService() {
		return inService;
	}

	public void setInService(boolean inService) {
		this.inService = inService;
	}

	public boolean isFamilyUnit() {
		return familyUnit;
	}

	public void setFamilyUnit(boolean familyUnit) {
		this.familyUnit = familyUnit;
	}

	public boolean isVacant() {
		return vacant;
	}

	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	@Override
	public String toString() {
		return "HousingInventory [housingUnitId=" + housingUnitId + ", projectUuid=" + projectUuid + ", address="
				+ address + ", bedsCurrent=" + bedsCurrent + ", bedsCapacity=" + bedsCapacity + ", inService="
				+ inService + ", familyUnit=" + familyUnit + ", vacant=" + vacant + ", inactive=" + inactive + "]";
	}

	
	
	
	
}

