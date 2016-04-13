package com.hserv.coordinatedentry.controller;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

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
public class SurveyRESTImpl {

	private SurveyRepository surveyRepository;
	private SurveyHandlerService surveyHandlerService;

	@Autowired
	public SurveyRESTImpl(SurveyRepository surveyRepository,
			SurveyHandlerService surveyHandlerService) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveyHandlerService = surveyHandlerService;
	}

	@RequestMapping(method = RequestMethod.GET, value="/surveys/all")
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

	@RequestMapping(method = RequestMethod.GET, value="/surveys/{surveyId}")
	public @ResponseBody WSResponse getSelectedSurvey(@PathVariable Integer surveyId){
		WSResponse wsRepsSts= null;
		Survey survey = null;

		try{
			wsRepsSts = new WSResponse();
			survey = surveyRepository.findOne(surveyId);
			wsRepsSts.setData(survey);
			wsRepsSts.setStatusCode("200");
			wsRepsSts.setStatusMessage("Success");
		}catch(Exception e){
			wsRepsSts.setErroMessage("Something Wrong in fetching Servey"+e.getMessage());
		}

		return wsRepsSts;

	}

	// /service/secure/survey/create - POST
	@RequestMapping(method = RequestMethod.POST, value="/service/survey/create")
	public @ResponseBody WSResponse createSurvey(@RequestBody Survey survey){
		WSResponse wsResponseStatus = null;

		Survey survey1 = null;

		try{
			wsResponseStatus = new WSResponse();
			survey1 = surveyRepository.save(survey);
			wsResponseStatus.setData(survey1);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in createSurvey API"+e.getMessage());
		}

		return wsResponseStatus;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/surveys/{surveyId}/update")
	public @ResponseBody WSResponse updateSurvey(@PathParam("surveyId") String surveyId, @RequestBody SurveyView surveyView){
		WSResponse wsResponseStatus = null;
		System.out.println("update called : "+ surveyId);
		try{
			System.out.println(surveyView.getSurveyId());
			wsResponseStatus = new WSResponse();
			surveyHandlerService.updateSurvey(surveyView);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatusMessage("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in updateSurvey API"+e.getMessage());
		}

		return wsResponseStatus;

	}


	@RequestMapping(method = RequestMethod.DELETE, value="/surveys/{surveyId}")
	public @ResponseBody WSResponse deleteSurvey(@PathVariable("surveyId") Integer surveyId){
		WSResponse wsResponseStatus = null;
		System.out.println("surveyId ; "+surveyId);
		try{
			wsResponseStatus = new WSResponse();
			ResponseMessage result = surveyHandlerService.deleteSurvey(surveyId);
			System.out.println("result : "+result);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something wrong in deleteSurvey API"+e.getMessage());
		}
		//Response.status(200).entity(wsResponseStatus).build();
		return wsResponseStatus;
	}

	@RequestMapping("service/survey/byTitle")
	public @ResponseBody List<Survey> getSurveyByTitle(@Param ("title") String title){
		List response = surveyRepository.findBySurveyTitleContainingAllIgnoringCase(title);
		return response;

	}

}
