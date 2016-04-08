package app.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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





import app.entity.Question;
import app.entity.ResponseStorage;
import app.entity.Survey;
import app.repository.QuestionBankRepository;
import app.repository.ResponseRepository;
import app.repository.SurveyQuestionRepository;
import app.repository.SurveyRepository;
import app.util.WSResponseStatus;


@RestController
public class SurveyController {

  @Autowired
  private SurveyRepository surveyRepository;
  @Autowired
  private SurveyQuestionRepository surveyQuestionRepository;
  @Autowired
  private QuestionBankRepository questionBankRepository;
  @Autowired
  private ResponseRepository responseRepository;
  
  
  @RequestMapping(method = RequestMethod.GET, value="/service/survey/questions")
  public @ResponseBody WSResponseStatus getSurveyQuestions(){
	WSResponseStatus wsRepsSts= null;
	List<Survey> surveys = null;
	
	try{
		wsRepsSts = new WSResponseStatus();
		 surveys = surveyRepository.findAll();
		//surveyQuestionRepository.find
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Question1", "Which city do you live?");
		response.put("Question2", "What is your pet name?");
		wsRepsSts.setStatusCode("200");
		wsRepsSts.setStatus("Success");
		wsRepsSts.setData(surveys);
	}catch(Exception e){
		wsRepsSts.setErroMessage("Something Wrong in getSurveyQuestions API"+e.getMessage());
	}
    return wsRepsSts;
  }
  
  @RequestMapping("service/survey/byTitle")
  public @ResponseBody List<Survey> getSurveyByTitle(@Param ("title") String title){
	  List response = surveyRepository.findBySurveyTitleContainingAllIgnoringCase(title);
	  return response;
	  
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/service/survey/{surveyId}")
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
  
  @RequestMapping(method = RequestMethod.PUT, value="/service/survey/update")
  public @ResponseBody WSResponseStatus updateSurvey(@PathParam("id") String id, @RequestBody Survey survey){
	  WSResponseStatus wsResponseStatus = null;
	  System.out.println("update called : "+ id);
	  try{
		  System.out.println(survey.getSurveyId());
		  wsResponseStatus = new WSResponseStatus();
		 // surveyRepository.save(survey);
		  wsResponseStatus.setStatusCode("200");
		  wsResponseStatus.setStatusMessage("Success");
		  
	  }catch(Exception e){
		  wsResponseStatus.setErroMessage("Something Wrong in updateSurvey API"+e.getMessage());
	  }
	  
	return wsResponseStatus;
	  
  }
  
 // /service/secure/question/update
  @RequestMapping(method = RequestMethod.PUT, value="/service/question/update")
  public @ResponseBody WSResponseStatus updateQuestion(@RequestBody Question question){
 WSResponseStatus wsResponseStatus = null;
	  
	  try{
		  wsResponseStatus = new WSResponseStatus();
		  questionBankRepository.save(question);
		  wsResponseStatus.setStatusCode("200");
		  wsResponseStatus.setStatusMessage("success");
		  
	  }catch(Exception e){
		  wsResponseStatus.setErroMessage("Something Wrong in UpdateQuestion API"+e.getMessage());
	  }
	  
	  return wsResponseStatus;
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
  
 ///service/secure/question/create
  @RequestMapping(method = RequestMethod.POST, value="/service/question/create")
  public @ResponseBody WSResponseStatus createQuestion(@RequestBody Question question){
	  WSResponseStatus wsResponseStatus = null;
	  
	  Question question1 = null;
	  
	  try{
		  wsResponseStatus = new WSResponseStatus();
		  question1 = questionBankRepository.save(question);
		  wsResponseStatus.setData(question1);
		  wsResponseStatus.setStatusCode("200");
		  wsResponseStatus.setStatus("Success");
	  }catch(Exception e){
		  wsResponseStatus.setErroMessage("Something Wrong in createQuestion API"+e.getMessage());
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
  
  // /service/secure/question/delete/{questionId}
  @RequestMapping(method = RequestMethod.GET, value="/service/question/delete")
  public @ResponseBody WSResponseStatus deleteQuestion(@RequestParam (value="questionId") Integer questionId){
	  WSResponseStatus wsResponseStatus = null;
	  
	  try{
		  wsResponseStatus = new WSResponseStatus();
		  questionBankRepository.delete(questionId);
		  wsResponseStatus.setStatusCode("200");
		  wsResponseStatus.setStatus("Success");
	  }catch(Exception e){
		  wsResponseStatus.setErroMessage("Something Wrong in deleteQuestionAPI"+e.getMessage());
	  }
	  
	  return wsResponseStatus;
  }
  
  
  // /service/user/response/update - PUT
  @RequestMapping(method = RequestMethod.PUT, value="/service/user/response/update")
  public @ResponseBody WSResponseStatus updateUserResponse(@RequestBody ResponseStorage response){
	  WSResponseStatus wsResponseStatus = null;
	  
	  try{
		  wsResponseStatus = new WSResponseStatus();
		  
		  responseRepository.save(response);
		  
		  wsResponseStatus.setStatusCode("200");
		  wsResponseStatus.setStatus("Success");
		  
	  }catch(Exception e){
		  wsResponseStatus.setErroMessage("Something Wrong in updateUserResponse"+e.getMessage());
	  }
	  
	  return wsResponseStatus;
  }
  
 
  // /service/user/response/save - POST
@RequestMapping(method = RequestMethod.POST, value="/service/user/response/save")
public @ResponseBody WSResponseStatus saveUserResponse(@RequestBody ResponseStorage response){
	WSResponseStatus wsResponseStatus = null;
	ResponseStorage response2 = null;
	
	try{
		wsResponseStatus = new WSResponseStatus();
		response2 = responseRepository.save(response);
		wsResponseStatus.setStatusCode("200");
		wsResponseStatus.setStatus("Success");
		wsResponseStatus.setData(response2);
	}catch(Exception e){
		wsResponseStatus.setErroMessage("Something Wrong in saveUserResponse API"+e.getMessage());
	}
	
	
	return wsResponseStatus;
	
	
}  
  
 }
