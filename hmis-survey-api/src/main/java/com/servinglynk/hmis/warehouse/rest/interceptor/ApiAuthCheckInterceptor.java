package com.servinglynk.hmis.warehouse.rest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.client.authorizationservice.AuthorizationServiceClient;
import com.servinglynk.hmis.warehouse.core.model.ApiMethodAuthorizationCheck;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;

public class ApiAuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SessionHelper sessionHelper;
	
	@Autowired
	private TrustedAppHelper trustedAppHelper;
	
	@Autowired AuthorizationServiceClient client;
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = null;
		handlerMethod = (HandlerMethod) handler;
		
		String accessToken = this.sessionHelper.retrieveSessionToken(request);
		String trustedApp = this.trustedAppHelper.retrieveTrustedAppId(request);

		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		if(apiMapping!=null) {
			if(apiMapping.value().equalsIgnoreCase("HEALTH_CHECK")) return true;
			ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
			apiMethodAuthorizationCheck.setApiMethodId(apiMapping.value());
			apiMethodAuthorizationCheck.setAccessToken(accessToken);
			apiMethodAuthorizationCheck.setTrustedAppId(trustedApp);
			// client = new AuthorizationServiceClient();
			ApiMethodAuthorizationCheck clientresponse = client.checkApiAuthorization(apiMethodAuthorizationCheck);
		
			com.servinglynk.hmis.warehouse.core.model.Session session = new com.servinglynk.hmis.warehouse.core.model.Session();
			session.setToken(clientresponse.getAccessToken());
			session.setAccount(clientresponse.getAccount());
			session.setClientTypeId(clientresponse.getTrustedApp().getTrustedAppId());
			this.sessionHelper.setSession(session, request);
			this.trustedAppHelper.setTrustedApp(clientresponse.getTrustedApp(), request);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
			return true;
		}else{
			
			return false;
		}
	}

}
