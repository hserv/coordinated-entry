package com.hserv.coordinatedentry.housingmatching.dao;

import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.entity.SharingRuleEntity;

public interface SharingRuleDao {
	
	List<SharingRuleEntity> getSharingRules(UUID profileId,UUID userId);
	
	List<UUID> getSharedEnrollments(List<UUID> projects,String schemaYear);
	
	List<UUID> getSharedClients(List<UUID> enrollments,String schemaYear);

}