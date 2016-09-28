package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housingmatching.entity.GlobalHousehold;
import com.hserv.coordinatedentry.housingmatching.entity.HouseholdMembership;

/**
 * Spring Data JPA repository for the HouseholdMembership entity.
 */
public interface HouseholdMembershipRepository extends JpaRepository<HouseholdMembership,Serializable> {
	
   Page<HouseholdMembership> findByGlobalHousehold(GlobalHousehold globalHousehold,Pageable pageable);

   HouseholdMembership findByGlobalClientId(UUID clientId);

   Integer countByGlobalHousehold(GlobalHousehold globalHousehold);

}