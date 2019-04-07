package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SharingRuleEntity;

public interface SharingRuleDao {
	
	List<SharingRuleEntity> getSharingRules(UUID profileId,UUID userId);
	
	List<UUID> getSharedEnrollments(List<UUID> projects,String schemaYear);
	
	List<UUID> getSharedClients(List<UUID> enrollments,String schemaYear);

}