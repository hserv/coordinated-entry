package com.hserv.coordinatedentry.housinginventory.domain;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="housing_inventory",schema="housing_inventory")
public class HousingInventory extends HousingInventoryBaseEntity  {
	
	private static final long serialVersionUID = -5909795577794623031L;
	@Id
	@Column(name = "housing_unit_id")
	private UUID housingInventoryId;
	
	@Column(name = "beds_current")
	private Integer bedsCurrent;
	
	@Column(name = "project_id")
	private String projectId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "beds_capacity")
	private Integer bedsCapacity;
	
	@Column(name = "family_unit")
	private Boolean familyUnit;
	
	@Column(name = "in_service")
	private Boolean inService;
	
	@Column(name = "vacant")
	private Boolean vacant;
	
	@OneToMany(mappedBy = "housingInventory",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<HousingUnitAddress> housingUnitAddresss = new HashSet<HousingUnitAddress>();
	
	@OneToMany(mappedBy = "housingInventory", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<HousingUnitAssignment> housingUnitAssignments = new HashSet<HousingUnitAssignment>();

	@Transient
	private HousingUnitAddress housingUnitAddress; //housingUnitAddress
	
	
	public HousingInventory(){
		
	}
	
	public HousingInventory(UUID housingInventoryId, Integer bedsCurrent, String projectId, String userId,
			Integer bedsCapacity, Boolean familyUnit, Boolean inService, Boolean vacant,LocalDateTime dateCreated,
			LocalDateTime dateUpdated, Boolean inactive,HousingUnitAddress housingUnitAddress) {
		super(dateCreated, dateUpdated,inactive);
		this.housingInventoryId = housingInventoryId;
		this.bedsCurrent = bedsCurrent;
		this.projectId = projectId;
		this.userId = userId;
		this.bedsCapacity = bedsCapacity;
		this.familyUnit = familyUnit;
		this.inService = inService;
		this.vacant = vacant;
		this.housingUnitAddress=housingUnitAddress;
	}

	public Set<HousingUnitAddress> getHousingUnitAddresss() {
		return housingUnitAddresss;
	}

	public void setHousingUnitAddresss(Set<HousingUnitAddress> housingUnitAddresss) {
		this.housingUnitAddresss = housingUnitAddresss;
	}

	public UUID getHousingInventoryId() {
		return housingInventoryId;
	}

	public void setHousingInventoryId(UUID housingInventoryId) {
		this.housingInventoryId = housingInventoryId;
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

	public Boolean getFamilyUnit() {
		return familyUnit;
	}

	public void setFamilyUnit(Boolean familyUnit) {
		this.familyUnit = familyUnit;
	}

	public Boolean getInService() {
		return inService;
	}

	public void setInService(Boolean inService) {
		this.inService = inService;
	}

	public Boolean getVacant() {
		return vacant;
	}

	public void setVacant(Boolean vacant) {
		this.vacant = vacant;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public HousingUnitAddress getHousingUnitAddress() {
		return housingUnitAddress;
	}

	public void setHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
		this.housingUnitAddress = housingUnitAddress;
	}

	public Set<HousingUnitAssignment> getHousingUnitAssignments() {
		return housingUnitAssignments;
	}

	public void setHousingUnitAssignments(Set<HousingUnitAssignment> housingUnitAssignments) {
		this.housingUnitAssignments = housingUnitAssignments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bedsCapacity == null) ? 0 : bedsCapacity.hashCode());
		result = prime * result + ((bedsCurrent == null) ? 0 : bedsCurrent.hashCode());
		result = prime * result + ((familyUnit == null) ? 0 : familyUnit.hashCode());
		result = prime * result + ((housingInventoryId == null) ? 0 : housingInventoryId.hashCode());
		result = prime * result + ((housingUnitAddress == null) ? 0 : housingUnitAddress.hashCode());
		result = prime * result + ((housingUnitAddresss == null) ? 0 : housingUnitAddresss.hashCode());
		result = prime * result + ((housingUnitAssignments == null) ? 0 : housingUnitAssignments.hashCode());
		result = prime * result + ((inService == null) ? 0 : inService.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((vacant == null) ? 0 : vacant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HousingInventory other = (HousingInventory) obj;
		if (bedsCapacity == null) {
			if (other.bedsCapacity != null)
				return false;
		} else if (!bedsCapacity.equals(other.bedsCapacity))
			return false;
		if (bedsCurrent == null) {
			if (other.bedsCurrent != null)
				return false;
		} else if (!bedsCurrent.equals(other.bedsCurrent))
			return false;
		if (familyUnit == null) {
			if (other.familyUnit != null)
				return false;
		} else if (!familyUnit.equals(other.familyUnit))
			return false;
		if (housingInventoryId == null) {
			if (other.housingInventoryId != null)
				return false;
		} else if (!housingInventoryId.equals(other.housingInventoryId))
			return false;
		if (housingUnitAddress == null) {
			if (other.housingUnitAddress != null)
				return false;
		} else if (!housingUnitAddress.equals(other.housingUnitAddress))
			return false;
		if (housingUnitAddresss == null) {
			if (other.housingUnitAddresss != null)
				return false;
		} else if (!housingUnitAddresss.equals(other.housingUnitAddresss))
			return false;
		if (housingUnitAssignments == null) {
			if (other.housingUnitAssignments != null)
				return false;
		} else if (!housingUnitAssignments.equals(other.housingUnitAssignments))
			return false;
		if (inService == null) {
			if (other.inService != null)
				return false;
		} else if (!inService.equals(other.inService))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (vacant == null) {
			if (other.vacant != null)
				return false;
		} else if (!vacant.equals(other.vacant))
			return false;
		return true;
	}
	
	

}