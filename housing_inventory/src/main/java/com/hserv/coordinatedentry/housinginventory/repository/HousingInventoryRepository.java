package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;

public interface HousingInventoryRepository extends JpaRepository<HousingInventory,
Serializable> , JpaSpecificationExecutor<HousingInventory>{

}
