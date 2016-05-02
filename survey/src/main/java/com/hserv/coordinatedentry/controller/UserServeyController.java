package com.hserv.coordinatedentry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.service.SurveyHandlerService;
import com.hserv.coordinatedentry.util.WSResponse;

@RestController
public class UserServeyController {
	
	@Autowired
	private SurveyHandlerService surveyHandlerService;
	/**
	 * 
	 * @param userId
	 * @param serveyId
	 * @return
	 */
	@RequestMapping(value="/service/user/{userId}/survey/{surveyId}",method= RequestMethod.GET)
	@ResponseBody
	public WSResponse getServeyByUser(@PathVariable("userId") String userId,@PathVariable("surveyId")Integer serveyId){
		
		
		
		WSResponse wsResponse=new WSResponse();
		//wsResponse.setData(surveyHandlerService.getSurveyByUserId(userId, serveyId));
		return wsResponse;
	}
/*	*//**
	 * 
	 * @return
	 *//*
	@RequestMapping(value="/service/user/response/update",method= RequestMethod.PUT)
	@ResponseBody
	public WSResponse updateUserResponse(){
		
		
		
		
		return null;
	}
	
	*//**
	 * 
	 * @return
	 *//*
	@RequestMapping(value="/service/user/response/save",method= RequestMethod.POST)
	@ResponseBody
	public WSResponse saveUserResponse(){
		
		
		
		
		return null;
	}*/
}
