package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.model.MatchAlgorithmModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.AutoMatchAlgorithmService;

@Service
public class AutoMatchAlgorithmServiceImpl implements AutoMatchAlgorithmService {

	@Override
	public List<MatchReservationModel> execute(MatchAlgorithmModel algorithmModel) {
		// TODO apply auto-matching algo based on passing rules upon
		// eligibile projects, housing inventory, eligible clients and community preference.
		return null;
	}

}
