package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Surveysv2;
import com.servinglynk.hmis.warehouse.core.model.Surveyv2;
public interface SurveyServicev2 {

	Surveyv2 createSurvey(Surveyv2 survey,Session session);
	Surveyv2 updateSurvey(Surveyv2 survey,String caller);
	Surveyv2 deleteSurvey(UUID surveyId,String caller);
	Surveyv2 getSurveyById(UUID surveyId);
	Surveysv2 getAllSurveys(Integer startIndex, Integer maxItems, String string);
}
