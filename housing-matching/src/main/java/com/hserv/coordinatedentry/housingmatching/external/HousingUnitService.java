package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;


public interface HousingUnitService {
	
	List<HousingInventory> getHousingInventoryList(String token, String projectId);

}
