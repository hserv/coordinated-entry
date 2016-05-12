package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.UUID;

public class HousingInventoryModel extends HousingMatchBaseModel {

	private String housingUnitId;
	
	private UUID projectUuid;
	
	private Address address;
	
	private String bedsCurrent;
	
	private String bedsCapacity;
	
	private boolean inService;
	
	private boolean familyUnit;
	
	private boolean vacant;
	
	private boolean inactive;

	public String getHousingUnitId() {
		return housingUnitId;
	}

	public void setHousingUnitId(String housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	public UUID getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(UUID projectUuid) {
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
		return "HousingInventoryModel [housingUnitId=" + housingUnitId + ", projectUuid=" + projectUuid + ", address="
				+ address + ", bedsCurrent=" + bedsCurrent + ", bedsCapacity=" + bedsCapacity + ", inService="
				+ inService + ", familyUnit=" + familyUnit + ", vacant=" + vacant + ", inactive=" + inactive
				+ ", getDateCreated()=" + getDateCreated() + ", getDateUpdated()=" + getDateUpdated() + ", getUserId()="
				+ getUserId() + "]";
	}
	
	
	
}
