package com.hserv.coordinatedentry.housinginventory.domain;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="housing_inventory",schema="housing_inventory")
public class HousingInventory extends HousingInventoryBaseEntity  {
	
	private static final long serialVersionUID = -5909795577794623031L;
	@Id
	@Column(name = "housing_unit_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
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
	
	@Column(name="alias_name")
	private String aliasName;
	
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

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((housingInventoryId == null) ? 0 : housingInventoryId.hashCode());
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
		if (housingInventoryId == null) {
			if (other.housingInventoryId != null)
				return false;
		} else if (!housingInventoryId.equals(other.housingInventoryId))
			return false;
		return true;
	}

	
	
	

}