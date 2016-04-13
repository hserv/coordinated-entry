package com.servinglynk.hmis.housinginventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A HousingInventory.
 */
@Entity
@Table(name = "housing_inventory")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HousingInventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "housing_unit_id", nullable = false)
    private Long housingUnitId;
    
    @Column(name = "beds_current")
    private Long bedsCurrent;
    
    @Column(name = "beds_capacity")
    private Long bedsCapacity;
    
    @Column(name = "family_unit")
    private Boolean familyUnit;
    
    @Column(name = "in_service")
    private Boolean inService;
    
    @Column(name = "vacant")
    private Boolean vacant;
    
    @Column(name = "inactive")
    private Boolean inactive;
    
    @Column(name = "date_created")
    private LocalDate dateCreated;
    
    @Column(name = "date_updated")
    private LocalDate dateUpdated;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "project_id")
    private Long projectId;
    
    @Column(name = "address_id")
    private Long addressId;
    
    @OneToMany(mappedBy = "housingInventory")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MatchReservations> matchReservationss = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "housing_unit_address_id")
    private HousingUnitAddress housingUnitAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHousingUnitId() {
        return housingUnitId;
    }
    
    public void setHousingUnitId(Long housingUnitId) {
        this.housingUnitId = housingUnitId;
    }

    public Long getBedsCurrent() {
        return bedsCurrent;
    }
    
    public void setBedsCurrent(Long bedsCurrent) {
        this.bedsCurrent = bedsCurrent;
    }

    public Long getBedsCapacity() {
        return bedsCapacity;
    }
    
    public void setBedsCapacity(Long bedsCapacity) {
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

    public Boolean getInactive() {
        return inactive;
    }
    
    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }
    
    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }
    
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getAddressId() {
        return addressId;
    }
    
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Set<MatchReservations> getMatchReservationss() {
        return matchReservationss;
    }

    public void setMatchReservationss(Set<MatchReservations> matchReservationss) {
        this.matchReservationss = matchReservationss;
    }

    public HousingUnitAddress getHousingUnitAddress() {
        return housingUnitAddress;
    }

    public void setHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
        this.housingUnitAddress = housingUnitAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HousingInventory housingInventory = (HousingInventory) o;
        if(housingInventory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, housingInventory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HousingInventory{" +
            "id=" + id +
            ", housingUnitId='" + housingUnitId + "'" +
            ", bedsCurrent='" + bedsCurrent + "'" +
            ", bedsCapacity='" + bedsCapacity + "'" +
            ", familyUnit='" + familyUnit + "'" +
            ", inService='" + inService + "'" +
            ", vacant='" + vacant + "'" +
            ", inactive='" + inactive + "'" +
            ", dateCreated='" + dateCreated + "'" +
            ", dateUpdated='" + dateUpdated + "'" +
            ", userId='" + userId + "'" +
            ", projectId='" + projectId + "'" +
            ", addressId='" + addressId + "'" +
            '}';
    }
}
