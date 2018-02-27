package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public interface ClientSurveySubmissionDao {

	
	void create(ClientSurveySubmissionEntity entity);
	
	void updateClientSurveySubmission(ClientSurveySubmissionEntity entity);
	
	ClientSurveySubmissionEntity getById(UUID id);
	
	List<ClientSurveySubmissionEntity> getAllClientSurveySubmissions(UUID clientId, Integer startIndex, Integer maxItems);
	
	long clientSurveySubmissionsCount(UUID clientId);
}