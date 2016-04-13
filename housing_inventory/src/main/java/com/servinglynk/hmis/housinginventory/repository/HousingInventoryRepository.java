package com.servinglynk.hmis.housinginventory.repository;


import org.springframework.data.jpa.repository.*;

import com.servinglynk.hmis.housinginventory.domain.HousingInventory;

import java.util.List;

/**
 * Spring Data JPA repository for the HousingInventory entity.
 */
public interface HousingInventoryRepository extends JpaRepository<HousingInventory,Long> {

}
