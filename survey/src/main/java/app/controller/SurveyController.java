package app.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Survey;
import app.repository.SurveyRepository;
import app.service.SurveyHandlerService;
import app.util.WSResponseStatus;
import app.view.SurveyView;


@RestController
public class SurveyController {

	private SurveyRepository surveyRepository;
	private SurveyHandlerService surveyHandlerService;

	@Autowired
	public SurveyController(SurveyRepository surveyRepository,
			SurveyHandlerService surveyHandlerService) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveyHandlerService = surveyHandlerService;
	}

	@RequestMapping(method = RequestMethod.GET, value="/surveys/all")
	public @ResponseBody WSResponseStatus getSurveyQuestions(){
		WSResponseStatus wsRepsSts= null;
		List<Survey> surveys = null;

		try{
			wsRepsSts = new WSResponseStatus();
			surveys = surveyHandlerService.getSurveyList();
			wsRepsSts.setStatusCode("200");
			wsRepsSts.setStatus("Success");
			wsRepsSts.setData(surveys);
		}catch(Exception e){
			wsRepsSts.setErroMessage("Something Wrong in getSurveyQuestions API"+e.getMessage());
		}
		return wsRepsSts;
	}

	@RequestMapping(method = RequestMethod.GET, value="/surveys/{surveyId}")
	public @ResponseBody WSResponseStatus getSelectedSurvey(@PathVariable Integer surveyId){
		WSResponseStatus wsRepsSts= null;
		Survey survey = null;

		try{
			wsRepsSts = new WSResponseStatus();
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
	public @ResponseBody WSResponseStatus createSurvey(@RequestBody Survey survey){
		WSResponseStatus wsResponseStatus = null;

		Survey survey1 = null;

		try{
			wsResponseStatus = new WSResponseStatus();
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
	public @ResponseBody WSResponseStatus updateSurvey(@PathParam("surveyId") String surveyId, @RequestBody SurveyView surveyView){
		WSResponseStatus wsResponseStatus = null;
		System.out.println("update called : "+ surveyId);
		try{
			System.out.println(surveyView.getSurveyId());
			wsResponseStatus = new WSResponseStatus();
			surveyHandlerService.updateSurvey(surveyView);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatusMessage("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in updateSurvey API"+e.getMessage());
		}

		return wsResponseStatus;

	}


	// /service/secure/survey/delete/{surveyId} - GET
	@RequestMapping(method = RequestMethod.GET, value="/service/survey/delete")
	public @ResponseBody WSResponseStatus deleteSurvey(@RequestParam (value="surveyId") Integer surveyId){
		WSResponseStatus wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponseStatus();
			surveyRepository.delete(surveyId);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something wrong in deleteSurvey API"+e.getMessage());
		}

		return wsResponseStatus;
	}

	@RequestMapping("service/survey/byTitle")
	public @ResponseBody List<Survey> getSurveyByTitle(@Param ("title") String title){
		List response = surveyRepository.findBySurveyTitleContainingAllIgnoringCase(title);
		return response;

	}

}
