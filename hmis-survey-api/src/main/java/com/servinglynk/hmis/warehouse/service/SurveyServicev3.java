package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Surveysv3;
import com.servinglynk.hmis.warehouse.core.model.Surveyv3;
public interface SurveyServicev3 {

	Surveyv3 createSurvey(Surveyv3 survey,Session session);
	Surveyv3 updateSurvey(Surveyv3 survey,String caller);
	Surveyv3 deleteSurvey(UUID surveyId,String caller);
	Surveyv3 getSurveyById(UUID surveyId);
	Surveysv3 getAllSurveys(Integer startIndex, Integer maxItems, String string);
}
