package com.hserv.coordinatedentry.housingmatching.translator;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;

@Component
public class HousingInventoryTranslator {
	
	public HousingInventoryModel translate(HousingInventory inventory) {
		// TODO need to finalize the attributes which needs to be mapped from HousingInventory
		// into model HousingInventoryModel.
		return null;
	}

}
