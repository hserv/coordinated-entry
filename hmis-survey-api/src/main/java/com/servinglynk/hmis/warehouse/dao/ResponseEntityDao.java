package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ResponseEntity;

import java.util.List;

public interface ResponseEntityDao {

   ResponseEntity createResponseEntity(ResponseEntity ResponseEntity);
   ResponseEntity updateResponseEntity(ResponseEntity ResponseEntity);
   void deleteResponseEntity(ResponseEntity ResponseEntity);
   ResponseEntity getResponseEntityById(UUID ResponseEntityId);
   List<ResponseEntity> getAllSurveyResponses(UUID surveyid,UUID sectionId, Integer startIndex, Integer maxItems);
   long getSurveyResponsesCount(UUID surveyid);
}
