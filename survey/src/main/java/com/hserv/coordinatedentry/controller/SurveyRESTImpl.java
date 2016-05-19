package com.hserv.coordinatedentry.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.annotation.APIMapping;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.exception.InvalidArgumentException;
import com.hserv.coordinatedentry.exception.SurveyControllerException;
import com.hserv.coordinatedentry.exception.SurveyServiceException;
import com.hserv.coordinatedentry.service.SurveyHandlerService;
import com.hserv.coordinatedentry.util.ApplicationConstants;
import com.hserv.coordinatedentry.util.ResponseMessage;
import com.hserv.coordinatedentry.util.WSResponse;
import com.hserv.coordinatedentry.view.SurveyView;


@RestController
@RequestMapping("/surveys")
public class SurveyRESTImpl {

	private static final Logger logger = LoggerFactory.getLogger(SurveyRESTImpl.class);
	
	private SurveyHandlerService surveyHandlerService;

	@Autowired
	public SurveyRESTImpl(SurveyHandlerService surveyHandlerService) {
		super();
		this.surveyHandlerService = surveyHandlerService;
	}

	@RequestMapping(method = RequestMethod.GET, value="/")
	@APIMapping(value="SURVEY_API_GET_ALL_SURVEY")
	public @ResponseBody WSResponse getSurveyList(){
		WSResponse wsRepsSts= null;
		List<SurveyView> surveys = null;
		
		try{
			wsRepsSts = new WSResponse();
			surveys = surveyHandlerService.getSurveyList();
			wsRepsSts.setStatusCode("200");
			wsRepsSts.setStatus(ApplicationConstants.SUCCESS);
			wsRepsSts.setData(surveys);
		}catch(Exception e){
			wsRepsSts.setStatus(ApplicationConstants.FAILURE);
			wsRepsSts.setErroMessage("Something Wrong in getSurveyQuestions API"+e.getMessage());
		}
		return wsRepsSts;
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{survey_id}")
	@APIMapping(value="SURVEY_API_GET_SURVEY_BY_ID")
	public @ResponseBody WSResponse getSelectedSurvey(@PathVariable("survey_id") Integer surveyId){
		try{
			
			if (surveyId == null || surveyId < 1) {
				throw new InvalidArgumentException ("Invalid survey id");
			}
			
			Survey survey = surveyHandlerService.getSurveyById(surveyId);
			if(survey == null) {
				throw new SurveyControllerException("Survey does not exist");
			} 
			
			WSResponse wsRepsSts = new WSResponse();
			wsRepsSts.setData(survey);
			
			return wsRepsSts;
			
		}catch(InvalidArgumentException e){
			throw e;
		}catch(SurveyControllerException e){
			throw e;
		}catch(Exception e){
			throw new SurveyControllerException("Backend server error, please try again!");
		}
	}

	// /service/secure/survey/create - POST
	@RequestMapping(method = RequestMethod.POST, value="/")
	@APIMapping(value="SURVEY_API_CREATE_SURVEY")
	public @ResponseBody WSResponse createSurvey(@RequestBody @Validated SurveyView surveyView){
		logger.debug("Creating new Survey...............");
		
		WSResponse wsResponse = null;
		try{
			wsResponse = new WSResponse();
			ResponseMessage response = surveyHandlerService.createSurvey(surveyView);
			wsResponse.setData(response.name());
			wsResponse.setStatusCode("200");
			wsResponse.setStatus("Success");

		}catch(Exception e){
			logger.error("Error while creating new servey: ", e);
			wsResponse.setErroMessage("Something Wrong in createSurvey API"+e.getMessage());
		}

		return wsResponse;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{survey_id}")
	@APIMapping(value="SURVEY_API_UPDATE_SURVEY")
	public @ResponseBody WSResponse updateSurvey(@PathVariable("survey_id") Integer surveyId, @RequestBody @Validated SurveyView surveyView){
		WSResponse wsResponse = new WSResponse();;
		System.out.println("update called : "+ surveyId);
		try {
			if (surveyId == null || surveyId < 1) {
				throw new SurveyServiceException("Invalid survey id");
			}
			
			surveyView.setSurveyId(surveyId);
			System.out.println(surveyView.getSurveyId());
			
			ResponseMessage response = surveyHandlerService.updateSurvey(surveyView);
			if (ResponseMessage.FAILURE.equals(response)) {
				throw new SurveyServiceException("Backend server error, Please try again!");
			}
			
			wsResponse.setStatusCode("200");
			wsResponse.setStatusMessage(ApplicationConstants.SUCCESS);

		} catch (Exception e) {
			wsResponse.setStatusCode("500");
			wsResponse.setStatusMessage(ApplicationConstants.FAILURE);
			wsResponse.setErroMessage("Error occured while updating survey: " + e.getMessage());
		}

		return wsResponse;

	}

	@RequestMapping(method = RequestMethod.DELETE, value="/{survey_id}")
	@APIMapping(value="SURVEY_API_DELETE_SURVEY")
	public @ResponseBody WSResponse deleteSurvey(@PathVariable("survey_id") Integer surveyId){
		WSResponse wsResponse = null;
		System.out.println("surveyId ; "+surveyId);
		try{
			wsResponse = new WSResponse();
			ResponseMessage result = surveyHandlerService.deleteSurvey(surveyId);
			System.out.println("result : "+result);
			wsResponse.setStatusCode("200");
			wsResponse.setStatus("Success");
		}catch(Exception e){
			wsResponse.setErroMessage("Something wrong in deleteSurvey API"+e.getMessage());
		}
		//Response.status(200).entity(wsResponse).build();
		return wsResponse;
	}

	@RequestMapping(method = RequestMethod.GET, value="/allSurvey")	
	@APIMapping(value="SURVEY_API_GET_ALL_SURVEY_DETAIL")
	public @ResponseBody WSResponse getAllSurveDetails(){
		WSResponse wsRepsSts= null;
		List<Survey> surveys = null;

		try{
			wsRepsSts = new WSResponse();
			surveys = surveyHandlerService.getAllSurveyList();
			wsRepsSts.setStatusCode("200");
			wsRepsSts.setStatus("Success");
			wsRepsSts.setData(surveys);
		}catch(Exception e){
			wsRepsSts.setStatus("Failure");
			wsRepsSts.setErroMessage("Something Wrong in getSurveyQuestions API"+e.getMessage());
		}
		return wsRepsSts;
		
		
	}
	/*@RequestMapping("service/survey/byTitle")
	public @ResponseBody List<Survey> getSurveyByTitle(@Param ("title") String title){
		List response = surveyRepository.findBySurveyTitleContainingAllIgnoringCase(title);
		return response;

	}*/

}
