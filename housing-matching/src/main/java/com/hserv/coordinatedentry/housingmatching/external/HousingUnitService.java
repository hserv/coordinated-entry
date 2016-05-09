package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;


public interface HousingUnitService {
	
	HousingInventoryModel getHousingInventoryList(String userId, boolean inactive, String projectId);

}
