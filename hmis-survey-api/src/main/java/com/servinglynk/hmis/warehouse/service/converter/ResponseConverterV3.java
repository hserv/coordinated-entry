package com.servinglynk.hmis.warehouse.service.converter; 

import java.time.ZoneId;
import java.util.Date;

import com.servinglynk.hmis.warehouse.core.model.Client;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.model.ClientEntity;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
public class ResponseConverterV3 {

   public static ResponseEntity modelToEntity (Response model ,ResponseEntity entity) {
       if(entity==null) entity = new ResponseEntity();
       entity.setId(model.getResponseId());
       entity.setRefused(model.isRefused());
       entity.setResponseText(model.getResponseText());
       entity.setAppId(model.getAppId());
       entity.setEffectiveDate(model.getEffectiveDate());
       if(model.getDedupClientId()!=null) entity.setDedupClientId(model.getDedupClientId());
//       entity.setClientId(model.getClientId());
       return entity;    
   }


   public static Response entityToModel (ResponseEntity entity, ClientEntity clientEntity) {
       Response model = new Response();
       model.setResponseId(entity.getId());
       model.setRefused(entity.isRefused());
       model.setResponseText(entity.getResponseText());
       model.setQuestionScore(entity.getQuestionScore());
       model.setAppId(entity.getAppId());
       if(entity.getSurveySectionEntity()!=null) model.setSectionId(entity.getSurveySectionEntity().getId());
       if(entity.getQuestionEntity()!=null) model.setQuestionId(entity.getQuestionEntity().getId());
       model.setClientId(clientEntity.getId());
       model.setSurveyId(entity.getSurveyEntity().getId());
       model.setSubmissionId(entity.getSubmissionId());
       model.setEffectiveDate(entity.getEffectiveDate());
       model.setDedupClientId(entity.getDedupClientId());
       return model;
   }
   
   public static Response entityToModelDetail (ResponseEntity entity, ClientEntity clientEntity) {
       Response model = new Response();
       model.setResponseId(entity.getId());
       model.setRefused(entity.isRefused());
       model.setResponseText(entity.getResponseText());
       model.setQuestionScore(entity.getQuestionScore());
       model.setAppId(entity.getAppId());
       if(entity.getSurveySectionEntity()!=null) model.setSectionId(entity.getSurveySectionEntity().getId());
       if(entity.getQuestionEntity()!=null) model.setQuestionId(entity.getQuestionEntity().getId());
       model.setClientId(clientEntity.getId());
       model.setSurveyId(entity.getSurveyEntity().getId());
       model.setSubmissionId(entity.getSubmissionId());
       model.setEffectiveDate(entity.getEffectiveDate());
       model.setDedupClientId(clientEntity.getDedupClientId());
       if(entity.getClient()!=null) {
    	   Client client = new Client();
    	  if(clientEntity.getDob()!=null) client.setDob(Date.from(clientEntity.getDob().atZone(ZoneId.systemDefault()).toInstant()));
    	   client.setEmailAddress(clientEntity.getEmailAddress());
    	   client.setFirstName(clientEntity.getFirstName());
			client.setLastName(clientEntity.getLastName());
			client.setMiddleName(clientEntity.getMiddleName());
			client.setPhoneNumber(clientEntity.getPhoneNumber());
			client.setId(clientEntity.getId());
			model.setClient(client);
       }
       return model;
   }


}
