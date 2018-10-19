package com.hserv.coordinatedentry.housingmatching.translator;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.enums.SpdatLabelEnum;
import com.hserv.coordinatedentry.housingmatching.model.ClientModel;
import com.hserv.coordinatedentry.housingmatching.model.CommunityType;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;
import com.hserv.coordinatedentry.housingmatching.service.impl.CommunityServiceLocator;

@Component
public class EligibleClientsTranslator {

/*	@Autowired
	MatchReservationTranslator matchReservationTranslator;*/
	
	@Autowired
	CommunityServiceLocator communityServiceLocator;

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
			eligibleClientModel.setLink(eligibleClient.getClientLink());
			eligibleClientModel.setDateCreated(eligibleClient.getDateCreated());
			eligibleClientModel.setDateUpdated(eligibleClient.getDateUpdated());
			eligibleClientModel.setIgnoreMatchProcess(eligibleClient.isIgnoreMatchProcess());
			eligibleClientModel.setRemarks(eligibleClient.getRemarks());
			eligibleClientModel.setClientDedupId(eligibleClient.getClientDedupId());
			eligibleClientModel.setSurveySubmissionDate(eligibleClient.getSurveySubmissionDate());
			eligibleClientModel.setSurveySubmissionDate(eligibleClient.getSurveySubmissionDate());
			eligibleClientModel.setBonusScore(eligibleClient.getBonusScore());
			eligibleClientModel.setTotalScore(eligibleClient.getTotalScore());
			eligibleClientModel.setReaddedReason(eligibleClient.getReaddedReason());
		}
		return eligibleClientModel;
	}
	
	public EligibleClientModel translateV2(EligibleClient eligibleClient) {
		EligibleClientModel eligibleClientModel = null;

		if (null != eligibleClient) {
			eligibleClientModel = new EligibleClientModel();
			eligibleClientModel.setProgramType(eligibleClient.getProgramType());
			eligibleClientModel.setClientId(eligibleClient.getClientId());
			eligibleClientModel.setMatched(eligibleClient.getMatched());
			eligibleClientModel.setSpdatLabel(SpdatLabelEnum.lookupEnum(eligibleClient.getSpdatLabel()));
			eligibleClientModel.setSurveyScore(eligibleClient.getSurveyScore());
			eligibleClientModel.setSurveyDate(eligibleClient.getSurveyDate());
			eligibleClientModel.setLink(eligibleClient.getClientLink());
			eligibleClientModel.setDateCreated(eligibleClient.getDateCreated());
			eligibleClientModel.setDateUpdated(eligibleClient.getDateUpdated());
			eligibleClientModel.setIgnoreMatchProcess(eligibleClient.isIgnoreMatchProcess());
			eligibleClientModel.setRemarks(eligibleClient.getRemarks());
			eligibleClientModel.setClientDedupId(eligibleClient.getClientDedupId());
			eligibleClientModel.setSurveySubmissionDate(eligibleClient.getSurveySubmissionDate());
			if(eligibleClient.getClient()!=null) {
				ClientModel clientModel = new ClientModel();
				clientModel.setId(eligibleClient.getClient().getId());
				if(eligibleClient.getClient().getDob()!=null) clientModel.setDob(Date.from( eligibleClient.getClient().getDob().atZone(ZoneId.systemDefault()).toInstant()));
				clientModel.setEmailAddress(eligibleClient.getClient().getEmailAddress());
				clientModel.setFirstName(eligibleClient.getClient().getFirstName());
				clientModel.setLastName(eligibleClient.getClient().getLastName());
				clientModel.setMiddleName(eligibleClient.getClient().getMiddleName());
				clientModel.setPhoneNumber(eligibleClient.getClient().getPhoneNumber());
				clientModel.setClientDedupId(eligibleClient.getClient().getDedupClientId());
				eligibleClientModel.setClient(clientModel);
			}
			eligibleClientModel.setBonusScore(eligibleClient.getBonusScore());
			eligibleClientModel.setTotalScore(eligibleClient.getTotalScore());
			eligibleClientModel.setReaddedReason(eligibleClient.getReaddedReason());
		}
		return eligibleClientModel;
	}

	public EligibleClient translate(EligibleClientModel eligibleClientModel,EligibleClient eligibleClient) {

		if(eligibleClient==null){
			 eligibleClient = new EligibleClient();
			 eligibleClient.setClientId(eligibleClientModel.getClientId());
		}

		eligibleClient.setRemarks(eligibleClientModel.getRemarks());
		eligibleClient.setProgramType(eligibleClientModel.getProgramType());
		eligibleClient.setMatched(eligibleClientModel.getMatched());
		eligibleClient.setSpdatLabel(eligibleClientModel.getSpdatLabel().getValue());
		eligibleClient.setSurveyScore(eligibleClientModel.getSurveyScore());
		eligibleClient.setSurveyDate(eligibleClientModel.getSurveyDate());
		eligibleClient.setZipCode(eligibleClientModel.getZipcode());
		eligibleClient.setSurveyType(eligibleClientModel.getSurveyType());
		eligibleClient.setClientLink(eligibleClientModel.getLink());
		eligibleClient.setIgnoreMatchProcess(eligibleClientModel.isIgnoreMatchProcess());
		eligibleClient.setClientDedupId(eligibleClientModel.getClientDedupId());
		MatchStrategy strategy = communityServiceLocator.locate(CommunityType.MONTEREY);
		int additionalScore = strategy.getAdditionalScore(19,eligibleClientModel.getSpdatLabel().getValue());
		eligibleClient.setCocScore(eligibleClientModel.getSurveyScore().intValue()+additionalScore);
		if(eligibleClientModel.getBonusScore()!=null) {
			eligibleClient.setBonusScore(eligibleClientModel.getBonusScore());
			eligibleClient.setTotalScore(eligibleClientModel.getSurveyScore().intValue()+eligibleClientModel.getBonusScore());
		}else {
			eligibleClient.setBonusScore(0);
			eligibleClient.setTotalScore(eligibleClientModel.getSurveyScore().intValue());
		}
		eligibleClient.setReaddedReason(eligibleClientModel.getReaddedReason());
		
		return eligibleClient;
	}

}
