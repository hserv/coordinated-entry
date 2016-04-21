package com.hserv.coordinatedentry.housingmatching.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.model.SurveyScoreModel;

@Component
public class SurveyScoreTranslator {
	
	@Autowired
	private ClientInfoTranslator clientInfoTranslator;
	
	public List<SurveyScoreModel> translate(List<EligibleClients> eligibleClientsList) {
		List<SurveyScoreModel> surveyScoreModels = null;

		if (eligibleClientsList != null && !eligibleClientsList.isEmpty()) {
			surveyScoreModels = new ArrayList<SurveyScoreModel>();
			for (EligibleClients client : eligibleClientsList) {
				surveyScoreModels.add(translate(client));
			}
		}
		return surveyScoreModels;
	}
	
	public EligibleClients translate(SurveyScoreModel scoreModel) {
		EligibleClients eligibleClients = null;
		
		if (scoreModel != null) {
			eligibleClients = new EligibleClients();
		} else {
			return null;
		}
		
		eligibleClients.setSurveyScore(scoreModel.getSurveyScore());
		if (scoreModel.getClientInfoModel() != null) {
			eligibleClients.setClientId(UUID.fromString(scoreModel.getClientInfoModel().getClientId()));
			eligibleClients.setClientInfo(clientInfoTranslator.translate(scoreModel.getClientInfoModel()));
		}
		
		return eligibleClients;
	}
	
	public SurveyScoreModel translate(EligibleClients eligibleClients) {
		SurveyScoreModel scoreModel = null;
		
		if (eligibleClients != null) {
			scoreModel = new SurveyScoreModel();
		} else {
			return null;
		}
		
		scoreModel.setSurveyScore(eligibleClients.getSurveyScore());
		scoreModel.setClientInfoModel(clientInfoTranslator.translate(eligibleClients.getClientInfo()));
		
		return scoreModel;
	}

}
