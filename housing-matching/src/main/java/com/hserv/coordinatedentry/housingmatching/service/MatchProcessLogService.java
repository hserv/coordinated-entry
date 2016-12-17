package com.hserv.coordinatedentry.housingmatching.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.MatchProcessLogEntity;

public interface MatchProcessLogService {

	void log(String messageId, Object[] args, boolean status, UUID housingUnitId, UUID projectId,
			UUID clientId);
	
	Page<MatchProcessLogEntity> getMatchProcessLog(UUID processId,UUID housingUnitId,UUID projectId,UUID clientId,Pageable pageable);
	
}