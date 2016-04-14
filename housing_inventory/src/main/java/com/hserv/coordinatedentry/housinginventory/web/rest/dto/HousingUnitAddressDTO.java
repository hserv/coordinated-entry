package com.hserv.coordinatedentry.housinginventory.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;


/**
 * A DTO for the HousingUnitAddress entity.
 */
public class HousingUnitAddressDTO implements Serializable {


    private UUID addressId;


    private String addressline1;


    private String addressline2;


    private String city;


    private String state;


    private String zip;

    

    private Long housingInventoryId;
    
    
    
   

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

    public Long getHousingInventoryId() {
        return housingInventoryId;
    }

    public void setHousingInventoryId(Long housingInventoryId) {
        this.housingInventoryId = housingInventoryId;
    }
   
}
