package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "global_household",schema="housing_inventory")
public class GlobalHousehold extends BaseEntity {

	@Id
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	@Column(name="global_household_id")
    private UUID globalHouseholdId;

    @Column(name = "head_of_household_id")
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID headOfHouseholdId;

    @OneToMany(mappedBy = "globalHousehold",cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<HouseholdMembership> members = new HashSet<>();

    @Column(name="deleted")
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	


    public UUID getGlobalHouseholdId() {
		return globalHouseholdId;
	}

	public void setGlobalHouseholdId(UUID globalHouseholdId) {
		this.globalHouseholdId = globalHouseholdId;
	}

	public UUID getHeadOfHouseholdId() {
        return headOfHouseholdId;
    }

    public void setHeadOfHouseholdId(UUID  headOfHouseholdId) {
        this.headOfHouseholdId = headOfHouseholdId;
    }

    public Set<HouseholdMembership> getMembers() {
        return members;
    }

    public void setMembers(Set<HouseholdMembership> householdMemberships) {
        this.members = householdMemberships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GlobalHousehold globalHousehold = (GlobalHousehold) o;
        if(globalHousehold.globalHouseholdId == null || globalHouseholdId == null) {
            return false;
        }
        return Objects.equals(globalHouseholdId, globalHousehold.globalHouseholdId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(globalHouseholdId);
    }
    
    

    @Override
    public String toString() {
        return "GlobalHousehold{" +
            "globalHouseholdId=" + globalHouseholdId +
            ", headOfHouseholdId='" + headOfHouseholdId + "'" +
            '}';
    }

	
}
