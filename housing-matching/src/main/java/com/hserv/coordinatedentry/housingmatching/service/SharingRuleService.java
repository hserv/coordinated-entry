package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.UUID;

public interface SharingRuleService {

	List<UUID> getSharedEnrollments(List<UUID> projects,String schemaYear);
	List<UUID> getSharedProjects();
	List<UUID> getSharedClients(List<UUID> enrollments,String schemaYear);
	
}