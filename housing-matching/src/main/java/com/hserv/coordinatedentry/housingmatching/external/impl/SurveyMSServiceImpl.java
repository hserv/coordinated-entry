package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.util.RestClient;

@Service
public class SurveyMSServiceImpl implements SurveyMSService {

	@Value(value = "${SURVEY_MS_REST_SERVICE_URL}")
	private String SURVEY_MS_REST_SERVICE_URL;

	@Autowired
	private RestClient restClient;
	

	@Override
	public List<SurveySectionModel> fetchSurveyResponse(String clientId) {

		// 3.Obtain token and call Survey MS.
		String url = new StringBuilder(SURVEY_MS_REST_SERVICE_URL).append(clientId).toString();
		List<SurveySectionModel> surveySectionList = new ArrayList<>();
		try{
			// 4.Return the useful data.
			SurveyResponseModel surveyResponseModel = (SurveyResponseModel)restClient.get(url, SurveyResponseModel.class);
			surveySectionList = surveyResponseModel.getSurveySectionList();
			
		}catch(Exception ex){
			
		}
		return surveySectionList;
	}
	
//	@Autowired
	RestTemplate restTemplate;

	private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter = 
          new MarshallingHttpMessageConverter();
 
        
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
 
        return xmlConverter;
	}
	
	@Override
	public ClientsSurveyScores fetchSurveyResponse() {
		
		
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
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-HMIS-TrustedApp-Id", "MASTER_TRUSTED_APP");
		headers.add("Authorization","HMISUserAuth session_token=75FA4103585E4EC7AF3A1B3E7A4D103E905CEC430620459C99CE34603EF5A44D");
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		
		ResponseEntity<ClientsSurveyScores> responseEntity = restTemplate.exchange(SURVEY_MS_REST_SERVICE_URL, HttpMethod.GET,entity,ClientsSurveyScores.class);
		
/*		ObjectMapper mapper = new ObjectMapper();
	     try {
			JsonNode jsonNode =  	mapper.readTree(responseEntity.getBody());
			 JsonNode node =  jsonNode.get("clientsSurveyScores");
			 System.out.println(jsonNode+"    \n"+node.asText());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println(" survey "+responseEntity.getBody().getClientsSurveyScores().size());
		
		return responseEntity.getBody();
	}

}
