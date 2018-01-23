package com.servinglynk.hmis.warehouse.service;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public interface ClientSurveySubmissionService {
	
	ClientSurveySubmissionEntity createClientSurveySubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	void createClientSurveySubmissionEntity(List<ClientSurveySubmissionEntity> clientSurveySubmissionEntities);
	ClientSurveySubmissionEntity updateClientSurveySubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity getClientSurveySubmissionEntitybyId(UUID id);
}

