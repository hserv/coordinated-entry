package com.hserv.coordinatedentry.housingmatching.external;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface SurveyMSService {

	List<SurveySectionModel> fetchSurveyResponse(String clientId);

/*	ClientsSurveyScores fetchSurveyResponse(Session session);*/
	
	
	ClientsSurveyScores fetchSurveyResponses(String projectGroup,UUID clientId);
	
	LocalDateTime getSurveyDate(UUID clientId, UUID surveyId);
	
	
	LocalDateTime getSurveyScoreDate(UUID clientId, UUID surveyId);
}
