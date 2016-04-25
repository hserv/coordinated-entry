package com.hserv.coordinatedentry.housingmatching.facade;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.task.ComputeScoreTask;

@Component
public class SurveyTotallingFacade {

	@Autowired
	private SurveyMSService surveyMSService;
	
	@Autowired
	private EligibleClientService eligibleClientService;

	public void updateClientScore(String clientId, DeferredResult<String> deferredResult) {
		Timer timer = new Timer();
		ComputeScoreTask task = new ComputeScoreTask(clientId, deferredResult, surveyMSService ,eligibleClientService);
		timer.schedule(task, 0);
	}
}
