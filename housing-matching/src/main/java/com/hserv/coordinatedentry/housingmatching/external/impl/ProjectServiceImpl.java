package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.external.ProjectService;
import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseProject;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Component("projectService")
public class ProjectServiceImpl implements ProjectService{
		
	 @Autowired
	 ISearchServiceClient searchServiceClient;

	
	public BaseProject getProjectInfo(UUID projectId,Session session, String trustedAppId){
		
			Map<String,Object> searchParams = new HashMap<>();
			searchParams.put("q", projectId);
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.setSearchParams(searchParams);
			searchRequest.setSessionToken(session.getToken());
			searchRequest.setTrustedAppId(trustedAppId);
			searchRequest.setSearchEntity("projects");
			try {
				List<BaseProject> projects =  (List<BaseProject>) searchServiceClient.search(searchRequest);
				if(!projects.isEmpty()) return projects.get(0);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}	
	}
}