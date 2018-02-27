package com.servinglynk.hmis.warehouse.service.converter;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public class ClientSurveySubmissionConverter {

	
	public static ClientSurveySubmission entityToModel(ClientSurveySubmissionEntity entity) {
		ClientSurveySubmission model = new ClientSurveySubmission();
		model.setClientId(entity.getClientId());
		model.setGlobalEnrollmentId(entity.getGlobalEnrollmentId());
		model.setId(entity.getId());
		model.setSubmissionId(entity.getSubmissionId());
		model.setSurveyId(entity.getSurveyId());
		return model;
	}
}
