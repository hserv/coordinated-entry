package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.dao.SurveyResponseRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.entity.SurveySection;
import com.hserv.coordinatedentry.housingmatching.model.SurveyScoreModel;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
import com.hserv.coordinatedentry.housingmatching.translator.SurveyScoreTranslator;

@Service
public class SurveyScoreServiceImpl implements SurveyScoreService {

	@Autowired
	SurveyScoreTranslator surveyScoreTranslator;

	@Autowired
	EligibleClientsRepository eligibleClientsRepository;
	
	@Autowired
	SurveyResponseRepository surveyResponseRepository;

	@Override
	public List<SurveyScoreModel> getScores() {
		List<SurveySection> surveySections = surveyResponseRepository.findAll();
		return surveyScoreTranslator.translate(eligibleClientsRepository.findAll());
	}

	@Override
	public boolean deleteScores() {
		eligibleClientsRepository.deleteScores();
		return true;
	}

	@Override
	public int getScoreByClientId(String clientId) {
		if (clientId != null && !clientId.isEmpty() && eligibleClientsRepository.exists(UUID.fromString(clientId))) {
			EligibleClients eligibleClients = eligibleClientsRepository.findByClientId(UUID.fromString(clientId));
			return eligibleClients.getSurveyScore();
		}
		return 0;
	}

	@Override
	public boolean deleteScoreByClientId(String clientId) {
		if (clientId != null && !clientId.isEmpty() && eligibleClientsRepository.exists(UUID.fromString(clientId))) {
			eligibleClientsRepository.deleteScoreByClientId(clientId);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateScoreByClientId(int score, String clientId) {
		if (clientId != null && !clientId.isEmpty() && eligibleClientsRepository.exists(UUID.fromString(clientId))) {
			eligibleClientsRepository.updateScoreByClientId(score, UUID.fromString(clientId));
			return true;
		}
		return false;
	}

}
