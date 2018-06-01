package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hserv.coordinatedentry.housingmatching.entity.GlobalProjectEntity;

public interface GlobalProjectRepository extends JpaRepository<GlobalProjectEntity,
Serializable> , JpaSpecificationExecutor<GlobalProjectEntity>{
	
}