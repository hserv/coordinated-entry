package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hserv.coordinatedentry.housingmatching.entity.GlobalProjectMapEntity;

public interface GlobalProjectMapRepository extends JpaRepository<GlobalProjectMapEntity,
Serializable> , JpaSpecificationExecutor<GlobalProjectMapEntity>{
	List<GlobalProjectMapEntity> findByProjectIdAndProjectGroupCode(UUID projectId,String projectGroupCode);
}

