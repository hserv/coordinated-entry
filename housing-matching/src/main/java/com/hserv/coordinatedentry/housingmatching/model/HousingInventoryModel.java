package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HousingInventoryModel {
	
	@JsonProperty(value="housing_inventories")
	List<HousingInventory> housingInventories;

	public List<HousingInventory> getHousingInventories() {
		return housingInventories;
	}

	public void setHousingInventories(List<HousingInventory> housingInventories) {
		this.housingInventories = housingInventories;
	}

	@Override
	public String toString() {
		return "HousingInventoryModel [housingInventories=" + housingInventories + "]";
	}
	
	
}