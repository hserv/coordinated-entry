package com.hserv.coordinatedentry.housinginventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;

/**
 * A HousingInventory.
 */
@Entity
@Table(name = "housing_inventory")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HousingInventory extends HousingInventoryBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "housing_unit_id")
    private UUID housingUnitId;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "beds_current")
    private Integer bedsCurrent;

    @Column(name = "beds_capacity")
    private Integer bedsCapacity;

    @Column(name = "family_unit")
    private Boolean familyUnit;

    @OneToMany(mappedBy = "housingInventory")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HousingUnitAddress> housingUnitAddresss = new HashSet<>();

    @OneToMany(mappedBy = "housingInventory")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HousingUnitAssignment> housingUnitAssignments = new HashSet<>();

    @OneToMany(mappedBy = "housingInventory")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MatchReservations> matchReservationss = new HashSet<>();

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Set<HousingUnitAddress> getHousingUnitAddresss() {
		return housingUnitAddresss;
	}

	public void setHousingUnitAddresss(Set<HousingUnitAddress> housingUnitAddresss) {
		this.housingUnitAddresss = housingUnitAddresss;
	}

	public Set<HousingUnitAssignment> getHousingUnitAssignments() {
		return housingUnitAssignments;
	}

	public void setHousingUnitAssignments(Set<HousingUnitAssignment> housingUnitAssignments) {
		this.housingUnitAssignments = housingUnitAssignments;
	}

	public Set<MatchReservations> getMatchReservationss() {
		return matchReservationss;
	}

	public void setMatchReservationss(Set<MatchReservations> matchReservationss) {
		this.matchReservationss = matchReservationss;
	}
     
}
