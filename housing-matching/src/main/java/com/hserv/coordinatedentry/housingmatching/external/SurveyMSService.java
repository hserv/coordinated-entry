package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;

public interface SurveyMSService {

	List<SurveySectionModel> fetchSurveyResponse(String clientId);

	ClientsSurveyScores fetchSurveyResponse();
}
