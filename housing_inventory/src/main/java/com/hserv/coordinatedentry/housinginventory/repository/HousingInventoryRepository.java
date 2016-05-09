package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the HousingInventory entity.
 */
public interface HousingInventoryRepository extends JpaRepository<HousingInventory,UUID> {

}
