package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.core.model.Surveys;
public interface SurveyService {

   Survey createSurvey(Survey survey,String caller);
   Survey updateSurvey(Survey survey,String caller);
   Survey deleteSurvey(UUID surveyId,String caller);
   Survey getSurveyById(UUID surveyId);
   Surveys getAllSurveys(Integer startIndex, Integer maxItems);
}
