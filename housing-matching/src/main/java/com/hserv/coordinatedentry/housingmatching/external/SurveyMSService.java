package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;

public interface SurveyMSService {

	List<SurveySectionModel> fetchServeyResponse(String clientId);
}
