package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.BatchProcessEntity;
import com.hserv.coordinatedentry.housingmatching.helper.ProcessAlreadyRunningException;
import com.hserv.coordinatedentry.housingmatching.model.BatchProcessModel;
import com.hserv.coordinatedentry.housingmatching.service.BatchProcessService;
import com.hserv.coordinatedentry.housingmatching.translator.BatchProcessTranslator;
import com.hserv.coordinatedentry.housingmatching.util.Constants;

@Component
public class BatchProcessServiceImpl implements BatchProcessService {
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	@Autowired
	BatchProcessTranslator batchProcessTranslator;
	
	
	public void  startBatch(String projectGroup,String user){
		try{
			BatchProcessEntity batchProcessEntity = new BatchProcessEntity();
			batchProcessEntity.setInitiateBy(user);
			batchProcessEntity.setStatus(Constants.BATCH_STATUS_INPROGRESS);
			batchProcessEntity.setStartedAt(LocalDateTime.now());
			batchProcessEntity.setIsProcessing(1L);
			repositoryFactory.getBatchProcessRepository().save(batchProcessEntity);
		}catch (Exception e) {
			throw new ProcessAlreadyRunningException(); 
		}
		
			System.out.println("Batch process record created");
	}
	
	public void endBatch(String projectGroup,Boolean success){
		List<BatchProcessEntity> entities = repositoryFactory.getBatchProcessRepository().findByProjectGroupCodeAndStatus(projectGroup, Constants.BATCH_STATUS_INPROGRESS);
		if(!entities.isEmpty()){
			BatchProcessEntity batchProcessEntity = entities.get(0);
			if(success)
				batchProcessEntity.setStatus(Constants.BATCH_STATUS_COMPLETED);
			else
				batchProcessEntity.setStatus(Constants.BATCH_STATUS_FAILED);
			batchProcessEntity.setCompletedAt(LocalDateTime.now());
			batchProcessEntity.setIsProcessing(null);
			repositoryFactory.getBatchProcessRepository().save(batchProcessEntity);
			System.out.println("Batch process record updated");
		}
	}
	
	
	public BatchProcessModel getStatus(String projectGroup){
		Specification<BatchProcessEntity> specification = Specifications.where(new Specification<BatchProcessEntity>() {

			@Override
			public Predicate toPredicate(Root<BatchProcessEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
			return criteriaBuilder.and(
						criteriaBuilder.equal(root.get("projectGroupCode"),projectGroup));
			}	
		});
		
		Sort sort = new Sort(Direction.DESC,"dateUpdated");
		List<BatchProcessEntity> entities = repositoryFactory.getBatchProcessRepository().findAll(specification,sort);
		
		if(!entities.isEmpty())
				return batchProcessTranslator.translate(entities.get(0));
		 return null;
	}

	
	public Page<BatchProcessEntity> getStatusHistory(String projectGroup,Pageable pageable){
		Specification<BatchProcessEntity> specification = Specifications.where(new Specification<BatchProcessEntity>() {

			@Override
			public Predicate toPredicate(Root<BatchProcessEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
			return criteriaBuilder.and(
						criteriaBuilder.equal(root.get("projectGroupCode"),projectGroup));
			}	
		});
		
		Sort sort = new Sort(Direction.DESC,"dateUpdated");
		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),sort);
		Page<BatchProcessEntity> entities = repositoryFactory.getBatchProcessRepository().findAll(specification,pageRequest);
		
		 return entities;
	}

}