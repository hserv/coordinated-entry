package com.hserv.coordinatedentry.housingmatching.dao;

import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;

public interface EligibleClientsDaoV3 {

	EligibleClient getEligibleClients(UUID clientDedupId,String projectGroupCode);
}
