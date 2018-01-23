package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public interface ClientSurveySubmissionEntityDao {
	
	ClientSurveySubmissionEntity createClientSubmissionEntity(ClientSurveySubmissionEntity  clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity updateClientSubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	void deleteClientSubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity);
	ClientSurveySubmissionEntity getClientSubmissionEntityById(UUID clientSubmissionEntityId);
	  

}
