package com.hserv.coordinatedentry.housingmatching.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

public interface EligibleClientServiceV3 {
	
	
	Page<EligibleClient> getEligibleClients(String projectGroupCode, Pageable pageable, String filter);
	
	EligibleClientModel getEligibleClientDetail(UUID clientDedupId,String version);

}