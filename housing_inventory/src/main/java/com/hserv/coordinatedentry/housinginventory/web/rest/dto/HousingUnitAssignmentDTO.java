package com.hserv.coordinatedentry.housinginventory.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;


/**
 * A DTO for the HousingUnitAssignment entity.
 */
public class HousingUnitAssignmentDTO implements Serializable {

    

    private UUID assignmentId;


    private String clientId;


    private String householdId;


    private ZonedDateTime checkoutDate;


    private Long housingInventoryId;
    
    
    
    
    public UUID getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(UUID assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(String householdId) {
        this.householdId = householdId;
    }
    public ZonedDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(ZonedDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Long getHousingInventoryId() {
        return housingInventoryId;
    }

    public void setHousingInventoryId(Long housingInventoryId) {
        this.housingInventoryId = housingInventoryId;
    }
   
}
