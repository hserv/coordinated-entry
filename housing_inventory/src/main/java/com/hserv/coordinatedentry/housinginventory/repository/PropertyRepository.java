package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housinginventory.domain.PropertyEntity;


@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Serializable>{

	@Query(value="SELECT * FROM base.hmis_property WHERE service = 'COMMON'",nativeQuery=true)
	List<PropertyEntity> readCommonProperties();
	@Query(value="SELECT * FROM base.hmis_property WHERE service = ? ",nativeQuery=true)
	List<PropertyEntity> readProperties(String serviceName);

}
