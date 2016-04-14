package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.HousingUnitAddress;

@Repository
public interface HousingUnitAddressRepository extends JpaRepository<HousingUnitAddress, Serializable>{

}
