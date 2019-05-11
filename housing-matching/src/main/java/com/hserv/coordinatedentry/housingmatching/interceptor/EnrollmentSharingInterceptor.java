package com.hserv.coordinatedentry.housingmatching.interceptor;

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

import com.hserv.coordinatedentry.housingmatching.service.SharingRuleService;
import com.hserv.coordinatedentry.housingmatching.service.impl.PropertyReaderServiceImpl;
import com.servinglynk.hmis.warehouse.core.model.Session;


public class EnrollmentSharingInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	SharingRuleService sharingRuleService;
	
	@Autowired protected Environment env;
	
	@Autowired private PropertyReaderServiceImpl propertyReaderServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if(request.getRequestURI().contains("/health")) return true;
		
		String value =	propertyReaderServiceImpl.readSharingRuleProperty();
		if(value==null || value.equalsIgnoreCase("false")) return true;

		
		
		
//		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("Inside sharing interceptor ");
			
			
		
			List<UUID> enrollments  =	new ArrayList<UUID>();
			List<UUID> clients		= new ArrayList<UUID>();
			
			List<UUID> projects = sharingRuleService.getSharedProjects();
				String schemas = "v2014,v2015,v2016,v2017";
				if(schemas!=null) {
					String[] schemaYears = schemas.split(",");
					for(String schemayear : schemaYears) {
						List<UUID> versionEnrollments = sharingRuleService.getSharedEnrollments(projects,schemayear);
						if(!versionEnrollments.isEmpty()) {
							List<UUID> versionClients = sharingRuleService.getSharedClients(versionEnrollments, schemayear);
							clients.addAll(versionClients);
						}
						enrollments.addAll(versionEnrollments);						
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
