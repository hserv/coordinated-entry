package com.servinglynk.hmis.warehouse.service.converter; 

import java.time.ZoneId;
import java.util.Date;

import com.servinglynk.hmis.warehouse.core.model.Client;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
public class ResponseConverter {

   public static ResponseEntity modelToEntity (Response model ,ResponseEntity entity) {
       if(entity==null) entity = new ResponseEntity();
       entity.setId(model.getResponseId());
       entity.setRefused(model.isRefused());
       entity.setResponseText(model.getResponseText());
       entity.setAppId(model.getAppId());
       entity.setPickListValueCode(model.getPickListValueCode());
       entity.setEffectiveDate(model.getEffectiveDate());
       entity.setHmisLink(model.getHmisLink());
       if(model.getDedupClientId()!=null) entity.setDedupClientId(model.getDedupClientId());
       
//       entity.setClientId(model.getClientId());
       return entity;    
   }


   public static Response entityToModel (ResponseEntity entity) {
       Response model = new Response();
       model.setResponseId(entity.getId());
       model.setRefused(entity.isRefused());
       model.setResponseText(entity.getResponseText());
       model.setPickListValueCode(entity.getPickListValueCode());
       model.setQuestionScore(entity.getQuestionScore());
       model.setAppId(entity.getAppId());
       if(entity.getSurveySectionEntity()!=null) model.setSectionId(entity.getSurveySectionEntity().getId());
       if(entity.getQuestionEntity()!=null) model.setQuestionId(entity.getQuestionEntity().getId());
       model.setClientId(entity.getClientId());
       model.setSurveyId(entity.getSurveyEntity().getId());
       model.setSubmissionId(entity.getSubmissionId());
       model.setEffectiveDate(entity.getEffectiveDate());
       model.setDedupClientId(entity.getDedupClientId());
       model.setHmisLink(entity.getHmisLink());
       return model;
   }
   
   public static Response entityToModelDetail (ResponseEntity entity) {
       Response model = new Response();
       model.setResponseId(entity.getId());
       model.setRefused(entity.isRefused());
       model.setResponseText(entity.getResponseText());
       model.setPickListValueCode(entity.getPickListValueCode());
       model.setQuestionScore(entity.getQuestionScore());
       model.setAppId(entity.getAppId());
       if(entity.getSurveySectionEntity()!=null) model.setSectionId(entity.getSurveySectionEntity().getId());
       if(entity.getQuestionEntity()!=null) model.setQuestionId(entity.getQuestionEntity().getId());
       model.setClientId(entity.getClientId());
       model.setSurveyId(entity.getSurveyEntity().getId());
       model.setSubmissionId(entity.getSubmissionId());
       model.setEffectiveDate(entity.getEffectiveDate());
       model.setDedupClientId(entity.getDedupClientId());
       model.setHmisLink(entity.getHmisLink());
       if(entity.getClient()!=null) {
    	   Client client = new Client();
    	   if(entity.getClient().getDob()!=null) client.setDob(Date.from(entity.getClient().getDob().atZone(ZoneId.systemDefault()).toInstant()));
    	   client.setEmailAddress(entity.getClient().getEmailAddress());
    	   client.setFirstName(entity.getClient().getFirstName());
			client.setLastName(entity.getClient().getLastName());
			client.setMiddleName(entity.getClient().getMiddleName());
			client.setPhoneNumber(entity.getClient().getPhoneNumber());
			client.setId(entity.getClient().getId());
			client.setDedupClientId(entity.getDedupClientId());
			model.setClient(client);
       }
       return model;
   }


}
