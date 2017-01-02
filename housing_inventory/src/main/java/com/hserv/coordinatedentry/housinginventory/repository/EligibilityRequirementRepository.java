package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housinginventory.domain.EligibilityRequirement;

public interface EligibilityRequirementRepository extends JpaRepository<EligibilityRequirement, Serializable>{

	Page<EligibilityRequirement> findByProjectIdAndDeleted(UUID projectId,boolean deleted,Pageable pageable);

	Page<EligibilityRequirement> findByProjectIdInAndDeleted(List projectIds,boolean deleted, Pageable pageable);
	
	Page<EligibilityRequirement> findByProjectGroupCodeAndDeleted(String projectGroupCode,boolean deleted,Pageable pageable);

	EligibilityRequirement findByEligibilityIdAndProjectGroupCodeAndDeleted(UUID eligibilityId, String projectGroupCode,boolean deleted);

	Page<EligibilityRequirement> findByProjectIdAndProjectGroupCodeAndDeleted(UUID projectId, String projectGroupCode,boolean deleted,
			Pageable pageable);
	
}