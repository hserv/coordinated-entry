package app.controller;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.repository.SurveyRepository;


@RestController
public class SurveyController {

  @Autowired
  private SurveyRepository surveyRepository;
  
  @RequestMapping(method = RequestMethod.GET, value="/service/survey/questions")
  public Map<String, Object> getSurveyQuestions(){
    
    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("Question1", "Which city do you live?");
    response.put("Question2", "What is your pet name?");
    return response;
  }
  
  
}
