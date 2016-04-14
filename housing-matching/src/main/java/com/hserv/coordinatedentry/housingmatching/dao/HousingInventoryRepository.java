package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;

@Repository
public interface HousingInventoryRepository extends JpaRepository<HousingInventory, Serializable>{

	public List<HousingInventory> findByUserId(String userId);
	
}
