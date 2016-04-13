package com.servinglynk.hmis.housinginventory.repository;


import org.springframework.data.jpa.repository.*;

import com.servinglynk.hmis.housinginventory.domain.HousingUnitAddress;

import java.util.List;

/**
 * Spring Data JPA repository for the HousingUnitAddress entity.
 */
public interface HousingUnitAddressRepository extends JpaRepository<HousingUnitAddress,Long> {

}
