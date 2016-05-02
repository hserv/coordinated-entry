package com.hserv.coordinatedentry.housinginventory.domain;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A HousingUnitAssignment.
 */
@Entity
@Table(name = "housing_unit_assignment", schema="housing_inventory")
public class HousingUnitAssignment extends HousingInventoryBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "assignment_id")
    private UUID assignmentId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "household_id")
    private String householdId;

    @Column(name = "checkout_date")
    private LocalDateTime checkoutDate;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JsonIgnore
    private HousingInventory housingInventory;
    
    @Transient
    private String housingInventoryId;

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

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public HousingInventory getHousingInventory() {
        return housingInventory;
    }

    public void setHousingInventory(HousingInventory housingInventory) {
        this.housingInventory = housingInventory;
    }

	public String getHousingInventoryId() {
		return housingInventoryId;
	}

	public void setHousingInventoryId(String housingInventoryId) {
		this.housingInventoryId = housingInventoryId;
	}
    
    

}

