package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.GlobalProjectMapEntity;
import com.hserv.coordinatedentry.housingmatching.external.ProjectService;
import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.projects.ProjectSearchClient;
import com.servinglynk.hmis.warehouse.core.model.BaseProject;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Component("projectService")
public class ProjectServiceImpl implements ProjectService{
		
	 @Autowired
	 ProjectSearchClient projectSearchClient;
	 
	 @Autowired RepositoryFactory repositoryFactory;

	
	public BaseProject getProjectInfo(UUID projectId,Session session, String trustedAppId){
		
		List<GlobalProjectMapEntity> entities = repositoryFactory.getGlobalProjectMapRepository().findByProjectIdAndProjectGroupCode(projectId, session.getAccount().getProjectGroup().getProjectGroupCode());
		
		if(!entities.isEmpty()) {
			
			Map<String,Object> searchParams = new HashMap<>();
			searchParams.put("schemayear", entities.get(0).getSource());
			searchParams.put("projectId", projectId);
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.setSearchParams(searchParams);
			searchRequest.setSessionToken(session.getToken());
			searchRequest.setTrustedAppId(trustedAppId);
			try {
				BaseProject project =   projectSearchClient.search(searchRequest);
				return project;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}	
		}
		return null;
	}
}