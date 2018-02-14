package com.servinglynk.hmis.warehouse.service;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public interface ClientSurveySubmissionService {
	
	List<ClientSurveySubmissionEntity> getClientSurveySubmissionEntitybyClientId(UUID clientId);
	ClientSurveySubmissionEntity createClientSurveySubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity updateClientSurveySubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity getClientSurveySubmissionEntitybyId(UUID Id);
	ClientSurveySubmissionEntity getClientSurveySubmissionEntitybyClientSurveySubmission(UUID clientId,UUID surveyId,UUID submissionId);
}

