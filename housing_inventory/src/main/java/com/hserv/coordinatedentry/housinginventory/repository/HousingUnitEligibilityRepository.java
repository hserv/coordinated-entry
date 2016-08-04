package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitEligibility;

public interface HousingUnitEligibilityRepository extends JpaRepository<HousingUnitEligibility, Serializable>{

	Page<HousingUnitEligibility> findByHousingUnitIdAndProjectId(UUID housingUnitId,UUID projectId,Pageable pageable);
	
}