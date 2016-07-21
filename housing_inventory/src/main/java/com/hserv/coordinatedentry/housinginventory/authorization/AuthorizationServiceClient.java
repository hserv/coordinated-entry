package com.hserv.coordinatedentry.housinginventory.authorization;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.servinglynk.hmis.warehouse.client.base.CoreClientBase;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;



public class AuthorizationServiceClient extends CoreClientBase implements IAuthorizationClient {
	//final static Logger logger = Logger.getLogger(AuthorizationServiceClient.class);

	/** Surya 04/13/2015 
	 * serviceURL is the URL where the notification service is deployed (different host) */
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ApiMethodAuthorizationCheck checkApiAuthorization(ApiMethodAuthorizationCheck authCheck) {
		
		HttpHeaders headers = getHttpHeaders();
		headers.add("X-HMIS-TrustedApp-Id", authCheck.getTrustedAppId());
		headers.add("Authorization","HMISUserAuth session_token="+authCheck.getAccessToken());
	
		//logger.debug(authCheck.toString());
		
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		converter.setObjectMapper(mapper);
		
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(createXmlHttpMessageConverter());
		messageConverters.add(converter);
		
		
/*		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);*/

//		restTemplate = new RestTemplate(requestFactory);
		restTemplate.setMessageConverters(messageConverters);
				
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<ApiMethodAuthorizationCheck> response = restTemplate.exchange("https://www.hmislynk.com/hmis-user-service/rest/apimethodauthcheck/"+authCheck.getApiMethodId(), HttpMethod.GET, entity ,ApiMethodAuthorizationCheck.class);

		return response.getBody();
	}
	
	private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter = 
          new MarshallingHttpMessageConverter();
 
        
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
 
        return xmlConverter;
	}

/*	public static void main(String args[]){
		
		System.setProperty("https.protocol", "TLSv1,TLSv1.1,TLSv1.2");
		
		ApiMethodAuthorizationCheck apiMethodAuthorizationCheck = new ApiMethodAuthorizationCheck();
		apiMethodAuthorizationCheck.setAccessToken("F1C6FF8AB0DA48DBB600B5BC5B65570627EDF58AF85A432193EC5E0B2A775ECE");
		apiMethodAuthorizationCheck.setApiMethodId("CLIENT_API_CREATE_SEXUALORIENTATION");
		apiMethodAuthorizationCheck.setTrustedAppId("MASTER_TRUSTED_APP");
		
		AuthorizationServiceClient client = new AuthorizationServiceClient();
	    client.checkApiAuthorization(apiMethodAuthorizationCheck);
	}*/
}
