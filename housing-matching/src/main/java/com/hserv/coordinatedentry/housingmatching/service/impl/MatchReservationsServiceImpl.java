package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.MatchReservationsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.MatchReservations;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;

@Service
public class MatchReservationsServiceImpl implements MatchReservationsService {
	
	@Autowired
	MatchReservationTranslator matchReservationTranslator;
	
	@Autowired
	MatchReservationsRepository repository;

	@Override
	public boolean createMatchReservation(MatchReservationModel matchReservationModel) {
		repository.saveAndFlush(matchReservationTranslator.translate(matchReservationModel));
		return true;
	}

	@Override
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels) {
		Set<MatchReservations> matchReservations = matchReservationTranslator.translates(new HashSet<MatchReservationModel>(matchReservationModels));
		for (MatchReservations matchReservation : matchReservations) {
			repository.saveAndFlush(matchReservation);
		}
		return true;
	}

	@Override
	public int updateMatchStatus(String matchStatus, String reservationId) {
		return repository.updateMatchStatus(matchStatus, UUID.fromString(reservationId));
	}

	@Override
	public int updateMatchStatusAndMAnualMatch(String matchStatus, boolean manualMatch, String reservationId) {
		return repository.updateMatchStatusAndManualMatch(matchStatus, manualMatch, UUID.fromString(reservationId));
	}
	
	
	
	
}
