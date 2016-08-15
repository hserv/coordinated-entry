package com.hserv.coordinatedentry.housingmatching.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.enums.SpdatLabelEnum;
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
			eligibleClientModel.setSpdatLabel(SpdatLabelEnum.lookupEnum(eligibleClient.getSpdatLabel()));
			eligibleClientModel.setSurveyScore(eligibleClient.getSurveyScore());
			eligibleClientModel.setSurveyDate(eligibleClient.getSurveyDate());

		}
		return eligibleClientModel;
	}

	public EligibleClient translate(EligibleClientModel eligibleClientModel,EligibleClient eligibleClient) {

		if(eligibleClient==null){
			 eligibleClient = new EligibleClient();
			 eligibleClient.setClientId(eligibleClientModel.getClientId());
		}

		
		eligibleClient.setProgramType(eligibleClientModel.getProgramType());
		eligibleClient.setMatched(eligibleClientModel.getMatched());
		eligibleClient.setSpdatLabel(eligibleClientModel.getSpdatLabel().getValue());
		eligibleClient.setSurveyScore(eligibleClientModel.getSurveyScore());
		eligibleClient.setSurveyDate(eligibleClientModel.getSurveyDate());
		eligibleClient.setZipCode(eligibleClientModel.getZipcode());
		eligibleClient.setSurveyType(eligibleClientModel.getSurveyType());

		return eligibleClient;
	}

}
