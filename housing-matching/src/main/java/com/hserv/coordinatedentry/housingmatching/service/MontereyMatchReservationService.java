package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

public interface MontereyMatchReservationService {
	
	public void execute(List<MatchReservationModel> matchReservationModels);
	
}
