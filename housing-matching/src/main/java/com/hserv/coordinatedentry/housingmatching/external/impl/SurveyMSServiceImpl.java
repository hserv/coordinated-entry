package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.service.OAuth2TokenService;
import com.hserv.coordinatedentry.housingmatching.util.RestClient;

@Service
public class SurveyMSServiceImpl implements SurveyMSService {

	@Value(value = "${SURVEY_MS_REST_SERVICE_URL}")
	private String SURVEY_MS_REST_SERVICE_URL;

	@Autowired
	private RestClient restClient;
	
	//@Autowired
	OAuth2TokenService oAuth2TokenService;

	@Override
	public List<SurveySectionModel> fetchSurveyResponse(String clientId) {
		// TODO Call MS using rest template after authentication

		//1.Call HMIS auth service to get the token
		OAuth2AccessToken  oAuth2AccessToken = oAuth2TokenService.getAccessToken();
		System.out.println(oAuth2AccessToken.getValue());
		oAuth2AccessToken.getRefreshToken();
		oAuth2AccessToken.getExpiration();
		oAuth2AccessToken.getValue();
		//2.Get that saved with us (in session/memory) so that next time we want to
		//access any service we dont have to get token again
		
		
		
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

	@Override
	public SurveyResponseModel fetchSurveyResponse() {
		SurveyResponseModel surveyResponseModel = 
				(SurveyResponseModel)restClient.get(SURVEY_MS_REST_SERVICE_URL, SurveyResponseModel.class);
		
		return surveyResponseModel;
	}

}
