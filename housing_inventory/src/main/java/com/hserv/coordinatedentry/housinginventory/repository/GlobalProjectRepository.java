package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hserv.coordinatedentry.housinginventory.domain.GlobalProjectEntity;

public interface GlobalProjectRepository extends JpaRepository<GlobalProjectEntity,
Serializable> , JpaSpecificationExecutor<GlobalProjectEntity>{
	
}