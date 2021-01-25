package com.hserv.coordinatedentry.housingmatching.translator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

@Component
public class MatchReservationTranslator {
	
	@Autowired
	EligibleClientsTranslator eligibleClientsTranslator;

	public Set<MatchReservationModel> translate(Set<Match> matchReservations) {
		Set<MatchReservationModel> matchReservationModelSet = null;

		if (null != matchReservations && !matchReservations.isEmpty()) {
			matchReservationModelSet = new HashSet<>();

			for (Match matchRerserve : matchReservations) {
				MatchReservationModel matchReservationModel = translate(matchRerserve);
				matchReservationModelSet.add(matchReservationModel);
			}

		}
		return matchReservationModelSet;
	}

	public Set<Match> translates(Set<MatchReservationModel> matchReservationsModels) {
		Set<Match> matchReservations = null;
		if (matchReservationsModels != null && !matchReservationsModels.isEmpty()) {
			matchReservations = new HashSet<>();
		} else {
			return null;
		}
		Iterator<MatchReservationModel> modelItr = matchReservationsModels.iterator();
		while (modelItr.hasNext()) {
			matchReservations.add(translate(modelItr.next()));
		}
		return matchReservations;
	}

	public MatchReservationModel translate(Match matchReserve) {
		
		MatchReservationModel matchReservationModel = new MatchReservationModel();
		matchReservationModel.setReservationId(matchReserve.getReservationId());
		matchReservationModel.setMatchStatus(matchReserve.getMatchStatus());
		matchReservationModel.setMatchDate(matchReserve.getMatchDate());
		matchReservationModel.setManualMatch(matchReserve.getManualMatch());
		matchReservationModel.setReservationAdults(matchReserve.getReservationAdults());
		matchReservationModel.setReservationChildren(matchReserve.getReservationChildren());
		matchReservationModel.setInactive(matchReserve.getInactive());
		matchReservationModel.setHousingUnitId(matchReserve.getHousingUnitId());
		matchReservationModel.setEligibleClients(eligibleClientsTranslator.translate(matchReserve.getEligibleClient()));
		matchReservationModel.setProcessId(matchReserve.getProcessId());
		matchReservationModel.setClientDedupId(matchReserve.getClientDedupId());
		return matchReservationModel;
	}

	public Match translate(MatchReservationModel matchRerserveModel) {
		Match match = null;
		if (matchRerserveModel != null) {
			match = new Match();
		} else {
			return null;
		}
			
		if(matchRerserveModel.getMatchDate()!=null) match.setMatchDate(matchRerserveModel.getMatchDate());
		
		if(matchRerserveModel.getReservationAdults()!=null) match.setReservationAdults(matchRerserveModel.getReservationAdults());
		if(matchRerserveModel.getReservationChildren()!=null) match.setReservationChildren(matchRerserveModel.getReservationChildren());
		return match;
	}

	public Match translate(MatchReservationModel matchRerserveModel,Match match) {
		if(matchRerserveModel.getMatchDate()!=null) match.setMatchDate(matchRerserveModel.getMatchDate());
		
		if(matchRerserveModel.getReservationAdults()!=null) match.setReservationAdults(matchRerserveModel.getReservationAdults());
		if(matchRerserveModel.getReservationChildren()!=null) match.setReservationChildren(matchRerserveModel.getReservationChildren());
		return match;
	}
	
	public Match translatev2(MatchReservationModel matchReservationModel) {
		Match match = null;
		if (matchReservationModel != null) {
			match = new Match();
		} else {
			return null;
		}

		match.setMatchStatus(matchReservationModel.getMatchStatus());
		match.setMatchDate(matchReservationModel.getMatchDate());
		match.setManualMatch(true);
		match.setReservationAdults(matchReservationModel.getReservationAdults());
		match.setReservationChildren(matchReservationModel.getReservationChildren());
		match.setInactive(false);
		return match;
	}

}
