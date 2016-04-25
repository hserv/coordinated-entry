package com.hserv.coordinatedentry.housingmatching.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.ClientInfo;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.entity.MatchReservations;
import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

@Component
public class EligibleClientsTranslator {

	@Autowired
	ClientInfoTranslator clientInfoTranslator;

	@Autowired
	MatchReservationTranslator matchReservationTranslator;

	public EligibleClientModel translate(EligibleClients eligibleClient) {
		EligibleClientModel eligibleClientModel = null;

		if (null != eligibleClient) {
			eligibleClientModel = new EligibleClientModel();
			eligibleClientModel.setProgramType(eligibleClient.getProgramType());
			eligibleClientModel.setClientId(eligibleClient.getClientId().toString());
			eligibleClientModel.setMatched(eligibleClient.getMatched());
			eligibleClientModel.setSpdatLabel(eligibleClient.getSpdatLabel());
			eligibleClientModel.setSurveyScore(eligibleClient.getSurveyScore());
			eligibleClientModel.setSurveyDate(eligibleClient.getSurveyDate());

			if (null != eligibleClient.getClientInfo()) {
				ClientInfoModel clientInfo = clientInfoTranslator.translate(eligibleClient.getClientInfo());
				eligibleClientModel.setClientInfo(clientInfo);
			}

			if (null != eligibleClient.getMatchReservationses() && !eligibleClient.getMatchReservationses().isEmpty()) {

				Set<MatchReservationModel> matchReservationModels = matchReservationTranslator
						.translate(eligibleClient.getMatchReservationses());

				eligibleClientModel.getMatchReservations().addAll(matchReservationModels);
			}
		}
		return eligibleClientModel;
	}

	public EligibleClients translate(EligibleClientModel eligibleClientModel) {
		EligibleClients eligibleClients = new EligibleClients();

		eligibleClients.setClientId(UUID.fromString(eligibleClientModel.getClientId()));
		eligibleClients.setProgramType(eligibleClientModel.getProgramType());
		eligibleClients.setMatched(eligibleClientModel.getMatched());
		eligibleClients.setSpdatLabel(eligibleClientModel.getSpdatLabel());
		eligibleClients.setSurveyScore(eligibleClientModel.getSurveyScore());
		eligibleClients.setSurveyDate(eligibleClientModel.getSurveyDate());

		if (null != eligibleClientModel.getClientInfo()) {
			ClientInfo clientInfo = clientInfoTranslator.translate(eligibleClientModel.getClientInfo());
			eligibleClients.setClientInfo(clientInfo);
		}

		if (null != eligibleClientModel.getMatchReservations()
				&& !eligibleClientModel.getMatchReservations().isEmpty()) {

			Set<MatchReservations> matchReservations = matchReservationTranslator
					.translates(eligibleClientModel.getMatchReservations());

			eligibleClients.getMatchReservationses().addAll(matchReservations);
		}
		return eligibleClients;
	}

}
