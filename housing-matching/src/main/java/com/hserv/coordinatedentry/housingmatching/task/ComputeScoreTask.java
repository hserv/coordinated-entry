package com.hserv.coordinatedentry.housingmatching.task;

import java.util.List;
import java.util.TimerTask;
import java.util.UUID;

import org.springframework.web.context.request.async.DeferredResult;

import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;

public class ComputeScoreTask extends TimerTask {
	
	private EligibleClientService eligibleClientService;
	private SurveyMSService surveyMSService;
	private String clientId;
	private DeferredResult<String> deferredResult;

	public ComputeScoreTask(String clientId, DeferredResult<String> deferredResult,
			SurveyMSService surveyMSService ,EligibleClientService eligibleClientService) {
		this.clientId = clientId;
		this.deferredResult = deferredResult;
		this.surveyMSService = surveyMSService;
		this.eligibleClientService = eligibleClientService;
	}

	@Override
	public void run() {
		if (deferredResult.isSetOrExpired()) {
		} else {
			processSurveyResponse(clientId);
			deferredResult.setResult(new String("{\"triggered\": \"ok\"}\""));
		}
	}

	private List<SurveySectionModel> processSurveyResponse(String clientID) {
		
		//call survey MS (Use RestTemplate for calling)
		List<SurveySectionModel> surveyResponseModels = surveyMSService.fetchSurveyResponse(clientID);
		
		//total the score
		int scoreTotal = totalScoreOnSurveyResponse(surveyResponseModels);
		eligibleClientService.updateEligibleClientScore(UUID.fromString(clientID), scoreTotal);
		//find the category based upon score like TPH, RRH
		//set survey date to dto or entity object
		//add other criterion as well
		//persist to eligible_clients table so that Match Facade can use the top n clients from this table
		return surveyResponseModels;
	}
	
	public int totalScoreOnSurveyResponse(List<SurveySectionModel> surveyResponseModels) {
		int scoreTotal = 0;
		
		for(SurveySectionModel surveyResponseModel :surveyResponseModels){
			if(null != surveyResponseModel.getSectionScore()){
				scoreTotal = scoreTotal + surveyResponseModel.getSectionScore();
			}
		}
		return scoreTotal;
	}

}
