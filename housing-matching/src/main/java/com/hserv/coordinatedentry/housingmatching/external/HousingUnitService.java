package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.EligibilityRequirementModel;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;
import com.servinglynk.hmis.warehouse.core.model.Session;


public interface HousingUnitService {
	
	List<HousingInventory> getHousingInventoryList(Session session, String trustedAppId);

	List<EligibilityRequirementModel> getEligibilityRequirements(Session session, String trustedAppId);

}
