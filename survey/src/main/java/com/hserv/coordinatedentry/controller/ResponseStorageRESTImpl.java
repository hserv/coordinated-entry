package com.hserv.coordinatedentry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.entity.ResponseStorage;
import com.hserv.coordinatedentry.service.ResponseStorageService;
import com.hserv.coordinatedentry.util.WSResponse;

@RestController
@RequestMapping("/response")
public class ResponseStorageRESTImpl {
	
	private @Autowired ResponseStorageService responseStorageService;
	

	@RequestMapping(method = RequestMethod.POST, value="/create")
	public @ResponseBody WSResponse createResponseStorage(@RequestBody ResponseStorage response){
		WSResponse wsResponseStatus = null;
		ResponseStorage response2 = null;

		try{
			wsResponseStatus = new WSResponse();
			response2 = responseStorageService.save(response);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(response2);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in saveUserResponse API"+e.getMessage());
		}

		return wsResponseStatus;

	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value="/update")
	public @ResponseBody WSResponse updateUserResponse(@RequestBody ResponseStorage response){
		WSResponse wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponse();

			responseStorageService.save(response);

			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in updateUserResponse"+e.getMessage());
		}

		return wsResponseStatus;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public @ResponseBody WSResponse getAllResponseStorage(){
		WSResponse wsResponseStatus = null;
		List<ResponseStorage> responseStorageList = new ArrayList<ResponseStorage>();
		try{
			wsResponseStatus = new WSResponse();
			responseStorageList = responseStorageService.findAll();
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(responseStorageList);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in getAlleResponseStorage API"+e.getMessage());
		}
		return wsResponseStatus;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/survey/{surveyId}")
	public @ResponseBody WSResponse getResponseStorageByClientId(@RequestParam("clientId") String clientId){
		WSResponse wsResponseStatus = null;
		List<ResponseStorage> responseStorageList = new ArrayList<ResponseStorage>();
		try{
			wsResponseStatus = new WSResponse();
			responseStorageList = responseStorageService
					.findByClientId(clientId);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(responseStorageList);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in getAlleResponseStorage API"+e.getMessage());
		}
		return wsResponseStatus;
	}

}
