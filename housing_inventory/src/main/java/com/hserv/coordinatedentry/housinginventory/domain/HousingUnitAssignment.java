package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A HousingUnitAssignment.
 */
@Entity
@Table(name = "housing_unit_assignment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HousingUnitAssignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "assignment_id")
    private String assignmentId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "household_id")
    private String householdId;

    @Column(name = "checkout_date")
    private ZonedDateTime checkoutDate;

    @ManyToOne
    private HousingInventory housingInventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HousingUnitAssignment housingUnitAssignment = (HousingUnitAssignment) o;
        if(housingUnitAssignment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, housingUnitAssignment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HousingUnitAssignment{" +
            "id=" + id +
            ", assignmentId='" + assignmentId + "'" +
            ", clientId='" + clientId + "'" +
            ", householdId='" + householdId + "'" +
            ", checkoutDate='" + checkoutDate + "'" +
            '}';
    }
}
