package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.HousingInventoryRepository;
import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;
import com.hserv.coordinatedentry.housingmatching.service.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.translator.HousingInventoryTranslator;

@Service
public class HousingUnitServiceImpl implements HousingUnitService {
	
	@Autowired
	private HousingInventoryRepository housingInventoryRepo;
	
	@Autowired
	private HousingInventoryTranslator housingInventoryTranslator;

	@Override
	public List<HousingInventoryModel> getHousingInventoryList(String userId) {
		List<HousingInventory> housingInventories = housingInventoryRepo.findByUserId(userId);
		List<HousingInventoryModel> inventoryModels = new ArrayList<HousingInventoryModel>();
		for(HousingInventory inventory : housingInventories) {
			inventoryModels.add(housingInventoryTranslator.translate(inventory));
		}
		return null;
	}

}
