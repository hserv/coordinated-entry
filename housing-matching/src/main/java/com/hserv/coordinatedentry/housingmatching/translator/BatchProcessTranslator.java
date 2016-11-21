package com.hserv.coordinatedentry.housingmatching.translator;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;
import com.hserv.coordinatedentry.housingmatching.model.BatchProcessModel;


@Component
public class BatchProcessTranslator {
	
	public static BatchProcessModel translate(BatchProcessEntity batchProcessEntity){
		BatchProcessModel model = new BatchProcessModel();
		model.setCompletedAt(batchProcessEntity.getCompletedAt());
		model.setInitiatedBy(batchProcessEntity.getInitiateBy());
		model.setStartedAt(batchProcessEntity.getStartedAt());
		model.setStatus(batchProcessEntity.getStatus());
		return model;
	}

}
