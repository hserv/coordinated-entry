package com.servinglynk.hmis.warehouse.rest.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.servinglynk.hmis.warehouse.client.clientconsent.ClientConsentCheckService;
import com.servinglynk.hmis.warehouse.core.model.ApiMethodAuthorizationCheck;
import com.servinglynk.hmis.warehouse.core.model.exception.AccessDeniedException;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;

public class ClientConsentInterceptor  extends HandlerInterceptorAdapter {
	
	
	@Autowired
	private SessionHelper sessionHelper;

	@Autowired
	private TrustedAppHelper trustedAppHelper;
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		Boolean flag = false;
		
		String accessToken = this.sessionHelper.retrieveSessionToken(request);
		String trustedApp = this.trustedAppHelper.retrieveTrustedAppId(request);
		
		Map<String,Object> pathVariables = (Map<String,Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Map<String,Object> map = new HashMap<String,Object>();
		
		for(String key : pathVariables.keySet()) {
			map.put(key.toUpperCase(), pathVariables.get(key));
		}
		
		ApiMethodAuthorizationCheck auth = new ApiMethodAuthorizationCheck();
		auth.setAccessToken(accessToken);
		auth.setTrustedAppId(trustedApp);
			ClientConsentCheckService client = new ClientConsentCheckService();
			try {
				 flag = client.checkClientConsentForUser(auth, UUID.fromString(map.get("CLIENTID").toString()));	
			}catch (Exception e) {
				 throw new AccessDeniedException("User does not have consent to acess the client information");
			}
			if(!flag) throw new AccessDeniedException("User does not have consent to acess the client information");
			return flag;
			
	}

}