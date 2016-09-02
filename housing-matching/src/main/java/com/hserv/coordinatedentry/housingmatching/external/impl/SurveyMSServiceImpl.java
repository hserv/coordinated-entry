package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.util.RestClient;
import com.servinglynk.hmis.warehouse.core.model.Session;

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
	
	@Resource(name="restTemplateWithMapper")
	RestTemplate restTemplate;

	
	@Override
	public ClientsSurveyScores fetchSurveyResponse(Session session) {		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-HMIS-TrustedApp-Id", "MASTER_TRUSTED_APP");
		headers.add("Authorization","HMISUserAuth session_token="+session.getToken());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		
		ResponseEntity<ClientsSurveyScores> responseEntity = restTemplate.exchange(SURVEY_MS_REST_SERVICE_URL, HttpMethod.GET,entity,ClientsSurveyScores.class);

		return responseEntity.getBody();
	}

}
