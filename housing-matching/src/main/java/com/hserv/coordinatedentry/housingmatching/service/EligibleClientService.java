package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

public interface EligibleClientService {

	List<EligibleClientModel> getEligibleClients(int count);
}
