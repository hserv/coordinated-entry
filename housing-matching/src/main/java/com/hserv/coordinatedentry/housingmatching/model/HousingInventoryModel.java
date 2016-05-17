package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HousingInventoryModel {
	
	@JsonProperty(value="housing_inventories")
    private List<UnitWrapper> housingInventories;

    public List<UnitWrapper> getHousingInventories() {
        return housingInventories;
    }

    public void setHousingInventories(List<UnitWrapper> housingInventories) {
        this.housingInventories = housingInventories;
    }
}