package com.hserv.coordinatedentry.housingmatching.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

@Component
public class EligibleClientsTranslator {

	@Autowired
	MatchReservationTranslator matchReservationTranslator;

	public EligibleClientModel translate(EligibleClient eligibleClient) {
		EligibleClientModel eligibleClientModel = null;

		if (null != eligibleClient) {
			eligibleClientModel = new EligibleClientModel();
			eligibleClientModel.setProgramType(eligibleClient.getProgramType());
			eligibleClientModel.setClientId(eligibleClient.getClientId());
			eligibleClientModel.setMatched(eligibleClient.getMatched());
			eligibleClientModel.setSpdatLabel(eligibleClient.getSpdatLabel());
			eligibleClientModel.setSurveyScore(eligibleClient.getSurveyScore());
			eligibleClientModel.setSurveyDate(eligibleClient.getSurveyDate());

		}
		return eligibleClientModel;
	}

	public EligibleClient translate(EligibleClientModel eligibleClientModel) {
		EligibleClient eligibleClients = new EligibleClient();

		eligibleClients.setClientId(eligibleClientModel.getClientId());
		eligibleClients.setProgramType(eligibleClientModel.getProgramType());
		eligibleClients.setMatched(eligibleClientModel.getMatched());
		eligibleClients.setSpdatLabel(eligibleClientModel.getSpdatLabel());
		eligibleClients.setSurveyScore(eligibleClientModel.getSurveyScore());
		eligibleClients.setSurveyDate(eligibleClientModel.getSurveyDate());

		return eligibleClients;
	}

}
