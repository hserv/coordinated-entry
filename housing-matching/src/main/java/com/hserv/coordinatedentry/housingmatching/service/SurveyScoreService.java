package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

public interface SurveyScoreService {
	
	public void calculateScore();

	public List<EligibleClientModel> getScores();
	
	public boolean deleteScores();
	
	public int getScoreByClientId(String clientId);
	
	public boolean deleteScoreByClientId(String clientId);
	
	public boolean updateScoreByClientId(int score, String clientId);
	
}
