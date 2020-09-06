package com.servinglynk.hmis.warehouse.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.common.MQDateUtil;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmissions;
import com.servinglynk.hmis.warehouse.core.model.HmisPostingModel;
import com.servinglynk.hmis.warehouse.core.model.QuestionResponseModel;
import com.servinglynk.hmis.warehouse.core.model.Questionv2;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
import com.servinglynk.hmis.warehouse.core.model.Session;
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
	final static Logger logger = Logger.getLogger(ClientSurveySubmissionServiceImpl.class);
	
	@Transactional
	public void createClinetSurveySubmission(UUID clientId, UUID surveyId, UUID submissionId,LocalDateTime submissionDate, String surveyCategory) {
		SurveyEntity surveyEntity = daoFactory.getSurveyDao().getSurveyById(surveyId);
		ClientEntity clientEntity = daoFactory.getClientDao().getClientById(clientId);
		ClientSurveySubmissionEntity entity = new ClientSurveySubmissionEntity();
		entity.setClientId(clientEntity);
		entity.setSubmissionId(submissionId);
		entity.setSurveyId(surveyEntity);
		entity.setSurveyCategory(surveyCategory);
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
	public void updateClientSurveySubmission(UUID clientSurveySubmissionId, ClientSurveySubmission clientSurveySubmission, Session session) {
		ClientSurveySubmissionEntity clientSurveySubmissionEntity = daoFactory.getClientSurveySubmissionDao().getById(clientSurveySubmissionId);
		if(clientSurveySubmissionEntity==null) throw new ResourceNotFoundException("Client Survey submission not found");
		clientSurveySubmissionEntity.setUpdatedAt(LocalDateTime.now());
		clientSurveySubmissionEntity.setUser(getUser());
		ClientSurveySubmissionEntity entity = ClientSurveySubmissionConverter.modelToEntity(clientSurveySubmission, clientSurveySubmissionEntity);

		daoFactory.getClientSurveySubmissionDao().updateClientSurveySubmission(entity);
		SurveyEntity surveyEntity = entity.getSurveyId();
		if(clientSurveySubmission.getHmisPostingStatus() != null && surveyEntity !=null && surveyEntity.getId() != null) {
			try {
				Responses responesBySubmissionId = serviceFactory.getResponseServiceV3().getResponsesBySubmissionId(surveyEntity.getId(), entity.getSubmissionId(), null, null);
				HmisPostingModel hmisPostingModel = new HmisPostingModel();
				if(responesBySubmissionId != null) {
					if(entity.getClientId() != null) {
						hmisPostingModel.setDedupClientId(entity.getClientId().getDedupClientId());
						hmisPostingModel.setClientId(entity.getClientId().getId());
					}
					hmisPostingModel.setGlobalProjectId(clientSurveySubmission.getGlobalProjectId());
					hmisPostingModel.setSurveyCategory(clientSurveySubmission.getSurveyCategory());
					hmisPostingModel.setSurveyId(surveyEntity.getId());
					hmisPostingModel.setGlobalEnrollmentId(clientSurveySubmission.getGlobalEnrollmentId());
					hmisPostingModel.setSubmissionDate(clientSurveySubmission.getSubmissionDate());
					if(clientSurveySubmission.getEntryDate() != null)
						hmisPostingModel.setEntryDate(clientSurveySubmission.getEntryDate());
					if(clientSurveySubmission.getExitDate() != null)
						hmisPostingModel.setExitDate(clientSurveySubmission.getExitDate());
					if(clientSurveySubmission.getInformationDate() != null)
						hmisPostingModel.setInformationDate(clientSurveySubmission.getInformationDate());
					
					hmisPostingModel.setProjectGroupCode(SecurityContextUtil.getUserProjectGroup());
					hmisPostingModel.setUserId(SecurityContextUtil.getUserAccount().getAccountId());
					hmisPostingModel.setSchemaVersion(surveyEntity.getHmisVersion());
					hmisPostingModel.setHmisPostingStatus(clientSurveySubmission.getHmisPostingStatus());
					List<Response> responses = responesBySubmissionId.getResponses();
					if(CollectionUtils.isNotEmpty(responses)) {
						List<QuestionResponseModel> questionResponses = new ArrayList<QuestionResponseModel>();
						for(Response response : responses) {
							QuestionResponseModel questionResponse = null;
							try {
								UUID questionId = response.getQuestionId();
								Questionv2  question = serviceFactory.getQuestionServicev2().getQuestionById(questionId);
								if(question.getHudQuestion() || StringUtils.equals("HUD", question.getQuestionClassification()) || StringUtils.equals("CES", question.getQuestionClassification()) ) {
									questionResponse = new QuestionResponseModel();
									questionResponse.setQuestionClassification(question.getQuestionClassification());
									questionResponse.setQuestionText(question.getDisplayText());
									questionResponse.setUpdateUrlTemplate(question.getUpdateUrlTemplate());
									questionResponse.setResponseId(response.getResponseId());
									questionResponse.setResponseText(response.getResponseText());
									questionResponse.setHmisLink(response.getHmisLink());
									questionResponse.setUriObjectField(question.getUriObjectField());
									questionResponses.add(questionResponse);
								}
							} catch (Exception e) {
								logger.error(" Error when trying to build the questionResponseModel ", e);
							}
						}
						hmisPostingModel.setQuestionResponses(questionResponses);
					}
				}
				// creating active mq request
				AMQEvent amqEvent = new AMQEvent();
				amqEvent.setEventType("survey.submissions");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("clientId", entity.getClientId().getId());
				data.put("dedupClientId", entity.getClientId().getDedupClientId());
				data.put("submissionId", entity.getSubmissionId());
				data.put("surveyId",entity.getSurveyId().getId());
				data.put("submissionDate",MQDateUtil.dateTimeToString(entity.getSubmissionDate()));
				data.put("deleted", false);
				data.put("projectGroupCode", SecurityContextUtil.getUserProjectGroup());
				data.put("userId",SecurityContextUtil.getUserAccount().getAccountId());
				data.put("trustedAppId", session.getClientTypeId());
				data.put("sessionToken",SecurityContextUtil.getSession().getToken());
				data.put("hmisPosting", hmisPostingModel.toJSONString());
				amqEvent.setModule("ces");
				amqEvent.setSubsystem("survey");
				amqEvent.setPayload(data);
				messageSender.sendAmqMessage(amqEvent);
			} catch (Exception e) {
				logger.error(" Error posting MQ hmis.posting ", e);
			}
		}
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