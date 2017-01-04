package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.EligibilityRequirement;

@Repository
public interface EligibilityRequirementRepository extends JpaRepository<EligibilityRequirement, Serializable> {

	List<EligibilityRequirement> findByProjectGroupCodeAndDeleted(String projectGroupCode,boolean deleted);
	
	List<EligibilityRequirement> findByProjectIdAndDeleted(UUID projectId,boolean deleted);
	
}