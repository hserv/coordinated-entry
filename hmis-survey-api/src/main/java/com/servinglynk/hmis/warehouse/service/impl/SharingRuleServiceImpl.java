package com.servinglynk.hmis.warehouse.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.common.security.LoggedInUser;
import com.servinglynk.hmis.warehouse.model.GlobalProjectMapEntity;
import com.servinglynk.hmis.warehouse.model.SharingRuleEntity;
import com.servinglynk.hmis.warehouse.service.SharingRuleService;

@Component
public class SharingRuleServiceImpl extends ServiceBase implements SharingRuleService {
	
	
	@Transactional
	public List<UUID> getSharedEnrollments(List<UUID> projects,String schemaYear) {
		List<UUID> returnEnrollments = new ArrayList<UUID>();			
			if(!projects.isEmpty()) {
				returnEnrollments = daoFactory.getSharingRuleDao().getSharedEnrollments(projects, schemaYear);
			}
		return returnEnrollments;
	}
	
	@Transactional
	public List<UUID> getSharedProjects() {
		Set<UUID> projects = new HashSet<UUID>();
		
		SecurityContext context =  SecurityContextHolder.getContext();
		Authentication authentication =  context.getAuthentication();

		if(authentication.getPrincipal()!=null){
			LoggedInUser entity = (LoggedInUser) authentication.getPrincipal();
			List<SharingRuleEntity> sharingRules = daoFactory.getSharingRuleDao().getSharingRules(entity.getProfileId(), entity.getUserId());
			
			
			for(SharingRuleEntity sharingRuleEntity : sharingRules ) {
				List<GlobalProjectMapEntity> projectMaps =	sharingRuleEntity.getGlobalProjectEntity().getProjects();
				for(GlobalProjectMapEntity projectMapEntity : projectMaps) {
					projects.add(projectMapEntity.getProjectId());
				}
			}
			
		}
		return new ArrayList<UUID>(projects);
	}

	
	@Transactional
	public List<UUID> getSharedClients(List<UUID> enrollments,String schemaYear){
		return daoFactory.getSharingRuleDao().getSharedClients(enrollments, schemaYear);
	}
}