package com.hserv.coordinatedentry.housingmatching.dao;
import org.springframework.data.jpa.repository.*;

import com.hserv.coordinatedentry.housingmatching.entity.GlobalHousehold;

import java.io.Serializable;
import java.util.UUID;


/**
 * Spring Data JPA repository for the GlobalHousehold entity.
 */
public interface GlobalHouseholdRepository extends JpaRepository<GlobalHousehold,Serializable> {

	GlobalHousehold findByHeadOfHouseholdIdAndDeleted(UUID clientId, boolean deleted);

}
