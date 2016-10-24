package com.hserv.coordinatedentry.housingmatching.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.servinglynk.hmis.warehouse.client.authorizationservice.AuthorizationServiceClient;
import com.servinglynk.hmis.warehouse.core.model.ApiMethodAuthorizationCheck;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Value("${AUTH_SERVICE_URL}")
	private String authServieUrl;
	
	@Resource(name="restTemplateWithMapper")
	private RestTemplate restTemplate;
	
	@Autowired
	private SessionHelper sessionHelper;
	
	@Autowired
	private TrustedAppHelper trustedAppHelper;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle");
	
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		String apiMethodId=null;
		
		String accessToken = this.sessionHelper.retrieveSessionToken(request);
		String trustedAppId = this.trustedAppHelper.retrieveTrustedAppId(request);
		if(apiMapping!=null) {
			 if(apiMapping.value().equalsIgnoreCase("HEALTH_CHECK")) return true;
			ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
			apiMethodAuthorizationCheck.setApiMethodId("USR_CREATE_SESSION");  //TODO - remove this line once our api mappings are added to hmis db
			apiMethodAuthorizationCheck.setAccessToken(accessToken);
			apiMethodAuthorizationCheck.setTrustedAppId(trustedAppId);
			AuthorizationServiceClient client = new AuthorizationServiceClient();
			ApiMethodAuthorizationCheck clientresponse = client.checkApiAuthorization(apiMethodAuthorizationCheck);
		
			Session session = new Session();
			session.setToken(clientresponse.getAccessToken());
			session.setAccount(clientresponse.getAccount());
			this.sessionHelper.setSession(session, request);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session, ""));
			
			return true;
		}else{
			
			return false;
		}

	}
	
}
