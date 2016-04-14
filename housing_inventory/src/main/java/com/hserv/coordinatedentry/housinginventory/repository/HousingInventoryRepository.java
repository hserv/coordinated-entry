package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the HousingInventory entity.
 */
public interface HousingInventoryRepository extends JpaRepository<HousingInventory,Long> {

}
