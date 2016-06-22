package com.hserv.coordinatedentry.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hserv.coordinatedentry.annotation.APIMapping;
//import com.servinglynk.hmis.warehouse.client.authorizationservice.AuthorizationServiceClient;
import com.hserv.coordinatedentry.authorization.AuthorizationServiceClient;
import com.hserv.coordinatedentry.exception.AuthorizationException;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;


/* This is CES an example of CES interceptor. It intercepts all the requests that the micro service receives and also calls HMIS 
Authorization interceptor (through Client SDK) for authorization purposes */
public class WebInterceptor extends HandlerInterceptorAdapter {

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		String apiMethodId = null;

		if (apiMapping != null) {
			apiMethodId = apiMapping.value();
		}
		apiMethodId = "CLIENT_API_CREATE_SEXUALORIENTATION";
		
		String authorization = extractAuthorizationString(request.getHeader("Authorization"));
		
		//this is the object that need be posted to the HMIS Authorization intecetpro
		ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
		//Set the Access token that HOME APP Pass on to CES
		//TODO Swagger work 
		apiMethodAuthorizationCheck.setAccessToken(authorization);
		//apiMethodAuthorizationCheck.setAccessToken("F1C6FF8AB0DA48DBB600B5BC5B65570627EDF58AF85A432193EC5E0B2A775ECE");
		// SET THE API Method that CES pre registered with HMIS Platform
		apiMethodAuthorizationCheck.setApiMethodId(apiMethodId);
		// SET the TRUSTED APP ID that HOME APP(or any client) - NOT CES TRUSTED APP
		apiMethodAuthorizationCheck.setTrustedAppId("MASTER_TRUSTED_APP");
		
		//This is the client Authroization Service client call that needs to be made - 
		
		try {
			AuthorizationServiceClient client = new AuthorizationServiceClient();
			apiMethodAuthorizationCheck = client.checkApiAuthorization(apiMethodAuthorizationCheck);
		} catch (Exception e) {
			//TODO: change the message as per returns from response
			throw new AuthorizationException("Invalid user access token");
		}
		
	
		return true;
	}

	private String extractAuthorizationString(String authorizationHeader) {
		if (StringUtils.isBlank(authorizationHeader)) {
			return null;
		}
		
		//TODO: extract token only from string
		return authorizationHeader;
	}
}