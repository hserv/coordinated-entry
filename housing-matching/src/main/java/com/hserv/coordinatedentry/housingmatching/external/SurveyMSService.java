package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;

public interface SurveyMSService {

	public List<SurveyResponseModel> fetchServeyResponse();
}
