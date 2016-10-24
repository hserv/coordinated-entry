package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;

public interface HousingInventoryRepository extends JpaRepository<HousingInventory,
Serializable> , JpaSpecificationExecutor<HousingInventory>{

	HousingInventory findByHousingInventoryIdAndProjectGroupCode(UUID inventoryId, String projectgroup);
	
	
	@Query(value="select 1",nativeQuery=true)
	Integer validateConnection();
}
