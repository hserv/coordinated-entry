package com.hserv.coordinatedentry.housinginventory.repository;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;


public interface HousingUnitAddressRepository extends JpaRepository<HousingUnitAddress, Serializable>{

	Page<HousingUnitAddress> findByHousingInventoryAndDeleted(HousingInventory housingInventory,boolean deleted,Pageable pageable);
	List<HousingUnitAddress> findByHousingInventoryAndDeleted(HousingInventory housingInventory,boolean deleted);
	HousingUnitAddress findByAddressIdAndProjectGroupCodeAndDeleted(UUID id,
			String projectGroup,boolean deleted);
}
