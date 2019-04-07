package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.dao.SharingRuleDao;
import com.hserv.coordinatedentry.housingmatching.entity.GlobalProjectMapEntity;
import com.hserv.coordinatedentry.housingmatching.entity.SharingRuleEntity;
import com.hserv.coordinatedentry.housingmatching.service.SharingRuleService;
import com.servinglynk.hmis.warehouse.common.security.LoggedInUser;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Component
public class SharingRuleServiceImpl implements SharingRuleService {
	
	@Autowired
	private SharingRuleDao sharingRuleDao;
	
	
	@Transactional
	public List<UUID> getSharedEnrollments(List<UUID> projects,String schemaYear) {
		List<UUID> returnEnrollments = new ArrayList<UUID>();			
			if(!projects.isEmpty()) {
				returnEnrollments = sharingRuleDao.getSharedEnrollments(projects, schemaYear);
			}
		return returnEnrollments;
	}
	
	@Transactional
	public List<UUID> getSharedProjects() {
		Set<UUID> projects = new HashSet<UUID>();
		
		SecurityContext context =  SecurityContextHolder.getContext();
		Authentication authentication =  context.getAuthentication();

		if(authentication.getPrincipal()!=null){
			Session entity = (Session) authentication.getPrincipal();
			List<SharingRuleEntity> sharingRules = sharingRuleDao.getSharingRules(entity.getAccount().getProfile().getId(), entity.getAccount().getAccountId());
			
			
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
		return sharingRuleDao.getSharedClients(enrollments, schemaYear);
	}
}