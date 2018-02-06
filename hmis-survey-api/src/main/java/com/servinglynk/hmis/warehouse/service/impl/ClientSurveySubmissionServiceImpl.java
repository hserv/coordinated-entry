package com.servinglynk.hmis.warehouse.service.impl;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.service.ClientSurveySubmissionService;


@Component
public class ClientSurveySubmissionServiceImpl extends ServiceBase implements ClientSurveySubmissionService {
	
	@Transactional
	public List<ClientSurveySubmissionEntity> getClientSurveySubmissionEntitybyClientId(UUID clientId) {
		return daoFactory.getClientSubmissionEntityDao().getClientSurveySubmissionEntityByClienId(clientId);
			
	}
	

	@Transactional
	public ClientSurveySubmissionEntity getClientSurveySubmissionEntitybyId(UUID Id) {
		return daoFactory.getClientSubmissionEntityDao().getClientSurveySubmissionEntityById(Id);
			
	}
	
	
	@Transactional
	public ClientSurveySubmissionEntity getClientSurveySubmissionEntitybyClientSurveySubmission(UUID clientId,UUID surveyId,UUID submissionId) {
		return daoFactory.getClientSubmissionEntityDao().getClientSubmissionEntitybyClientSurveySubmission(clientId,surveyId,submissionId);
	}
	
	@Transactional
	public ClientSurveySubmissionEntity createClientSurveySubmissionEntity (ClientSurveySubmissionEntity  clientSurveySubmissionEntity){
		daoFactory.getClientSubmissionEntityDao().createClientSubmissionEntity(clientSurveySubmissionEntity);
		return clientSurveySubmissionEntity;
	}
	
	
	@Transactional
	public ClientSurveySubmissionEntity updateClientSurveySubmissionEntity  (ClientSurveySubmissionEntity clientSurveySubmissionEntity) {
		daoFactory.getClientSubmissionEntityDao().updateClientSubmissionEntity(clientSurveySubmissionEntity);
		return clientSurveySubmissionEntity;
	}
		
	
}




