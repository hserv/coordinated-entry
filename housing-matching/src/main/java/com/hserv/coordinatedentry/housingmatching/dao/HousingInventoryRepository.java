package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;

@Repository
public interface HousingInventoryRepository extends JpaRepository<HousingInventory, Serializable> {

	static String QUERY_FIND_AVAILABLE_HOUSING_UNIT = "select hi from HousingInventory as hi where hi.bedsCurrent >0 "
			+ " and hi.bedsCurrent >0 and hi.inService=true and hi.vacant=true and hi.inactive=false";

	public List<HousingInventory> findByUserId(String userId);

	@Query(QUERY_FIND_AVAILABLE_HOUSING_UNIT)
	public List<HousingInventory> findAvailableHousingUnit();
}
