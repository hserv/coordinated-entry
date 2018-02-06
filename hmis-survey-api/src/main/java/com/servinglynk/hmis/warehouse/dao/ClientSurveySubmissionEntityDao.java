package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public interface ClientSurveySubmissionEntityDao {
	
	List<ClientSurveySubmissionEntity> getClientSurveySubmissionEntityByClienId(UUID clientId);
	ClientSurveySubmissionEntity createClientSubmissionEntity(ClientSurveySubmissionEntity  clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity updateClientSubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity getClientSubmissionEntitybyClientSurveySubmission(UUID clientId,UUID surveyId,UUID submissionId); 
	ClientSurveySubmissionEntity getClientSurveySubmissionEntityById(UUID Id);
	}
