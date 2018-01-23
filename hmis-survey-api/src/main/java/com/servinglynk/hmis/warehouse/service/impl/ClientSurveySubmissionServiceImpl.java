package com.servinglynk.hmis.warehouse.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.service.ClientSurveySubmissionService;


@Component
public class ClientSurveySubmissionServiceImpl extends ServiceBase implements ClientSurveySubmissionService {

	@Override
	@Transactional
	public ClientSurveySubmissionEntity createClientSurveySubmissionEntity (ClientSurveySubmissionEntity  clientSurveySubmissionEntity){
		
		
		daoFactory.getClientSubmissionEntityDao().createClientSubmissionEntity(clientSurveySubmissionEntity);
		 return clientSurveySubmissionEntity;
	}
	@Override
	@Transactional
	public void createClientSurveySubmissionEntity (List<ClientSurveySubmissionEntity>  clientSurveySubmissionEntities){
		
		clientSurveySubmissionEntities.stream().forEach((clientSurveySubmissionEntity) ->{
			
			createClientSurveySubmissionEntity (  clientSurveySubmissionEntity);
			logger.debug("created {}",clientSurveySubmissionEntity);
		});
			

		
	
	}
	
	
	@Transactional
	public ClientSurveySubmissionEntity updateClientSurveySubmissionEntity  (ClientSurveySubmissionEntity clientSurveySubmissionEntity) {
		daoFactory.getClientSubmissionEntityDao().updateClientSubmissionEntity(clientSurveySubmissionEntity);
		return clientSurveySubmissionEntity;
	}
	
	@Transactional
	public ClientSurveySubmissionEntity getClientSurveySubmissionEntitybyId(UUID id) {
		return daoFactory.getClientSubmissionEntityDao().getClientSubmissionEntityById(id);
	}
	
}
;



/*
@Component
public class ResponseServiceImpl extends ServiceBase implements ResponseService  {

   @Transactional
   public Response createResponse(UUID clientId,UUID surveyId,Responses responses,BaseClient client,String caller){
	   Response returnResponse = new Response();
	   
	   UUID submissionId = UUID.randomUUID();
	   
	   
	   SurveyEntity surveyEntity = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
	   if(surveyEntity==null) throw new SurveyNotFoundException();
	   
	   
	   for(Response response : responses.getResponses()){
	   
	   QuestionEntity questionEntity = daoFactory.getQuestionEntityDao().getQuestionEntityById(response.getQuestionId());
	   if(questionEntity==null) throw new QuestionNotFoundException();
	   
	   SurveySectionEntity sectionEntity = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(response.getSectionId());
	   if(sectionEntity==null) throw new SurveySectionNotFoundException();
	   
       ResponseEntity pResponse = ResponseConverter.modelToEntity(response, null);
       pResponse.setSurveyEntity(surveyEntity);
       pResponse.setSurveySectionEntity(sectionEntity);
       pResponse.setQuestionEntity(questionEntity);
       pResponse.setCreatedAt(LocalDateTime.now());
       pResponse.setUser(caller);
       pResponse.setClientId(clientId);
       pResponse.setSubmissionId(submissionId);
       pResponse.setClientLink(client.getLink());
       pResponse.setDedupClientId(client.getDedupClientId());
//       pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(questionEntity, response.getResponseText()));
       daoFactory.getResponseEntityDao().createResponseEntity(pResponse);
    //   pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(questionEntity, pResponse));
//       daoFactory.getResponseEntityDao().updateResponseEntity(pResponse);
	   }
	*/