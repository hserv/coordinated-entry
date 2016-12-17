package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;

@Repository
public interface HousingUnitsRepository extends JpaRepository<HousingInventory, Serializable>,JpaSpecificationExecutor<HousingInventory> {

	List<HousingInventory>	findByProjectGroupCode(String projectGroupCode);
	List<HousingInventory>	findByProjectGroupCodeAndVacant(String projectGroupCode,boolean isVacent);
	List<HousingInventory>  findByProjectId(String projectId);
//	List<HousingInventory>  findByProjectIdAndFamilyUnitAndBedsCapacity(UUID projectId,boolean isFamilyUnit);
	
	
}