package com.hserv.coordinatedentry.housinginventory.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;


/**
 * A DTO for the HousingInventory entity.
 */
public class HousingInventoryDTO implements Serializable {

    

    private UUID housingUnitId;


    private String projectId;


    private String userId;


    private Integer bedsCurrent;


    private Integer bedsCapacity;


    private Boolean familyUnit;

    

   
    public UUID getHousingUnitId() {
		return housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getBedsCurrent() {
        return bedsCurrent;
    }

    public void setBedsCurrent(Integer bedsCurrent) {
        this.bedsCurrent = bedsCurrent;
    }
    public Integer getBedsCapacity() {
        return bedsCapacity;
    }

    public void setBedsCapacity(Integer bedsCapacity) {
        this.bedsCapacity = bedsCapacity;
    }
    public Boolean getFamilyUnit() {
        return familyUnit;
    }

    public void setFamilyUnit(Boolean familyUnit) {
        this.familyUnit = familyUnit;
    }

  
}
