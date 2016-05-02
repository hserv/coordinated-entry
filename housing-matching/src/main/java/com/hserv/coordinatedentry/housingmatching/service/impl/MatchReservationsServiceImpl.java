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
import com.hserv.coordinatedentry.housingmatching.service.NotificationService;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;

@Service
public class MatchReservationsServiceImpl implements MatchReservationsService {
	
	@Autowired
	MatchReservationTranslator matchReservationTranslator;
	
	@Autowired
	MatchReservationsRepository repository;
	
	@Autowired
	NotificationService notificationService;

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

	@Override
	public boolean deleteAll() {
		repository.deleteAll();	
		return true;
	}
	

	@Override
	public Set<MatchReservationModel> findAll() {
		return matchReservationTranslator.translate(new HashSet<MatchReservations>(repository.findAll()));
	}

	@Override
	public boolean deleteByClientId(String clientId) {
		if (repository.exists(UUID.fromString(clientId))) {
			repository.deleteByEligibleClientsClientId(UUID.fromString(clientId));
			return true;
		}
		repository.deleteByEligibleClientsClientId(UUID.fromString(clientId));
		return false;
	}

	@Override
	public MatchReservationModel findByClientId(String clientId) {
		if (repository.exists(UUID.fromString(clientId))) {
			return matchReservationTranslator.translate(repository.findByEligibleClientsClientId(UUID.fromString(clientId)));
		}
		return null;
	}

	@Override
	public boolean updateByClientId(String clientId, MatchReservationModel matchReservationModel) {
		MatchReservations matchReservations = matchReservationTranslator.translate(matchReservationModel);
		if (matchReservations.getEligibleClients() != null)
			matchReservations.getEligibleClients().setClientId(UUID.fromString(clientId));
		repository.saveAndFlush(matchReservations);
		
		sendNotification(matchReservationModel);
		return true;
	}

	private void sendNotification(MatchReservationModel matchReservationModel) {
		// if input value is approve/yes then only
		if ("approve".equalsIgnoreCase(matchReservationModel.getMatchStatus())
				|| "yes".equalsIgnoreCase(matchReservationModel.getMatchStatus())) {
			// call notification service to notify housing unit/user/client
			
			notificationService.notifyHousingUnit();
			notificationService.notifyUser();
			notificationService.notifyClient();
		}
	}
	
	
	
	
}
