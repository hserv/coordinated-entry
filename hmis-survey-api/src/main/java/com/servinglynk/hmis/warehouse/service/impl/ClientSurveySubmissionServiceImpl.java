package com.servinglynk.hmis.warehouse.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.common.MQDateUtil;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;
import com.servinglynk.hmis.warehouse.core.model.Sort;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.AMQEvent;
import com.servinglynk.hmis.warehouse.model.ClientEntity;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.service.ClientSurveySubmissionService;
import com.servinglynk.hmis.warehouse.service.converter.ClientSurveySubmissionConverter;
import com.servinglynk.hmis.warehouse.service.exception.ResourceNotFoundException;
import com.servinglynk.hmis.warehouse.util.SecurityContextUtil;

@Component
public class ClientSurveySubmissionServiceImpl extends ServiceBase implements ClientSurveySubmissionService {

	@Transactional
	public void createClinetSurveySubmission(UUID clientId, UUID surveyId, UUID submissionId,LocalDateTime submissionDate) {
		SurveyEntity surveyEntity = daoFactory.getSurveyDao().getSurveyById(surveyId);
		ClientEntity clientEntity = daoFactory.getClientDao().getClientById(clientId);
		ClientSurveySubmissionEntity entity = new ClientSurveySubmissionEntity();
		entity.setClientId(clientEntity);
		entity.setSubmissionId(submissionId);
		entity.setSurveyId(surveyEntity);
		entity.setSubmissionDate(submissionDate);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUser(getUser());
		entity.setProjectGroupCode(SecurityContextUtil.getUserProjectGroup());
		
		daoFactory.getClientSurveySubmissionDao().create(entity);
		
		
		// creating active mq request
		AMQEvent amqEvent = new AMQEvent();

		amqEvent.setEventType("survey.submissions");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("clientId", clientId);
		data.put("dedupClientId", clientEntity.getDedupClientId());
		data.put("submissionId", submissionId);
		data.put("surveyId",surveyId);
		data.put("submissionDate",MQDateUtil.dateTimeToString(submissionDate));
		data.put("deleted", false);
		data.put("projectGroupCode", SecurityContextUtil.getUserProjectGroup());
		data.put("userId",SecurityContextUtil.getUserAccount().getAccountId());
		amqEvent.setPayload(data);
		amqEvent.setModule("ces");
		amqEvent.setSubsystem("survey");
		messageSender.sendAmqMessage(amqEvent);
	
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
	public void updateHmisPostingStatus(UUID clientSurveySubmissionId, String hmisPostingStatus) {
		ClientSurveySubmissionEntity entity = daoFactory.getClientSurveySubmissionDao().getById(clientSurveySubmissionId);
		if(entity==null) throw new ResourceNotFoundException("Client Survey submission not found");
		entity.setHmisPostStatus(hmisPostingStatus);
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUser(getUser());
		daoFactory.getClientSurveySubmissionDao().updateClientSurveySubmission(entity);
	}
	
	
	@Transactional
	public ClientSurveySubmissions getAllClientSurveySubmissions(UUID clientId,String queryString, String sortColumn, String order,Integer startIndex,Integer maxItems) {
		ClientSurveySubmissions submissions = new ClientSurveySubmissions();
		List<ClientSurveySubmission> clientSurveySubmissions = new ArrayList<ClientSurveySubmission>();
		String sortField=null;

			 if(sortColumn.equalsIgnoreCase("surveyId"))  sortField ="surveyId.id";
			 if(sortColumn.equalsIgnoreCase("surveyTitle")) sortField ="surveyId.surveyTitle";
			 if(sortColumn.equalsIgnoreCase("submissionDate")) sortField = "submissionDate";

		List<ClientSurveySubmissionEntity> entities = daoFactory.getClientSurveySubmissionDao().getAllClientSurveySubmissions(clientId,queryString,sortField,order,startIndex,maxItems);
		
		for(ClientSurveySubmissionEntity entity : entities ) {

			ClientSurveySubmission model = ClientSurveySubmissionConverter.entityToModel(entity);
			clientSurveySubmissions.add(model);
		}
				
		submissions.setClientSurveySubmissions(clientSurveySubmissions);
		
		long count = daoFactory.getClientSurveySubmissionDao().clientSurveySubmissionsCount(clientId,queryString);

		SortedPagination pagination = new SortedPagination();
	        Sort sort = new Sort();
	        sort.setField(sortColumn);
	        sort.setOrder(order);
	        pagination.setSort(sort);
	        pagination.setMaximum(maxItems);
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
		
			 if(sortColumn.equalsIgnoreCase("surveyId"))  sortField ="surveyId.id";
			 if(sortColumn.equalsIgnoreCase("surveyTitle")) sortField ="surveyId.surveyTitle";
			 if(sortColumn.equalsIgnoreCase("clientName")) sortField ="clientId.firstName";
			 if(sortColumn.equalsIgnoreCase("submissionDate")) sortField = "submissionDate";
		
		List<ClientSurveySubmissionEntity> entities = daoFactory.getClientSurveySubmissionDao().getSearchClientSurveySubmissions(name,globalClientId,startIndex,maxItems,sortField,order);
		
		for(ClientSurveySubmissionEntity entity : entities ) {
			ClientSurveySubmission model = ClientSurveySubmissionConverter.entityToModel(entity);
			clientSurveySubmissions.add(model);
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
			
			 if (s1.getSubmissionDate() == null) {
			        return (s2.getSubmissionDate() == null) ? 0 : -1;
			    }
			    if (s2.getSubmissionDate() == null) {
			        return 1;
			    }
				return s1.getSubmissionDate().compareTo(s2.getSubmissionDate());
		}
	}
	
	class SubmissionDateDescComparator implements Comparator<ClientSurveySubmission> {
		
		public int compare(ClientSurveySubmission s1, ClientSurveySubmission s2) {
			
			 if (s2.getSubmissionDate() == null) {
			        return (s1.getSubmissionDate() == null) ? 0 : -1;
			    }
			    if (s1.getSubmissionDate() == null) {
			        return 1;
			    }
				return s2.getSubmissionDate().compareTo(s1.getSubmissionDate());
		}
	}
	
	@Transactional
	public void updateClientSurveySubmissionDate(UUID surveyId, UUID clientId) {
		List<ClientSurveySubmissionEntity>	entities =	daoFactory.getClientSurveySubmissionDao().getSubmissionBySurveyIdAndClientId(surveyId,clientId);
		for(ClientSurveySubmissionEntity entity : entities) {
		//	if(entity.getSubmissionDate()==null) {
				entity.setSubmissionDate(LocalDateTime.now());
				daoFactory.getClientSurveySubmissionDao().updateClientSurveySubmission(entity);
		//	}
		}
	}
}