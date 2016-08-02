package com.hserv.coordinatedentry.housinginventory.config;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hserv.coordinatedentry.housinginventory.annotation.APIMapping;
import com.servinglynk.hmis.warehouse.client.authorizationservice.AuthorizationServiceClient;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;


/* This is CES an example of CES interceptor. It intercepts all the requests that the micro service receives and also calls HMIS 
Authorization interceptor (through Client SDK) for authorization purposes */
public class WebInterceptor extends HandlerInterceptorAdapter {
	
	private static enum Method {
		Query, Header,Cookie
	};


	private Method[] methodOrder = new Method[] { Method.Query, Method.Header ,Method.Cookie};

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception{
		
		
		HandlerMethod handlerMethod = null;
		handlerMethod = (HandlerMethod) handler;
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);		
		if(apiMapping!=null) {
			ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
			apiMethodAuthorizationCheck.setApiMethodId(apiMapping.value());
			apiMethodAuthorizationCheck.setAccessToken(getAuthorizationHeader(request));
			apiMethodAuthorizationCheck.setTrustedAppId(getTrustedApp(request));
			AuthorizationServiceClient client = new AuthorizationServiceClient();
			ApiMethodAuthorizationCheck clientresponse = client.checkApiAuthorization(apiMethodAuthorizationCheck);
			return true;
		}else{
			
			return false;
		}
	}
	
	public String getAuthorizationHeader(HttpServletRequest request){
		String sessionToken = null;
		for (Method method : methodOrder) {
			sessionToken = retrieveSessionToken(request, method);
			if (sessionToken != null) {
				break;
			}
		}
		return sessionToken;
	}
	
	private String retrieveSessionToken(HttpServletRequest request, Method method) {
		String headerName = "Authorization";
		String parameterName = "authentication_token";
		String cookieName = "authentication_token";
		String headerRegex = "[\\s|=|\"]+";
		String authRealm = "HMISUserAuth";
		String oAuthRealm = "Bearer";

		String sessionToken = null;
		switch (method) {
		case Header:
			String headerValue = request.getHeader(headerName);
			if (headerValue != null) {
				String[] split = headerValue.split(headerRegex);
				if (split != null)	{
					if ((split.length >= 3) && split[0].equalsIgnoreCase(authRealm))	{
						sessionToken = split[2];
					}
					else if ((split.length >= 2) && split[0].equalsIgnoreCase(oAuthRealm))	{
						sessionToken = split[1];
					}
				}
			}
			break;
		case Query:
			sessionToken = request.getParameter(parameterName);
			break;
		case Cookie:
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(cookieName)) {
						sessionToken = cookie.getValue();
					}
				}
			}
			break;
		}
		return sessionToken;
	}

	
	
	public String getTrustedApp(HttpServletRequest request){
		String trustedAppId = null;
		for (Method method : methodOrder) {
			trustedAppId = retrieveTrustedApp(request, method);
			if (trustedAppId != null) {
				break;
			}
		}
		return trustedAppId;
	}
	
	private String retrieveTrustedApp(HttpServletRequest request, Method method) {
		 String headerName = "X-HMIS-TrustedApp-Id";
		 String parameterName = "trustedApp_id";
		String trustedAppId = null;
		switch (method) {
		case Header:
			trustedAppId = request.getHeader(headerName);
			break;
		case Query:
			trustedAppId = request.getParameter(parameterName);
			break;
		}
		if (trustedAppId != null) {
			trustedAppId = trustedAppId.trim();
		}
		return trustedAppId;
	}
}