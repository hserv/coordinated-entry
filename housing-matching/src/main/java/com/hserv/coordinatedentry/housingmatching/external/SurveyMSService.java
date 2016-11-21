package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface SurveyMSService {

	List<SurveySectionModel> fetchSurveyResponse(String clientId);

	ClientsSurveyScores fetchSurveyResponse(Session session);
	
	
	ClientsSurveyScores fetchSurveyResponses(String projectGroup);
}
