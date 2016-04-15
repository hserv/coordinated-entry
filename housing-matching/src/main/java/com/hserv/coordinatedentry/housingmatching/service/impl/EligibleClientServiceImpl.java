package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;

@Service
public class EligibleClientServiceImpl implements EligibleClientService {

	@Autowired
	EligibleClientsRepository eligibleClientsRepository;

	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;

	@Override
	public List<EligibleClientModel> getEligibleClients(int count) {
		List<EligibleClientModel> eligibleClientModels = new ArrayList<>();
		List<EligibleClients> eligibleClients = eligibleClientsRepository
				.findTopEligibleClients(new PageRequest(0, count, sort()));
		for (EligibleClients eligibleClient : eligibleClients) {
			eligibleClientModels.add(eligibleClientsTranslator.translate(eligibleClient));
		}

		return eligibleClientModels;
	}
	
	private Sort sort() {
		return sortBySurveyDate().and(sortByScore());
	}
	private Sort sortByScore() {
        return new Sort(Sort.Direction.DESC, "spdatscore");
    }
	
	private Sort sortBySurveyDate() {
        return new Sort(Sort.Direction.ASC, "surveyDate");
    }

}
