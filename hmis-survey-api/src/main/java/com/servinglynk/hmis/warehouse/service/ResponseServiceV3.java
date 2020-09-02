package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
public interface ResponseServiceV3 {

   Response createResponse(UUID surveyId,Responses responses,BaseClient client,String caller);
   Response updateResponse(Response Response,String caller);
   Response deleteResponse(UUID ResponseId,String caller);
   Response getResponseById(UUID ResponseId, String version);
   Responses getAllSurveyResponses(UUID surveyid, Integer startIndex, Integer maxItems, String version);
   Responses getAllSurveyResponsesByClientDedupId(UUID surveyid,UUID clientDedupId, Integer startIndex, Integer maxItems, String version);
   void updateResponsesBySubmissionId(UUID submissionId,Responses responses,String caller);
   void deleteResponsesBySubmissionId(UUID surveyId,UUID submissionId,String caller);
   Responses getResponsesBySubmissionId(UUID surveyId,UUID submissionId,Integer startIndex,Integer maxItems);
}
