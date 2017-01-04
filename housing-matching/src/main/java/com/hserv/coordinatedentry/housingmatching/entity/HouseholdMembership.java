package com.hserv.coordinatedentry.housingmatching.entity;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "household_membership",schema="housing_inventory")
public class HouseholdMembership extends BaseEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="household_membership_id")
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID householdMembershipId;

    @Column(name = "global_client_id")
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID globalClientId;

    @Column(name = "relationship_to_head_of_household")
    private String relationshipToHeadOfHousehold;

    @ManyToOne
    @JoinColumn(name="global_household_id")
    private GlobalHousehold globalHousehold;


    @Column(name="deleted")
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	

    public UUID getHouseholdMembershipId() {
		return householdMembershipId;
	}

	public void setHouseholdMembershipId(UUID householdMembershipId) {
		this.householdMembershipId = householdMembershipId;
	}

	public UUID getGlobalClientId() {
        return globalClientId;
    }

    public void setGlobalClientId(UUID globalClientId) {
        this.globalClientId = globalClientId;
    }

    public String getRelationshipToHeadOfHousehold() {
        return relationshipToHeadOfHousehold;
    }

    public void setRelationshipToHeadOfHousehold(String relationshipToHeadOfHousehold) {
        this.relationshipToHeadOfHousehold = relationshipToHeadOfHousehold;
    }

    public GlobalHousehold getGlobalHousehold() {
        return globalHousehold;
    }

    public void setGlobalHousehold(GlobalHousehold globalHousehold) {
        this.globalHousehold = globalHousehold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseholdMembership householdMembership = (HouseholdMembership) o;
        if(householdMembership.householdMembershipId == null || householdMembershipId == null) {
            return false;
        }
        return Objects.equals(householdMembershipId, householdMembership.householdMembershipId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(householdMembershipId);
    }

    @Override
    public String toString() {
        return "HouseholdMembership{" +
            "householdMembershipId=" + householdMembershipId +
            ", globalClientId='" + globalClientId + "'" +
            ", relationshipToHeadOfHousehold='" + relationshipToHeadOfHousehold + "'" +
            '}';
    }
}
