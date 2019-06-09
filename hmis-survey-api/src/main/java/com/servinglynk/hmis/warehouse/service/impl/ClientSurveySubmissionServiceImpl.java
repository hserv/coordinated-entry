package com.servinglynk.hmis.warehouse.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;
import com.servinglynk.hmis.warehouse.core.model.Sort;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.ClientEntity;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
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

			ClientSurveySubmission model = ClientSurveySubmissionConverter.entityToModel(entity);
			LocalDateTime responseSubmissionDate = daoFactory.getResponseEntityDao().getSurveyDate(model.getClientId(),model.getSurveyId());
			if(responseSubmissionDate==null) responseSubmissionDate = daoFactory.getSectionScoreDao().getSurveyScoreDate(model.getClientId(),model.getSurveyId());
			model.setSubmissionDate(responseSubmissionDate);
			submissions.addClientSurveySubmission(model);
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
			Integer maxItems, String sortColumn, String order) {
		ClientSurveySubmissions submissions = new ClientSurveySubmissions();
		List<ClientSurveySubmission> clientSurveySubmissions = new ArrayList<ClientSurveySubmission>();
		String sortField=null;
		UUID globalClientId =null;
		String name=null;
		try {
			globalClientId =	UUID.fromString(queryString);
		}catch (Exception e) {
			name = queryString;
		}
		
		if(sortColumn.equalsIgnoreCase("surveyId"))   sortField ="surveyId";
		
		List<ClientSurveySubmissionEntity> entities = daoFactory.getClientSurveySubmissionDao().getSearchClientSurveySubmissions(name,globalClientId,startIndex,maxItems,sortField,order);
		
		for(ClientSurveySubmissionEntity entity : entities ) {
			ClientSurveySubmission model = ClientSurveySubmissionConverter.entityToModel(entity);
			LocalDateTime responseSubmissionDate = daoFactory.getResponseEntityDao().getSurveyDate(model.getClientId(),model.getSurveyId());
			if(responseSubmissionDate==null) responseSubmissionDate = daoFactory.getSectionScoreDao().getSurveyScoreDate(model.getClientId(),model.getSurveyId());
			model.setSubmissionDate(responseSubmissionDate);
			clientSurveySubmissions.add(model);
		}
		
		if(sortField!=null) {
			if(order==null || order.equalsIgnoreCase("asc")) {
				Collections.sort(clientSurveySubmissions, new SubmissionDateAscComparator());
			}else {
				Collections.sort(clientSurveySubmissions, new SubmissionDateDescComparator());				
			}
		}
		
		submissions.setClientSurveySubmissions(clientSurveySubmissions);
		
		long count = daoFactory.getClientSurveySubmissionDao().clientSurveySubmissionsCount(name,globalClientId);
		 SortedPagination pagination = new SortedPagination();
		 
	        pagination.setFrom(startIndex);
	        pagination.setReturned(submissions.getClientSurveySubmissions().size());
	        pagination.setTotal((int)count);
	        pagination.setMaximum(maxItems);
	        Sort sort = new Sort();
	        sort.setField(sortColumn);
	        sort.setOrder(order);
	        pagination.setSort(sort);
	        submissions.setPagination(pagination);
		
		return submissions;
	}
	
	class SubmissionDateAscComparator implements Comparator<ClientSurveySubmission> {
		
		public int compare(ClientSurveySubmission s1, ClientSurveySubmission s2) {
			return s1.getSubmissionDate().compareTo(s2.getSubmissionDate());
		}
	}
	
	class SubmissionDateDescComparator implements Comparator<ClientSurveySubmission> {
		
		public int compare(ClientSurveySubmission s1, ClientSurveySubmission s2) {
			return s2.getSubmissionDate().compareTo(s1.getSubmissionDate());
		}
	}
}