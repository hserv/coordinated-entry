package com.hserv.coordinatedentry.housingmatching.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;

public class SurveyTotallingFacade {
	
	@Autowired
	private SurveyMSService surveyMSService;

	
	public List<SurveyResponseModel> processServeyResponse() {
		
		//call survey MS (Use RestTemplate for calling)
		List<SurveyResponseModel> surveyResponseModels = surveyMSService.fetchServeyResponse();
		
		//total the score
		
		//find the category based upon score like TPH, RRH
		//set survey date to dto or entity object
		//add other criterion as well
		//persist to eligible_clients table so that Match Facade can use the top n clients from this table
		return surveyResponseModels;
	}
	
	public void totalScoreOnSurveyResponse(List<SurveyResponseModel> surveyResponseModels) {
		// perform totalling on survey response.
	}
}
