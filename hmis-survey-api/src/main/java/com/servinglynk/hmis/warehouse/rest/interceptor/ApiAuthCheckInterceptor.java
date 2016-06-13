package com.servinglynk.hmis.warehouse.rest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;

public class ApiAuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = null;
		handlerMethod = (HandlerMethod) handler;

		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		
		ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
		apiMethodAuthorizationCheck.setApiMethodId(apiMapping.value());

		//AuthorizationServiceClient client = new AuthorizationServiceClient();
		//client.checkApiAuthorization(apiMethodAuthorizationCheck);
		
		return true;
	}

}
