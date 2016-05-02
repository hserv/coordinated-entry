package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.MatchAlgorithmModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

public interface AutoMatchService {
	
	public List<MatchReservationModel> execute(MatchAlgorithmModel algorithmModel);
}

