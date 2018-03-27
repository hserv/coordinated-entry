package com.servinglynk.hmis.warehouse.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.ClientEntity;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.service.ClientSurveySubmissionService;
import com.servinglynk.hmis.warehouse.service.converter.ClientSurveySubmissionConverter;
import com.servinglynk.hmis.warehouse.service.exception.ResourceNotFoundException;
import com.servinglynk.hmis.warehouse.util.SecurityContextUtil;

@Component
public class ClientSurveySubmissionServiceImpl extends ServiceBase implements ClientSurveySubmissionService {

	@Transactional
	public void createClinetSurveySubmission(UUID clientId, UUID surveyId, UUID submissionId) {
		ClientEntity clientEntity = daoFactory.getClientDao().getClientById(clientId);
		ClientSurveySubmissionEntity entity = new ClientSurveySubmissionEntity();
		entity.setClientId(clientEntity);
		ClientSurveySubmissionEntity entity = new ClientSurveySubmissionEntity();

		entity.setSubmissionId(submissionId);
		entity.setSurveyId(surveyId);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUser(getUser());
		entity.setProjectGroupCode(SecurityContextUtil.getUserProjectGroup());
		
		daoFactory.getClientSurveySubmissionDao().create(entity);
		
	}
	
	@Transactional
	public void updateClientSurveySubmission(UUID clientSurveySubmissionId, UUID globalEnrollmentId) {
		ClientSurveySubmissionEntity entity = daoFactory.getClientSurveySubmissionDao().getById(clientSurveySubmissionId);
		if(entity==null) throw new ResourceNotFoundException("Client Survey submission not found");
		entity.setGlobalEnrollmentId(globalEnrollmentId);
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUser(getUser());
		daoFactory.getClientSurveySubmissionDao().updateClientSurveySubmission(entity);

	}
	
	@Transactional
	public ClientSurveySubmissions getAllClientSurveySubmissions(UUID clientId,Integer startIndex,Integer maxItems) {
		ClientSurveySubmissions submissions = new ClientSurveySubmissions();
		
		List<ClientSurveySubmissionEntity> entities = daoFactory.getClientSurveySubmissionDao().getAllClientSurveySubmissions(clientId,startIndex,maxItems);
		
		for(ClientSurveySubmissionEntity entity : entities ) {
			submissions.addClientSurveySubmission(ClientSurveySubmissionConverter.entityToModel(entity));
		}
		
		long count = daoFactory.getClientSurveySubmissionDao().clientSurveySubmissionsCount(clientId);
		 SortedPagination pagination = new SortedPagination();
		 
	        pagination.setFrom(startIndex);
	        pagination.setReturned(submissions.getClientSurveySubmissions().size());
	        pagination.setTotal((int)count);
	        submissions.setPagination(pagination);
		
		return submissions;
	}

	@Transactional
	public ClientSurveySubmissions getSearchClientSurveySubmissions(String queryString, Integer startIndex,
			Integer maxItems) {
		ClientSurveySubmissions submissions = new ClientSurveySubmissions();
		
		UUID globalClientId =null;
		String name=null;
		try {
			globalClientId =	UUID.fromString(queryString);
		}catch (Exception e) {
			name = queryString;
		}
		
		List<ClientSurveySubmissionEntity> entities = daoFactory.getClientSurveySubmissionDao().getSearchClientSurveySubmissions(name,globalClientId,startIndex,maxItems);
		
		for(ClientSurveySubmissionEntity entity : entities ) {
			submissions.addClientSurveySubmission(ClientSurveySubmissionConverter.entityToModel(entity));
		}
		
		long count = daoFactory.getClientSurveySubmissionDao().clientSurveySubmissionsCount(name,globalClientId);
		 SortedPagination pagination = new SortedPagination();
		 
	        pagination.setFrom(startIndex);
	        pagination.setReturned(submissions.getClientSurveySubmissions().size());
	        pagination.setTotal((int)count);
	        submissions.setPagination(pagination);
		
		return submissions;
	}

}