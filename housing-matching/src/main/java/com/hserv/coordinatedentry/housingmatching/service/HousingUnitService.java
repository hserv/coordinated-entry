package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;

public interface HousingUnitService {
	
	List<HousingInventoryModel> getHousingInventoryList(String userId);

}
