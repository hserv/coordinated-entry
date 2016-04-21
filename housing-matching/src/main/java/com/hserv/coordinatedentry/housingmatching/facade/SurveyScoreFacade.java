package com.hserv.coordinatedentry.housingmatching.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.model.SurveyScoreModel;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;

@Component
public class SurveyScoreFacade {

	@Autowired
	SurveyScoreService surveyScoreService;

	public List<SurveyScoreModel> getScores() {
		return surveyScoreService.getScores();
	}

	public boolean deleteScores() {
		return surveyScoreService.deleteScores();
	}

	public int getScoreByClientId(String clientId) {
		return surveyScoreService.getScoreByClientId(clientId);
	}

	public boolean deleteScoreByClientId(String clientId) {
		return surveyScoreService.deleteScoreByClientId(clientId);
	}

	public boolean updateScoreByClientId(int score, String clientId) {
		return surveyScoreService.updateScoreByClientId(score, clientId);
	}

}
