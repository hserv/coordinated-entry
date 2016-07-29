package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;

public interface HousingUnitAssignmentRepository extends JpaRepository<HousingUnitAssignment, Serializable>{

	Page<HousingUnitAssignment> findByHousingInventory(HousingInventory housingInventory,Pageable pageable);
}
