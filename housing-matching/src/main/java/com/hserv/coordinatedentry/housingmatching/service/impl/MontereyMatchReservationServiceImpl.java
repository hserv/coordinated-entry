package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.service.MontereyMatchReservationService;

@Service
public class MontereyMatchReservationServiceImpl implements MontereyMatchReservationService {
	
	@Autowired
	MatchReservationsService matchReservationService;

	@Override
	public void execute(List<MatchReservationModel> matchReservationModels) {
		// 7. check the county
		boolean isMonterey = isCountyMonterey(matchReservationModels);
		// if county is monterey, notify to ces_match_admin
		if (isMonterey) {
			notifyAdmin();
		} else {
			// if county is not monterey then insert into match_reservation.
			matchReservationService.createMatchReservation(matchReservationModels);
		}
	}
	
	private boolean isCountyMonterey(List<MatchReservationModel> matchReservationModels) {
		return false;
	}
	
	private void notifyAdmin() {
		// Send notification to ces_match_admin.
	}

}
