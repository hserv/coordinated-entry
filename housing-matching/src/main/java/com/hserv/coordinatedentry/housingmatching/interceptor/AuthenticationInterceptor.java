package com.hserv.coordinatedentry.housingmatching.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.servinglynk.hmis.warehouse.config.APIMapping;
//import com.servinglynk.hmis.warehouse.config.ApiMethodAuthorizationCheck;
//import com.servinglynk.hmis.warehouse.config.AuthorizationServiceClient;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 * 1.Before making any call from controller, we need to have user role
		 * check, please write that logic as a dispatcher/or whatever you feel
		 * is the best, so only if user has correct user privilages calls should
		 * be triggered. This should be at the base url "/".
		 */

		System.out.println("AuthenticationInterceptor : successful");
		HandlerMethod handlerMethod = null;

		handlerMethod = (HandlerMethod) handler;
		String methodName = handlerMethod.getMethod().getName();
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		String apiMethodId = null;

		if (apiMapping != null) {
			apiMethodId = apiMapping.value();
			System.out.println("Invoked method Id: "+apiMethodId);
		}

		// this is the object that need be posted to the HMIS Authorization inteceptor
		//ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
		// Set the Access token that HOME APP Pass on to CES
		//apiMethodAuthorizationCheck.setAccessToken("F1C6FF8AB0DA48DBB600B5BC5B65570627EDF58AF85A432193EC5E0B2A775ECE");
		// SET THE API Method that CES pre registered with HMIS Platform
		//apiMethodAuthorizationCheck.setApiMethodId("CLIENT_API_CREATE_SEXUALORIENTATION");
		// SET the TRUSTED APP ID that HOME APP(or any client) - NOT CES TRUSTED APP
		//apiMethodAuthorizationCheck.setTrustedAppId("MASTER_TRUSTED_APP");

		// This is the client Authroization Service client call that needs to be made -
		//AuthorizationServiceClient client = new AuthorizationServiceClient();
		//apiMethodAuthorizationCheck = client.checkApiAuthorization(apiMethodAuthorizationCheck);

		return true;
	}
}
