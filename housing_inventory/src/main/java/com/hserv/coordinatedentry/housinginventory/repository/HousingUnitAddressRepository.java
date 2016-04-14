package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the HousingUnitAddress entity.
 */
public interface HousingUnitAddressRepository extends JpaRepository<HousingUnitAddress,Long> {

}
