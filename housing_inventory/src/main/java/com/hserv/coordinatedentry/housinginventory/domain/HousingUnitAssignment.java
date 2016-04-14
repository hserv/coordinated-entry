package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A HousingUnitAssignment.
 */
@Entity
@Table(name = "housing_unit_assignment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
    private ZonedDateTime checkoutDate;

    @ManyToOne
    private HousingInventory housingInventory;

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

	public HousingInventory getHousingInventory() {
		return housingInventory;
	}

	public void setHousingInventory(HousingInventory housingInventory) {
		this.housingInventory = housingInventory;
	}

	
    }
