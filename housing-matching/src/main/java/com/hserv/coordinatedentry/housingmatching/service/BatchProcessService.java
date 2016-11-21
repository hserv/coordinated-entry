package com.hserv.coordinatedentry.housingmatching.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;
import com.hserv.coordinatedentry.housingmatching.model.BatchProcessModel;

public interface BatchProcessService {

	BatchProcessModel getStatus(String projectGroup);
	
	Page<BatchProcessEntity> getStatusHistory(String projectGroup,Pageable pageable);
	
	void startBatch(String projectGroup,String user);
	
	void endBatch(String projectGroup,Boolean success);
}