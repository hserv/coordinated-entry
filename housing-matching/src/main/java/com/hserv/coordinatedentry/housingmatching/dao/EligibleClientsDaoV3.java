package com.hserv.coordinatedentry.housingmatching.dao;

import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;

public interface EligibleClientsDaoV3 {

	EligibleClient getEligibleClients(UUID clientDedupId,String projectGroupCode);
	
	List<EligibleClient> getActiveEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients,Integer limit,Integer start);
	List<EligibleClient> getAllEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> clients, Integer limit,Integer start);
	List<EligibleClient> getInactiveEligibleClientsWithSharedClients(String projectGroupCode, List<UUID> clients,Integer limit,Integer start);
	Long getAllEligibleClientsCountWithSharedClients(String projectGroupCode, List<UUID> clients);	
	Long getInactiveEligibleClientsCountWithSharedClients(String projectGroupCode,  List<UUID> clients);
	Long getActiveEligibleClientsCountWithSharedClients(String projectGroupCode, List<UUID> clients);
	
}
