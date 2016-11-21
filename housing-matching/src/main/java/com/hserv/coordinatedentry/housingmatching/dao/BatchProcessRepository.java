package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;

public interface BatchProcessRepository extends JpaRepository<BatchProcessEntity, Serializable>,JpaSpecificationExecutor<BatchProcessEntity> {
	
	List<BatchProcessEntity> findByProjectGroupCodeAndStatus(String projectGroupCode,String status);

}
