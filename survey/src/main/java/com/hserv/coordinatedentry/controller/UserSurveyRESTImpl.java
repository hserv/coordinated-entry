package com.hserv.coordinatedentry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.service.SurveyHandlerService;
import com.hserv.coordinatedentry.util.WSResponse;
import com.hserv.coordinatedentry.view.SurveyView;

@RestController
public class UserSurveyRESTImpl {
	
	@Autowired
	private SurveyHandlerService surveyHandlerService;
	
	/*9.fetchUserResponseBySurveyId(surveyId, userId) - /service/user/{userId}/survey/{surveyId} - GET
	10.updateUserResponse(response) -> User Role - /service/user/response/update - PUT
	11.saveUserResponse(response) - > User Role - /service/user/response/save - POST*/
	/**
	 * 
	 * @param userId
	 * @param serveyId
	 * @return
	 */
	@RequestMapping(value="/user/{userId}/survey/{surveyId}",method= RequestMethod.GET)
	@ResponseBody
	public WSResponse getServeyByUser(@PathVariable("userId") String userId, @PathVariable("surveyId")Integer surveyId){
		WSResponse wsResponse = null;
		SurveyView surveyViewObject = null;
		wsResponse = new WSResponse();
		wsResponse.setData(surveyHandlerService.getSurveyBySurveyIdAndUserId(surveyId,userId));
		if(surveyViewObject != null) {
			wsResponse.setData(surveyViewObject);
			wsResponse.setStatusCode("200");
			wsResponse.setStatusMessage("Success");
		} else {
			wsResponse.setData(null);
			wsResponse.setStatusCode("500");
			wsResponse.setStatusMessage("Failure - No survey exists for survey Id " + surveyId + " and User Id" + userId);				
		}
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
