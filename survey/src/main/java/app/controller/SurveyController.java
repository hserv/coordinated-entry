package app.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

import app.model.Survey;
import app.repository.SurveyQuestionRepository;
import app.repository.SurveyRepository;


@RestController
public class SurveyController {

  @Autowired
  private SurveyRepository surveyRepository;
  @Autowired
  private SurveyQuestionRepository surveyQuestionRepository;
  
  
  @RequestMapping(method = RequestMethod.GET, value="/service/survey/questions")
  public @ResponseBody List<Survey> getSurveyQuestions(){
    
	List surveys = surveyRepository.findAll();
	//surveyQuestionRepository.find
    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("Question1", "Which city do you live?");
    response.put("Question2", "What is your pet name?");
    return surveys;
  }
  
  @RequestMapping("service/survey/byTitle")
  public @ResponseBody List<Survey> getSurveyByTitle(@Param ("title") String title){
	  List response = surveyRepository.findBySurveyTitleContainingAllIgnoringCase(title);
	  return response;
	  
  }
  
  
}
