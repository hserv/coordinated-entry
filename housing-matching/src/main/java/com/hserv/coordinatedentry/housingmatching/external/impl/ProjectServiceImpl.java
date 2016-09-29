package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hserv.coordinatedentry.housingmatching.external.ProjectService;
import com.hserv.coordinatedentry.housingmatching.model.Project;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Component("projectService")
public class ProjectServiceImpl implements ProjectService{
	
	@Value(value = "${PROJECT_REST_SERVICE_URL}")
	private String PROJECT_REST_SERVICE_URL;

	@Resource(name="restTemplateWithMapper")
	RestTemplate restTemplate;


	
	public Project getProjectInfo(UUID projectId,Session session, String trustedAppId){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-HMIS-TrustedApp-Id", trustedAppId);
		headers.add("Authorization","HMISUserAuth session_token="+session.getToken());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<Project> responseEntity = null;
		try{
				responseEntity = restTemplate.exchange(PROJECT_REST_SERVICE_URL+"/"+projectId, HttpMethod.GET,entity,Project.class);
				return responseEntity.getBody();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
