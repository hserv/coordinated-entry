package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SurveyEntity;

import java.util.List;

public interface SurveyDao {

	SurveyEntity createSurvey(SurveyEntity survey);
	SurveyEntity updateSurvey(SurveyEntity survey);
   void deleteSurvey(SurveyEntity survey);
   SurveyEntity getSurveyById(UUID surveyId);
   List<SurveyEntity> getAllSurveys(Integer startIndex, Integer maxItems);
   long getSurveysCount();
}
