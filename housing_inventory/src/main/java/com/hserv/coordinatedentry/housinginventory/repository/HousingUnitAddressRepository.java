package com.hserv.coordinatedentry.housinginventory.repository;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;


public interface HousingUnitAddressRepository extends JpaRepository<HousingUnitAddress, Serializable>{

	
}
