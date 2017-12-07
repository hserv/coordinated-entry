package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.ClientEntity;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;
import com.servinglynk.hmis.warehouse.service.ResponseServiceV3;
import com.servinglynk.hmis.warehouse.service.converter.ResponseConverterV3;
import com.servinglynk.hmis.warehouse.service.exception.QuestionNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.ResponseNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveySectionNotFoundException;


@Component
public class ResponseServiceImplV3 extends ServiceBase implements ResponseServiceV3  {

   @Transactional
   public Response createResponse(UUID surveyId,Responses responses,BaseClient client,String caller){
	   Response returnResponse = new Response();
	   
	   UUID submissionId = UUID.randomUUID();
	   
	   
	   SurveyEntity surveyEntity = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
	   if(surveyEntity==null) throw new SurveyNotFoundException();
	   
	   
	   for(Response response : responses.getResponses()){
	   
	   QuestionEntity questionEntity = daoFactory.getQuestionEntityDao().getQuestionEntityById(response.getQuestionId());
	   if(questionEntity==null) throw new QuestionNotFoundException();
	   
	   SurveySectionEntity sectionEntity = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(response.getSectionId());
	   if(sectionEntity==null) throw new SurveySectionNotFoundException();
	   
       ResponseEntity pResponse = ResponseConverterV3.modelToEntity(response, null);
       pResponse.setSurveyEntity(surveyEntity);
       pResponse.setSurveySectionEntity(sectionEntity);
       pResponse.setQuestionEntity(questionEntity);
       pResponse.setCreatedAt(LocalDateTime.now());
       pResponse.setUser(caller);
       pResponse.setClientId(client.getClientId());
       pResponse.setSubmissionId(submissionId);
       pResponse.setClientLink(client.getLink());
       pResponse.setDedupClientId(client.getDedupClientId());
//       pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(questionEntity, response.getResponseText()));
       daoFactory.getResponseEntityDao().createResponseEntity(pResponse);
    //   pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(questionEntity, pResponse));
//       daoFactory.getResponseEntityDao().updateResponseEntity(pResponse);
	   }
	   
	   returnResponse.setSubmissionId(submissionId);
       return returnResponse;
   }


   @Transactional
   public Response updateResponse(Response response,String caller){
       ResponseEntity pResponse = daoFactory.getResponseEntityDao().getResponseEntityById(response.getResponseId());
       if(pResponse==null) throw new ResponseNotFoundException();

       ResponseConverterV3.modelToEntity(response, pResponse);
       pResponse.setUpdatedAt(LocalDateTime.now());
       pResponse.setUser(caller);
   //    pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(pResponse.getQuestionEntity(), pResponse));
       daoFactory.getResponseEntityDao().updateResponseEntity(pResponse);
       response.setResponseId(pResponse.getId());
//	   serviceFactory.getSectionScoreService().updateSectionScores(pResponse.getClientId(), pResponse.getSurveyEntity().getId(), null,caller);
       return response;
   }
   
   @Transactional
   public void updateResponsesBySubmissionId(UUID submissionId,Responses responses,String caller){

	   for(Response response : responses.getResponses()){
		   ResponseEntity entity = daoFactory.getResponseEntityDao().getResponseBySubmission(submissionId,response.getResponseId());
		   if(entity!=null){
			   this.updateResponse(response, caller);
		   }
	   }
   }
   
   @Transactional
   public void deleteResponsesBySubmissionId(UUID surveyId,UUID submissionId,String caller){
	   
	   List<ResponseEntity> responses = daoFactory.getResponseEntityDao().getAllSubmissionResponses(surveyId, submissionId, null, null);
	   for(ResponseEntity entity : responses){
	       daoFactory.getResponseEntityDao().deleteResponseEntity(entity);
	   }
   }

   @Transactional
   public Responses getResponsesBySubmissionId(UUID surveyId,UUID submissionId,Integer startIndex,Integer maxItems){
	
	   Responses responses = new Responses();
	   
	   List<ResponseEntity> entities = daoFactory.getResponseEntityDao().getAllSubmissionResponses(surveyId, submissionId, startIndex, maxItems);
   
	   for(ResponseEntity entity:entities){
		   ClientEntity clientEntity = daoFactory.getClientDao().getClient(entity.getDedupClientId());
		   responses.addResponse(ResponseConverterV3.entityToModel(entity,clientEntity));
	   }
	   
       long count = daoFactory.getResponseEntityDao().getSubmissionResponsesCount(surveyId,submissionId);
       SortedPagination pagination = new SortedPagination();

       pagination.setFrom(startIndex);
       pagination.setReturned(responses.getResponses().size());
       pagination.setTotal((int)count);
       responses.setPagination(pagination);
       return responses; 

   }

   @Transactional
   public Response deleteResponse(UUID ResponseId,String caller){
       ResponseEntity pResponse = daoFactory.getResponseEntityDao().getResponseEntityById(ResponseId);
       if(pResponse==null) throw new ResponseNotFoundException();

       pResponse.setUser(caller);
       daoFactory.getResponseEntityDao().deleteResponseEntity(pResponse);
       return new Response();
   }


   @Transactional
   public Response getResponseById(UUID ResponseId,String version){
       ResponseEntity pResponse = daoFactory.getResponseEntityDao().getResponseEntityById(ResponseId);
       			ClientEntity clientEntity = daoFactory.getClientDao().getClient(pResponse.getDedupClientId());
   			return ResponseConverterV3.entityToModelDetail(pResponse,clientEntity);
   }


   @Transactional
   public Responses getAllSurveyResponses(UUID surveyid, Integer startIndex, Integer maxItems, String version){
       Responses Responses = new Responses();
        List<ResponseEntity> entities = daoFactory.getResponseEntityDao().getAllSurveyResponses(surveyid,null,startIndex,maxItems);
        for(ResponseEntity entity : entities){
        		ClientEntity clientEntity = daoFactory.getClientDao().getClient(entity.getDedupClientId());
        		Responses.addResponse(ResponseConverterV3.entityToModelDetail(entity,clientEntity));
        }
        long count = daoFactory.getResponseEntityDao().getSurveyResponsesCount(surveyid);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(Responses.getResponses().size());
        pagination.setTotal((int)count);
        Responses.setPagination(pagination);
        return Responses; 
   }
}