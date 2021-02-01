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
		ZoneId defaultZoneId = ZoneId.systemDefault();
		model.setClientId(entity.getClientId().getId());

		model.setGlobalEnrollmentId(entity.getGlobalEnrollmentId());
		model.setId(entity.getId());
		model.setSubmissionId(entity.getSubmissionId());
		model.setSubmissionDate(entity.getSubmissionDate());

		model.setSurveyCategory(entity.getSurveyCategory());
		model.setHmisLink(entity.getHmisLink());
		if(entity.getEntryDate() !=null) 
			model.setEntryDate(Date.from(entity.getEntryDate().atStartOfDay(defaultZoneId).toInstant()));
		if(entity.getExitDate() !=null)
			model.setExitDate(Date.from(entity.getExitDate().atStartOfDay(defaultZoneId).toInstant()));
		if(entity.getInformationDate() !=null)
			model.setInformationDate(Date.from(entity.getInformationDate().atStartOfDay(defaultZoneId).toInstant()));

		if(entity.getSurveyId()!=null) {
			model.setSurveyId(entity.getSurveyId().getId());
			model.setSurvey(SurveyConverter.entityToModel(entity.getSurveyId()));
		}


		
		model.setHmisPostingStatus(entity.getHmisPostStatus());
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
			entity.setEntryDate(clientSurveySubmission.getEntryDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
		if(clientSurveySubmission.getExitDate() != null)
			entity.setExitDate(clientSurveySubmission.getExitDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
		if(clientSurveySubmission.getGlobalEnrollmentId() != null)
			entity.setGlobalEnrollmentId(clientSurveySubmission.getGlobalEnrollmentId());
		if(clientSurveySubmission.getInformationDate() != null )
			entity.setInformationDate(clientSurveySubmission.getInformationDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
		entity.setHmisLink(clientSurveySubmission.getHmisLink());
		return entity;
	}
	
}
