package com.hserv.coordinatedentry.housingmatching.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HousingInventory 
 */
@Entity
@Table(name = "housing_inventory", schema = "housing")
public class HousingInventory implements java.io.Serializable {

	private UUID housingUnitId;
	private HousingUnitAddress housingUnitAddress;
	private String projectId;
	private Integer bedsCurrent;
	private Integer bedsCapacity;
	private Boolean familyUnit;
	private Boolean inService;
	private Boolean vacant;
	private Boolean inactive;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;
	private Set<MatchReservations> matchReservationses = new HashSet(0);

	public HousingInventory() {
	}

	public HousingInventory(UUID housingUnitId, HousingUnitAddress housingUnitAddress) {
		this.housingUnitId = housingUnitId;
		this.housingUnitAddress = housingUnitAddress;
	}

	public HousingInventory(UUID housingUnitId, HousingUnitAddress housingUnitAddress, String projectId,
			Integer bedsCurrent, Integer bedsCapacity, Boolean familyUnit, Boolean inService, Boolean vacant,
			Boolean inactive, Date dateCreated, Date dateUpdated, String userId, 
			Set<MatchReservations> matchReservationses) {
		this.housingUnitId = housingUnitId;
		this.housingUnitAddress = housingUnitAddress;
		this.projectId = projectId;
		this.bedsCurrent = bedsCurrent;
		this.bedsCapacity = bedsCapacity;
		this.familyUnit = familyUnit;
		this.inService = inService;
		this.vacant = vacant;
		this.inactive = inactive;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.userId = userId;
		this.matchReservationses = matchReservationses;
	}

	@Id
	@Column(name = "housing_unit_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getHousingUnitId() {
		return this.housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	public HousingUnitAddress getHousingUnitAddress() {
		return this.housingUnitAddress;
	}

	public void setHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
		this.housingUnitAddress = housingUnitAddress;
	}

	@Column(name = "project_id")
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "beds_current")
	public Integer getBedsCurrent() {
		return this.bedsCurrent;
	}

	public void setBedsCurrent(Integer bedsCurrent) {
		this.bedsCurrent = bedsCurrent;
	}

	@Column(name = "beds_capacity")
	public Integer getBedsCapacity() {
		return this.bedsCapacity;
	}

	public void setBedsCapacity(Integer bedsCapacity) {
		this.bedsCapacity = bedsCapacity;
	}

	@Column(name = "family_unit")
	public Boolean getFamilyUnit() {
		return this.familyUnit;
	}

	public void setFamilyUnit(Boolean familyUnit) {
		this.familyUnit = familyUnit;
	}

	@Column(name = "in_service")
	public Boolean getInService() {
		return this.inService;
	}

	public void setInService(Boolean inService) {
		this.inService = inService;
	}

	@Column(name = "vacant")
	public Boolean getVacant() {
		return this.vacant;
	}

	public void setVacant(Boolean vacant) {
		this.vacant = vacant;
	}

	@Column(name = "inactive")
	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created", length = 13)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_updated", length = 13)
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "housingInventory")
	public Set<MatchReservations> getMatchReservationses() {
		return this.matchReservationses;
	}

	public void setMatchReservationses(Set<MatchReservations> matchReservationses) {
		this.matchReservationses = matchReservationses;
	}

	@Override
	public String toString() {
		return "HousingInventory [housingUnitId=" + housingUnitId + ", housingUnitAddress=" + housingUnitAddress
				+ ", projectId=" + projectId + ", bedsCurrent=" + bedsCurrent + ", bedsCapacity=" + bedsCapacity
				+ ", familyUnit=" + familyUnit + ", inService=" + inService + ", vacant=" + vacant + ", inactive="
				+ inactive + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", userId=" + userId
				+ ", matchReservationses=" + matchReservationses + "]";
	}

	
	
}
