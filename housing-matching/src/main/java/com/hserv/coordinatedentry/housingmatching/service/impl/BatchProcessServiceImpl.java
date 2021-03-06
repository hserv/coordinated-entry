package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
	
	
	public UUID  startScoresBatch(String projectGroup,String user){
		try{
			BatchProcessEntity batchProcessEntity = new BatchProcessEntity();
			batchProcessEntity.setInitiateBy(user);
			batchProcessEntity.setStatus(Constants.BATCH_STATUS_INPROGRESS);
			batchProcessEntity.setProcessType(Constants.SCORES_PROCESS_BATCH);
			batchProcessEntity.setStartedAt(LocalDateTime.now());
			batchProcessEntity.setIsProcessing(1L);
			repositoryFactory.getBatchProcessRepository().save(batchProcessEntity);
			System.out.println("Batch process record created   "+batchProcessEntity.getId());
			return batchProcessEntity.getId();
		}catch (Exception e) {
			List<BatchProcessEntity> jobs= repositoryFactory.getBatchProcessRepository().findByProjectGroupCodeAndStatus(projectGroup, Constants.BATCH_STATUS_INPROGRESS);
			for(BatchProcessEntity job : jobs) {
				int diff =job.getStartedAt().compareTo(LocalDateTime.now().minusHours(2));
				if(diff<0) {
					endBatch(job.getId(),false);
				}
			}
			try{
				BatchProcessEntity batchProcessEntity = new BatchProcessEntity();
				batchProcessEntity.setInitiateBy(user);
				batchProcessEntity.setStatus(Constants.BATCH_STATUS_INPROGRESS);
				batchProcessEntity.setProcessType(Constants.SCORES_PROCESS_BATCH);
				batchProcessEntity.setStartedAt(LocalDateTime.now());
				batchProcessEntity.setIsProcessing(1L);
				repositoryFactory.getBatchProcessRepository().save(batchProcessEntity);
				System.out.println("Batch process record created   "+batchProcessEntity.getId());
				return batchProcessEntity.getId();
			}catch (Exception ex) {
				throw new ProcessAlreadyRunningException(); 
			}
		}
	}
	
	public UUID startMatchBatch(String projectGroup,String user){
		try{
			BatchProcessEntity batchProcessEntity = new BatchProcessEntity();
			batchProcessEntity.setInitiateBy(user);
			batchProcessEntity.setStatus(Constants.BATCH_STATUS_INPROGRESS);
			batchProcessEntity.setProcessType(Constants.MATCH_PROCESS_BATCH);
			batchProcessEntity.setStartedAt(LocalDateTime.now());
			repositoryFactory.getBatchProcessRepository().save(batchProcessEntity);
			return batchProcessEntity.getId();
		}catch (Exception e) {
			throw new ProcessAlreadyRunningException(); 
		}
	}
	
	public void endBatch(UUID batchId){
		BatchProcessEntity entity =  repositoryFactory.getBatchProcessRepository().findOne(batchId);
		entity.setStatus(Constants.BATCH_STATUS_COMPLETED);
		entity.setCompletedAt(LocalDateTime.now());
		entity.setIsProcessing(null);
		repositoryFactory.getBatchProcessRepository().save(entity);
	}
	
	public void endBatch(UUID processId,Boolean success){
		BatchProcessEntity batchProcessEntity = repositoryFactory.getBatchProcessRepository().findOne(processId);
			if(success)
				batchProcessEntity.setStatus(Constants.BATCH_STATUS_COMPLETED);
			else
				batchProcessEntity.setStatus(Constants.BATCH_STATUS_FAILED);
			batchProcessEntity.setCompletedAt(LocalDateTime.now());
			batchProcessEntity.setIsProcessing(null);
			repositoryFactory.getBatchProcessRepository().save(batchProcessEntity);
			System.out.println("Batch process record updated");
	}
	
	
	public BatchProcessModel getScoreStatus(String projectGroup){
		Specification<BatchProcessEntity> specification = Specifications.where(new Specification<BatchProcessEntity>() {

			@Override
			public Predicate toPredicate(Root<BatchProcessEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
			return criteriaBuilder.and(
						criteriaBuilder.equal(root.get("projectGroupCode"),projectGroup),
						criteriaBuilder.equal(root.get("processType"),Constants.SCORES_PROCESS_BATCH)
						);
			}	
		});
		
		Sort sort = new Sort(Direction.DESC,"dateUpdated");
		List<BatchProcessEntity> entities = repositoryFactory.getBatchProcessRepository().findAll(specification,sort);
		
		if(!entities.isEmpty())
				return batchProcessTranslator.translate(entities.get(0));
		 return null;
	}

	
	public Page<BatchProcessEntity> getScoreStatusHistory(String projectGroup,Pageable pageable){
		Specification<BatchProcessEntity> specification = Specifications.where(new Specification<BatchProcessEntity>() {

			@Override
			public Predicate toPredicate(Root<BatchProcessEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
			return criteriaBuilder.and(
						criteriaBuilder.equal(root.get("projectGroupCode"),projectGroup),
						criteriaBuilder.equal(root.get("processType"),Constants.SCORES_PROCESS_BATCH)
					);
			}	
		});
		
		Sort sort = new Sort(Direction.DESC,"dateUpdated");
		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),sort);
		Page<BatchProcessEntity> entities = repositoryFactory.getBatchProcessRepository().findAll(specification,pageRequest);
		
		 return entities;
	}

}