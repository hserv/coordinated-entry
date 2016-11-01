package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
public interface ResponseService {

   Response createResponse(UUID clientId,UUID surveyId,Responses response,String caller);
   Response updateResponse(Response Response,String caller);
   Response deleteResponse(UUID ResponseId,String caller);
   Response getResponseById(UUID ResponseId);
   Responses getAllSurveyResponses(UUID surveyid, Integer startIndex, Integer maxItems);
   
   void updateResponsesBySubmissionId(UUID submissionId,Responses responses,String caller);
   void deleteResponsesBySubmissionId(UUID surveyId,UUID submissionId,String caller);
   Responses getResponsesBySubmissionId(UUID surveyId,UUID submissionId,Integer startIndex,Integer maxItems);
}
