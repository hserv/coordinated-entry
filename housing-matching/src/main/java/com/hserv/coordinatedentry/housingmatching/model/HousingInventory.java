package com.hserv.coordinatedentry.housingmatching.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HousingInventory {
	@JsonProperty("housingInventoryId")
	private UUID housingUnitId;
	
	private String projectId;
	
	private Address address;
	
	private Integer bedsCurrent;
	
	private Integer bedsCapacity;
	
	private boolean inService;
	
	private boolean familyUnit;
	
	private boolean vacant;
	
	private boolean inactive;
	
	public UUID getHousingUnitId() {
		return housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getBedsCurrent() {
		return bedsCurrent;
	}

	public void setBedsCurrent(Integer bedsCurrent) {
		this.bedsCurrent = bedsCurrent;
	}

	public Integer getBedsCapacity() {
		return bedsCapacity;
	}

	public void setBedsCapacity(Integer bedsCapacity) {
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
		return "HousingInventory [housingUnitId=" + housingUnitId + ", projectId=" + projectId + ", address="
				+ address + ", bedsCurrent=" + bedsCurrent + ", bedsCapacity=" + bedsCapacity + ", inService="
				+ inService + ", familyUnit=" + familyUnit + ", vacant=" + vacant + ", inactive=" + inactive + "]";
	}
	
}

