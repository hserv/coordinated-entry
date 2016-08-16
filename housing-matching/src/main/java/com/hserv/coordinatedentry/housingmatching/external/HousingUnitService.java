package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;
import com.servinglynk.hmis.warehouse.client.model.Session;


public interface HousingUnitService {
	
	List<HousingInventory> getHousingInventoryList(String projectType,Session session);

}
