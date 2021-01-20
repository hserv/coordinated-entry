package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;

@Repository
public interface HousingUnitsRepository extends JpaRepository<HousingInventory, Serializable>,JpaSpecificationExecutor<HousingInventory> {

	List<HousingInventory>	findByProjectGroupCodeAndDeleted(String projectGroupCode,boolean deleted);
	List<HousingInventory>	findByProjectGroupCodeAndVacantAndDeleted(String projectGroupCode,boolean isVacent,boolean deleted);
	List<HousingInventory>	findByHousingInventoryIdAndProjectGroupCodeAndVacantAndDeleted(UUID housingInventoryId,String projectGroupCode,boolean isVacent,boolean deleted);
	List<HousingInventory>  findByProjectIdAndDeleted(String projectId,boolean deleted);
//	List<HousingInventory>  findByProjectIdAndFamilyUnitAndBedsCapacity(UUID projectId,boolean isFamilyUnit);
	List<HousingInventory> findByHousingInventoryIdAndProjectGroupCodeAndDeleted(UUID housingUnitId,
			String userProjectGroup, boolean deleted);
	
	
}