package com.servinglynk.hmis.warehouse.service.converter;


import java.time.ZoneId;
import java.util.Date;

import com.servinglynk.hmis.warehouse.core.model.Client;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

public class ClientSurveySubmissionConverter {

	
	public static ClientSurveySubmission entityToModel(ClientSurveySubmissionEntity entity) {
		ClientSurveySubmission model = new ClientSurveySubmission();

		model.setClientId(entity.getClientId().getId());

		model.setGlobalEnrollmentId(entity.getGlobalEnrollmentId());
		model.setId(entity.getId());
		model.setSubmissionId(entity.getSubmissionId());
		model.setSurveyId(entity.getSurveyId());
		
		if (entity.getClientId() != null) {
			Client client = new Client();
			if (entity.getClientId().getDob() != null)
				client.setDob(Date.from(entity.getClientId().getDob().atZone(ZoneId.systemDefault()).toInstant()));
			client.setEmailAddress(entity.getClientId().getEmailAddress());
			client.setFirstName(entity.getClientId().getFirstName());
			client.setLastName(entity.getClientId().getLastName());
			client.setMiddleName(entity.getClientId().getMiddleName());
			client.setPhoneNumber(entity.getClientId().getPhoneNumber());
			client.setId(entity.getClientId().getId());
			client.setDedupClientId(entity.getClientId().getDedupClientId());
			model.setClient(client);
			model.setClientLink("/hmis-clientapi/rest/v"+entity.getClientId().getSchemaYear()+"/clients/"+entity.getClientId().getId());
		}

		return model;
	}
}
