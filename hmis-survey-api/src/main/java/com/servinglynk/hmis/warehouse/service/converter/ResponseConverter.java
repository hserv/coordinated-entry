package com.servinglynk.hmis.warehouse.service.converter; 

import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
public class ResponseConverter {

   public static ResponseEntity modelToEntity (Response model ,ResponseEntity entity) {
       if(entity==null) entity = new ResponseEntity();
       entity.setId(model.getResponseId());
       entity.setResponseText(model.getResponseText());
       entity.setAppId(model.getAppId());
       entity.setClientId(model.getClientId());
       return entity;    
   }


   public static Response entityToModel (ResponseEntity entity) {
       Response model = new Response();
       model.setResponseId(entity.getId());
       model.setResponseText(entity.getResponseText());
       model.setQuestionScore(entity.getQuestionScore());
       model.setAppId(entity.getAppId());
       model.setSectionId(entity.getSurveySectionEntity().getId());
       model.setQuestionId(entity.getQuestionEntity().getId());
       model.setClientId(entity.getClientId());
       model.setSurveyId(entity.getSurveyEntity().getId());
       return model;
   }


}
