package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;

public interface HousingUnitAssignmentRepository extends JpaRepository<HousingUnitAssignment, Serializable>{

	Page<HousingUnitAssignment> findByHousingInventoryAndDeleted(HousingInventory housingInventory,boolean deleted,Pageable pageable);
	
	HousingUnitAssignment findByAssignmentIdAndProjectGroupCodeAndDeleted(UUID assignmentId,String projectGroupCode,boolean deleted);
}
