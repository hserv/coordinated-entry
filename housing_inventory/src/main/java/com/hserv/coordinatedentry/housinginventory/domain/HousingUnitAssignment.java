package com.hserv.coordinatedentry.housinginventory.domain;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A HousingUnitAssignment.
 */
@Entity
@Table(name = "housing_unit_assignment", schema="housing_inventory")
public class HousingUnitAssignment extends HousingInventoryBaseEntity  {

	private static final long serialVersionUID = -2423704859507175452L;

	@Id
	@Column(name = "assignment_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID assignmentId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "household_id")
    private String householdId;

    @Column(name = "checkout_date")
    @JsonFormat(pattern="MM-dd-yyyy")
    private LocalDateTime checkoutDate;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JsonIgnore
    private HousingInventory housingInventory;
    
    @Transient
    private String housingInventoryId;

   
    public HousingUnitAssignment() {
    	
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assignmentId == null) ? 0 : assignmentId.hashCode());
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
		HousingUnitAssignment other = (HousingUnitAssignment) obj;
		if (assignmentId == null) {
			if (other.assignmentId != null)
				return false;
		} else if (!assignmentId.equals(other.assignmentId))
			return false;
		return true;
	}

	
    
    

}

