package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housinginventory.domain.EligibilityRequirement;

public interface EligibilityRequirementRepository extends JpaRepository<EligibilityRequirement, Serializable>{

	Page<EligibilityRequirement> findByProjectId(UUID projectId,Pageable pageable);

	Page<EligibilityRequirement> findByProjectIdIn(List projectIds, Pageable pageable);
	
	Page<EligibilityRequirement> findByProjectGroupCode(String projectGroupCode,Pageable pageable);
	
}