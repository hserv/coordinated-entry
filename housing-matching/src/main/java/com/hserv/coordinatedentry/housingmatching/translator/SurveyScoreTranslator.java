package com.hserv.coordinatedentry.housingmatching.translator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

@Component
public class SurveyScoreTranslator {
	
	@Autowired
	EligibleClientsTranslator eligibleClientsTranslator;
	
	public List<EligibleClientModel> translate(List<EligibleClient> eligibleClientsList) {
		List<EligibleClientModel> clientModels = null;

		if (eligibleClientsList != null && !eligibleClientsList.isEmpty()) {
			clientModels = new ArrayList<EligibleClientModel>();
			for (EligibleClient client : eligibleClientsList) {
				clientModels.add(translate(client));
			}
		}
		return clientModels;
	}
	
	public EligibleClient translate(EligibleClientModel clientModel) {
		EligibleClient eligibleClients = null;
		
		if (clientModel != null) {
			eligibleClients = new EligibleClient();
		} else {
			return null;
		}
		
		eligibleClients.setClientId(clientModel.getClientId());
		eligibleClients.setSurveyScore(clientModel.getSurveyScore());
		eligibleClients.setProgramType(clientModel.getProgramType());
		eligibleClients.setSpdatLabel(clientModel.getSpdatLabel());
		eligibleClients.setSurveyType(clientModel.getSurveyType());
		eligibleClients.setSurveyDate(clientModel.getSurveyDate());
		
		return eligibleClients;
	}
	
	public EligibleClientModel translate(EligibleClient eligibleClient) {
		EligibleClientModel clientModel = null;
		
		if (eligibleClient != null) {
			clientModel = new EligibleClientModel();
		} else {
			return null;
		}
		
		clientModel.setClientId(eligibleClient.getClientId());
		clientModel.setSurveyScore(eligibleClient.getSurveyScore());
		clientModel.setProgramType(eligibleClient.getProgramType());
		clientModel.setSpdatLabel(eligibleClient.getSpdatLabel());
		clientModel.setSurveyType(eligibleClient.getSurveyType());
		clientModel.setSurveyDate(eligibleClient.getSurveyDate());
		
		return clientModel;
	}

}
