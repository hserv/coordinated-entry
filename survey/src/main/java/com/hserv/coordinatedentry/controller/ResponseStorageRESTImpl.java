package com.hserv.coordinatedentry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.annotation.APIMapping;
import com.hserv.coordinatedentry.entity.ResponseStorage;
import com.hserv.coordinatedentry.service.ResponseStorageService;
import com.hserv.coordinatedentry.util.WSResponse;

@RestController
@RequestMapping("/client")
public class ResponseStorageRESTImpl {
	
	private @Autowired ResponseStorageService responseStorageService;
	

	@RequestMapping(method = RequestMethod.POST, value="/{client_id}/responses")
	@APIMapping(value="SURVEY_API_CREATE_CLIENT_RESPONSE")
	public @ResponseBody WSResponse createResponseStorage(@PathVariable("client_id") String clientId, @RequestBody ResponseStorage response){
		WSResponse wsResponseStatus = null;
		ResponseStorage response2 = null;

		try{
			wsResponseStatus = new WSResponse();			
			response.setClientId(clientId);
			response2 = responseStorageService.save(response);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(response2);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in saveUserResponse API"+e.getMessage());
		}

		return wsResponseStatus;

	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value="/{client_id}/responses/{response_id}")
	@APIMapping(value="SURVEY_API_UPDATE_CLIENT_RESPONSE")
	public @ResponseBody WSResponse updateUserResponse(@PathVariable("client_id") String clientId, @PathVariable("response_id") Integer responseId, @RequestBody ResponseStorage response){
		WSResponse wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponse();
			response.setClientId(clientId);
			response.setResponseId(responseId);
			responseStorageService.save(response);

			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in updateUserResponse"+e.getMessage());
		}

		return wsResponseStatus;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{client_id}/responses")
	@APIMapping(value="SURVEY_API_GET_ALL_CLIENT_RESPONSE")
	public @ResponseBody WSResponse getAllResponseStorage(@PathVariable("client_id") String clientId){
		WSResponse wsResponseStatus = null;
		List<ResponseStorage> responseStorageList = new ArrayList<ResponseStorage>();
		try{
			wsResponseStatus = new WSResponse();
			responseStorageList = responseStorageService.findByClientId(clientId);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(responseStorageList);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in getAlleResponseStorage API"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{client_id}/responses/{response_id}/survey/{survey_id}")
	@APIMapping(value="SURVEY_API_GET_SURVEY_BY_CLIENT_ID_RESPONSE_ID_SURVEY_ID")
	public @ResponseBody WSResponse getResponseStorageByClientId(
				@PathVariable("client_id") String clientId,
				@PathVariable("response_id") Integer responseId,
				@PathVariable("survey_id") Integer surveyId){
		WSResponse wsResponseStatus = null;
		List<ResponseStorage> responseStorageList = new ArrayList<ResponseStorage>();
		try{
			wsResponseStatus = new WSResponse();
			responseStorageList = responseStorageService.findByClientIdAndSurveyId(clientId, responseId, surveyId);
			//responseStorageList = responseStorageService.findByClientIdAndSurveyId(clientId, surveyId);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(responseStorageList);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in getAlleResponseStorage API"+e.getMessage());
		}
		return wsResponseStatus;
	}

}
