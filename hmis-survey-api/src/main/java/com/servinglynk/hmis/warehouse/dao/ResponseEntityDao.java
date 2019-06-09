package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ResponseEntityDao {

   ResponseEntity createResponseEntity(ResponseEntity ResponseEntity);
   ResponseEntity updateResponseEntity(ResponseEntity ResponseEntity);
   void deleteResponseEntity(ResponseEntity ResponseEntity);
   ResponseEntity getResponseEntityById(UUID ResponseEntityId);
   List<ResponseEntity> getAllSurveyResponses(UUID surveyid,UUID sectionId, Integer startIndex, Integer maxItems);
   long getSurveyResponsesCount(UUID surveyid);
ResponseEntity getResponseBySubmission(UUID submissionId, UUID responseId);
List<ResponseEntity> getAllSubmissionResponses(UUID surveyId, UUID submissionId, Integer startIndex, Integer maxItems);
long getSubmissionResponsesCount(UUID surveyId, UUID submissionId);
ResponseEntity getResponseBySubmission(UUID submissionId);
LocalDateTime getSurveyDate(UUID clientId, UUID surveyId);
}
