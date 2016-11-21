package com.hserv.coordinatedentry.housingmatching.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface SurveyScoreService {
	
	public void calculateScore(Session session) throws Exception ;

	public Page<EligibleClient> getScores(Pageable pageable);
	
	public boolean deleteScores();
	
	public int getScoreByClientId(UUID clientId);
	
	public boolean deleteScoreByClientId(UUID clientId);
	
	public boolean updateScoreByClientId(int score, UUID clientId);
	
	void checkAnyProcessRunning(String projectGroupCode);
	
}
