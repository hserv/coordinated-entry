package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
public interface ResponseService {

   Responses createResponse(UUID clientId,UUID surveyId,Responses response,String caller);
   Response updateResponse(Response Response,String caller);
   Response deleteResponse(UUID ResponseId,String caller);
   Response getResponseById(UUID ResponseId);
   Responses getAllSurveyResponses(UUID surveyid, Integer startIndex, Integer maxItems);
}
