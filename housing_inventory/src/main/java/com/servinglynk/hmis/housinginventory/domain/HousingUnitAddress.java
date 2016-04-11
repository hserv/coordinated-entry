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
 * A HousingUnitAddress.
 */
@Entity
@Table(name = "housing_unit_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HousingUnitAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    
    @Column(name = "line1")
    private String line1;
    
    @Column(name = "line2")
    private String line2;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "zip")
    private String zip;
    
    @Column(name = "inactive")
    private Boolean inactive;
    
    @Column(name = "date_created")
    private LocalDate dateCreated;
    
    @Column(name = "date_updated")
    private LocalDate dateUpdated;
    
    @Column(name = "user_id")
    private Long userId;
    
    @OneToMany(mappedBy = "housingUnitAddress")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HousingInventory> housingInventorys = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }
    
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLine1() {
        return line1;
    }
    
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }
    
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
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

    public Set<HousingInventory> getHousingInventorys() {
        return housingInventorys;
    }

    public void setHousingInventorys(Set<HousingInventory> housingInventorys) {
        this.housingInventorys = housingInventorys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HousingUnitAddress housingUnitAddress = (HousingUnitAddress) o;
        if(housingUnitAddress.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, housingUnitAddress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HousingUnitAddress{" +
            "id=" + id +
            ", addressId='" + addressId + "'" +
            ", line1='" + line1 + "'" +
            ", line2='" + line2 + "'" +
            ", city='" + city + "'" +
            ", state='" + state + "'" +
            ", zip='" + zip + "'" +
            ", inactive='" + inactive + "'" +
            ", dateCreated='" + dateCreated + "'" +
            ", dateUpdated='" + dateUpdated + "'" +
            ", userId='" + userId + "'" +
            '}';
    }
}
