package com.hserv.coordinatedentry.housingmatching.translator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

@Component
public class MatchReservationTranslator {

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
		matchReservationModel.setReservationId(matchReserve.getReservationId().toString());
		matchReservationModel.setMatchStatus(matchReserve.getMatchStatus());
		matchReservationModel.setMatchDate(matchReserve.getMatchDate());
		matchReservationModel.setManualMatch(matchReserve.getManualMatch());
		matchReservationModel.setDateCreated(matchReserve.getDateCreated());
		matchReservationModel.setDateUpdated(matchReserve.getDateUpdated());
		matchReservationModel.setReservationAdults(matchReserve.getReservationAdults());
		matchReservationModel.setReservationChildren(matchReserve.getReservationChildren());
		matchReservationModel.setInactive(matchReserve.getInactive());
		matchReservationModel.setNoteId(matchReserve.getNoteId());
		return matchReservationModel;
	}

	public Match translate(MatchReservationModel matchRerserveModel) {
		Match matchReservations = null;
		if (matchRerserveModel != null) {
			matchReservations = new Match();
		} else {
			return null;
		}
		matchReservations.setReservationId(UUID.fromString(matchRerserveModel.getReservationId()));
		matchReservations.setMatchStatus(matchRerserveModel.getMatchStatus());
		matchReservations.setMatchDate(matchRerserveModel.getMatchDate());
		matchReservations.setManualMatch(matchRerserveModel.getManualMatch());
		matchReservations.setDateCreated(matchRerserveModel.getDateCreated());
		matchReservations.setDateUpdated(matchRerserveModel.getDateUpdated());
		matchReservations.setReservationAdults(matchRerserveModel.getReservationAdults());
		matchReservations.setReservationChildren(matchRerserveModel.getReservationChildren());
		matchReservations.setInactive(matchRerserveModel.getInactive());
		matchReservations.setNoteId(matchRerserveModel.getNoteId());
		return matchReservations;
	}

}
