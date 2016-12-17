package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.MatchProcessLogEntity;
@Repository
public interface MatchProcessLogRepository extends JpaRepository<MatchProcessLogEntity, Serializable>,JpaSpecificationExecutor<MatchProcessLogEntity> {
	

	Page<MatchProcessLogEntity> findByProcessIdOrderByDateCreatedAsc(UUID processId,Pageable pageable);
	
}