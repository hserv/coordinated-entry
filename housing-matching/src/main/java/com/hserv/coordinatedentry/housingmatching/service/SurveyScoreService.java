package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

public interface SurveyScoreService {
	
	public void calculateScore();

	public List<EligibleClientModel> getScores();
	
	public boolean deleteScores();
	
	public int getScoreByClientId(UUID clientId);
	
	public boolean deleteScoreByClientId(UUID clientId);
	
	public boolean updateScoreByClientId(int score, UUID clientId);
	
}
