package com.hserv.coordinatedentry.housinginventory.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hserv.coordinatedentry.housinginventory.annotation.APIMapping;
import com.servinglynk.hmis.warehouse.client.authorizationservice.IAuthorizationClient;
import com.servinglynk.hmis.warehouse.core.model.ApiMethodAuthorizationCheck;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;



/* This is CES an example of CES interceptor. It intercepts all the requests that the micro service receives and also calls HMIS 
Authorization interceptor (through Client SDK) for authorization purposes */
public class WebInterceptor extends HandlerInterceptorAdapter {
	
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	TrustedAppHelper trustedAppHelper;
	
	
	@Autowired
	private IAuthorizationClient client;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception{
		
		
		HandlerMethod handlerMethod = null;
		handlerMethod = (HandlerMethod) handler;
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);		
		if(apiMapping!=null) {
			if(apiMapping.value().equalsIgnoreCase("HEALTH_CHECK")) return true;
			
			ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
			apiMethodAuthorizationCheck.setApiMethodId(apiMapping.value());
			apiMethodAuthorizationCheck.setAccessToken(sessionHelper.retrieveSessionToken(request));
			apiMethodAuthorizationCheck.setTrustedAppId(trustedAppHelper.retrieveTrustedAppId(request));
			ApiMethodAuthorizationCheck clientresponse = client.checkApiAuthorization(apiMethodAuthorizationCheck);
			Session session = new Session();
			session.setAccount(clientresponse.getAccount());
			session.setToken(clientresponse.getAccessToken());
			this.sessionHelper.setSession(session, request);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
			return true;
		}else{
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
	}
}