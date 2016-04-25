package com.hserv.coordinatedentry.controller;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.repository.SurveyRepository;
import com.hserv.coordinatedentry.service.SurveyHandlerService;
import com.hserv.coordinatedentry.util.ResponseMessage;
import com.hserv.coordinatedentry.util.WSResponse;
import com.hserv.coordinatedentry.view.SurveyView;


@RestController
@RequestMapping("/surveys")
public class SurveyRESTImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(SurveyRESTImpl.class);
	
	private SurveyHandlerService surveyHandlerService;

	@Autowired
	public SurveyRESTImpl(SurveyHandlerService surveyHandlerService) {
		super();
		this.surveyHandlerService = surveyHandlerService;
	}

	@RequestMapping(method = RequestMethod.GET, value="/all")
	public @ResponseBody WSResponse getSurveyQuestions(){
		WSResponse wsRepsSts= null;
		List<Survey> surveys = null;

		try{
			wsRepsSts = new WSResponse();
			surveys = surveyHandlerService.getSurveyList();
			wsRepsSts.setStatusCode("200");
			wsRepsSts.setStatus("Success");
			wsRepsSts.setData(surveys);
		}catch(Exception e){
			wsRepsSts.setStatus("Failure");
			wsRepsSts.setErroMessage("Something Wrong in getSurveyQuestions API"+e.getMessage());
		}
		return wsRepsSts;
		
		
	}

	@RequestMapping(method = RequestMethod.GET, value="/{surveyId}")
	public @ResponseBody WSResponse getSelectedSurvey(@PathVariable Integer surveyId){
		WSResponse wsRepsSts= null;
		Survey survey = null;

		try{
			wsRepsSts = new WSResponse();
			survey = surveyHandlerService.getSurveyById(surveyId);
			if(survey != null) {
				wsRepsSts.setData(survey);
				wsRepsSts.setStatusCode("200");
				wsRepsSts.setStatusMessage("Success");
			} else {
				wsRepsSts.setData(null);
				wsRepsSts.setStatusCode("500");
				wsRepsSts.setStatusMessage("Failure - No survey exists for survey Id " +surveyId);				
			}
		}catch(Exception e){
			wsRepsSts.setErroMessage("Something Wrong in fetching Servey"+e.getMessage());
		}

		return wsRepsSts;

	}

	// /service/secure/survey/create - POST
	@RequestMapping(method = RequestMethod.POST, value="/create")
	public @ResponseBody WSResponse createSurvey(@RequestBody SurveyView surveyView){
		logger.debug("Creating new Survey...............");
		WSResponse wsResponse = null;

		ResponseMessage response = null;

		try{
			wsResponse = new WSResponse();
			response = surveyHandlerService.createSurvey(surveyView);
			wsResponse.setData(response.name());
			wsResponse.setStatusCode("200");
			wsResponse.setStatus("Success");

		}catch(Exception e){
			e.printStackTrace();
			wsResponse.setErroMessage("Something Wrong in createSurvey API"+e.getMessage());
		}

		return wsResponse;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{surveyId}")
	public @ResponseBody WSResponse updateSurvey(@PathVariable("surveyId") String surveyId, @RequestBody SurveyView surveyView){
		WSResponse wsResponse = null;
		System.out.println("update called : "+ surveyId);
		try{
			System.out.println(surveyView.getSurveyId());
			wsResponse = new WSResponse();
			surveyHandlerService.updateSurvey(surveyView);
			wsResponse.setStatusCode("200");
			wsResponse.setStatusMessage("Success");

		}catch(Exception e){
			wsResponse.setErroMessage("Something Wrong in updateSurvey API"+e.getMessage());
		}

		return wsResponse;

	}


	@RequestMapping(method = RequestMethod.DELETE, value="/{surveyId}")
	public @ResponseBody WSResponse deleteSurvey(@PathVariable("surveyId") Integer surveyId){
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

	/*@RequestMapping("service/survey/byTitle")
	public @ResponseBody List<Survey> getSurveyByTitle(@Param ("title") String title){
		List response = surveyRepository.findBySurveyTitleContainingAllIgnoringCase(title);
		return response;

	}*/

}
