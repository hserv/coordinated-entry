package com.hserv.coordinatedentry.housingmatching.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hserv.coordinatedentry.housingmatching.helper.SessionHelper;
import com.servinglynk.hmis.warehouse.client.authorizationservice.AuthorizationServiceClient;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;
import com.servinglynk.hmis.warehouse.client.model.Session;

/**
 * This is CES intercepter. It intercepts all the requests that 
 * the micro service receives and also calls HMIS Authorization intercepter (through Client SDK)
 * for authorization purposes.
 *
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Value("${AUTH_SERVICE_URL}")
	private String authServieUrl;
	
	@Resource(name="restTemplateWithMapper")
	private RestTemplate restTemplate;
	
	@Autowired
	private SessionHelper sessionHelper;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle");
	
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		String apiMethodId=null;
		
		String accessToken = this.sessionHelper.retrieveSessionToken(request);
		String trustedApp = request.getHeader("X-HMIS-TrustedApp-Id");
		if(apiMapping!=null) {
			ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
			apiMethodAuthorizationCheck.setApiMethodId("USR_CREATE_SESSION");  //TODO - remove this line once our api mappings are added to hmis db
			apiMethodAuthorizationCheck.setAccessToken(accessToken);
			apiMethodAuthorizationCheck.setTrustedAppId(trustedApp);
			AuthorizationServiceClient client = new AuthorizationServiceClient();
			ApiMethodAuthorizationCheck clientresponse = client.checkApiAuthorization(apiMethodAuthorizationCheck);
		
			Session session = new Session();
			session.setToken(clientresponse.getAccessToken());
			this.sessionHelper.setSession(session, request);
			
			return true;
		}else{
			
			return false;
		}

	}
	
}
