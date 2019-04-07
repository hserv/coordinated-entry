package com.servinglynk.hmis.warehouse.rest.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.service.core.ParentServiceFactory;


public class EnrollmentSharingInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	ParentServiceFactory serviceFactory;
	
	@Autowired protected Environment env;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
//		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("Inside sharing interceptor ");
			
			System.out.println("Schema year is "+request.getContextPath().replaceAll("/hmis-clientapi-", ""));
			
			String schemaYear = request.getContextPath().replaceAll("/hmis-clientapi-", "");
			
			Boolean isClientSearch = request.getRequestURI().contains("/searchall/clients") ;
			
		
			List<UUID> enrollments  =	new ArrayList<UUID>();
			List<UUID> clients		= new ArrayList<UUID>();
			
			List<UUID> projects = serviceFactory.getSharingRuleService().getSharedProjects();
			if(!isClientSearch) {
				enrollments = serviceFactory.getSharingRuleService().getSharedEnrollments(projects,schemaYear);
				if(!enrollments.isEmpty()) clients = serviceFactory.getSharingRuleService().getSharedClients(enrollments, schemaYear);
			}else {
				String schemas = env.getProperty("client.api.schemas");
				if(schemas!=null) {
					String[] schemaYears = schemas.split(",");
					for(String schemayear : schemaYears) {
						List<UUID> versionEnrollments = serviceFactory.getSharingRuleService().getSharedEnrollments(projects,schemayear);
						if(!versionEnrollments.isEmpty()) {
							List<UUID> versionClients = serviceFactory.getSharingRuleService().getSharedClients(versionEnrollments, schemayear);
							clients.addAll(versionClients);
						}
						enrollments.addAll(versionEnrollments);						
					}
				}
			}
			System.out.println("Shared enrollments count "+enrollments.size()+" clients count "+clients.size());
			SecurityContext context =  SecurityContextHolder.getContext();
			Session account = (Session) context.getAuthentication().getPrincipal();
			account.setSharedClients(clients);
			account.setSharedEnrollments(enrollments);
			Authentication authentication = new UsernamePasswordAuthenticationToken(account,"");
			SecurityContextHolder.clearContext();
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	//	}
		return true;
	}

}
