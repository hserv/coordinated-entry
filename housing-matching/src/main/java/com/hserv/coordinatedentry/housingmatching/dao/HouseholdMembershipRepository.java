package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
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
	
   Page<HouseholdMembership> findByGlobalHouseholdAndDeleted(GlobalHousehold globalHousehold,boolean deleted,Pageable pageable);

   List<HouseholdMembership> findByGlobalClientIdAndDeleted(UUID clientId,boolean deleted);

   Integer countByGlobalHouseholdAndDeleted(GlobalHousehold globalHousehold,boolean deleted);

}