package com.hserv.coordinatedentry.housingmatching.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;
import com.hserv.coordinatedentry.housingmatching.model.BatchProcessModel;

public interface BatchProcessService {

	BatchProcessModel getScoreStatus(String projectGroup);
	
	Page<BatchProcessEntity> getScoreStatusHistory(String projectGroup,Pageable pageable);
	
	UUID startScoresBatch(String projectGroup,String user);
	
	void endBatch(UUID processId,Boolean success);
	UUID startMatchBatch(String projectGroup,String user);
	
	void endBatch(UUID batchId);
}