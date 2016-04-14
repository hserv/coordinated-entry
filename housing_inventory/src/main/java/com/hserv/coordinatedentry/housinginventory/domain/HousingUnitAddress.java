package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A HousingUnitAddress.
 */
@Entity
@Table(name = "housing_unit_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HousingUnitAddress extends HousingInventoryBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "addressline_1")
    private String addressline1;

    @Column(name = "addressline_2")
    private String addressline2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @ManyToOne
    private HousingInventory housingInventory;

	public UUID getAddressId() {
		return addressId;
	}

	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
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

	public HousingInventory getHousingInventory() {
		return housingInventory;
	}

	public void setHousingInventory(HousingInventory housingInventory) {
		this.housingInventory = housingInventory;
	}
    
    

      

   
}