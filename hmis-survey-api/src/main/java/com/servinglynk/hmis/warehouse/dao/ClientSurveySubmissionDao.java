package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public interface ClientSurveySubmissionDao {

	
	UUID create(ClientSurveySubmissionEntity entity);
	
	void updateClientSurveySubmission(ClientSurveySubmissionEntity entity);
	
	ClientSurveySubmissionEntity getById(UUID id);
	
	List<ClientSurveySubmissionEntity> getAllClientSurveySubmissions(UUID clientId, String queryString, String sortField, String order, Integer startIndex, Integer maxItems);
	
	long clientSurveySubmissionsCount(UUID clientId, String queryString);
	List<ClientSurveySubmissionEntity> getSearchClientSurveySubmissions(String name, UUID globalClientId,
			Integer startIndex, Integer maxItems,String sortField,String order);

	long clientSurveySubmissionsCount(String name, UUID globalClientId);
	
	List<ClientSurveySubmissionEntity> getAllSurveySubmissions(UUID surveyId, UUID submissionId);

	void deleteSubmission(ClientSurveySubmissionEntity entity);

	List<ClientSurveySubmissionEntity> getSubmissionBySurveyIdAndClientId(UUID surveyId, UUID clientId);

}