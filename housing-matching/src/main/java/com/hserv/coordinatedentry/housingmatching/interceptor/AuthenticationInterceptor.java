package com.hserv.coordinatedentry.housingmatching.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.hserv.coordinatedentry.housingmatching.model.AuthResponseWrapper;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;

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
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle");
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		APIMapping apiMapping = handlerMethod.getMethodAnnotation(APIMapping.class);
		String apiMethodId=null;
		
		if(apiMapping!=null) {
			apiMethodId  = apiMapping.value();
			apiMethodId = "CLIENT_API_CREATE_SEXUALORIENTATION";//TODO - remove this line once our api mappings are added to hmis db
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-HMIS-TrustedApp-Id", request.getHeader("X-HMIS-TrustedApp-Id"));
		headers.set("Authorization", request.getHeader("Authorization"));

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
		    protected boolean hasError(HttpStatus statusCode) {
		        return false;
		    }});
		
		ResponseEntity<AuthResponseWrapper> authResponse =
		        restTemplate.exchange(authServieUrl+apiMethodId,HttpMethod.GET, entity, AuthResponseWrapper.class);

		if (null != authResponse && HttpStatus.OK.equals(authResponse.getStatusCode())) {
			return true;
		}else{
			response.setStatus(authResponse.getStatusCode().value());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = new Gson().toJson(authResponse.getBody().getErrors());
		    response.getWriter().write(json);
			return false;
		}
	}
	
}
