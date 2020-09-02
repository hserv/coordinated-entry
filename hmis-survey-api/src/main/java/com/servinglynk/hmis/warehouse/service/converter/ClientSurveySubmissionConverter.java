package com.servinglynk.hmis.warehouse.service.converter;


import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

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
		model.setSubmissionDate(entity.getSubmissionDate());
		if(entity.getSurveyId()!=null) {
			model.setSurveyId(entity.getSurveyId().getId());
			model.setSurvey(SurveyConverter.entityToModel(entity.getSurveyId()));
		}

		
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
	
	
	public static ClientSurveySubmissionEntity modelToEntity(ClientSurveySubmission clientSurveySubmission,ClientSurveySubmissionEntity entity) {

		if(StringUtils.isNotBlank(clientSurveySubmission.getHmisPostingStatus()))
			entity.setHmisPostStatus(clientSurveySubmission.getHmisPostingStatus());
		if(clientSurveySubmission.getGlobalEnrollmentId() != null)
			entity.setGlobalEnrollmentId(clientSurveySubmission.getGlobalEnrollmentId());
		if(clientSurveySubmission.getEntryDate() != null)
			entity.setEntryDate(clientSurveySubmission.getEntryDate().toLocalDate());
		if(clientSurveySubmission.getExitDate() != null)
			entity.setExitDate(clientSurveySubmission.getExitDate().toLocalDate());
		if(clientSurveySubmission.getGlobalEnrollmentId() != null)
			entity.setGlobalEnrollmentId(clientSurveySubmission.getGlobalEnrollmentId());
		if(clientSurveySubmission.getInformationDate() != null )
			entity.setInformationDate(clientSurveySubmission.getInformationDate().toLocalDate());

		return entity;
	}
	
}
