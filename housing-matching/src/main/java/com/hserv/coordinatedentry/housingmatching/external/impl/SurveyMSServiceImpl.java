package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;

@Service
public class SurveyMSServiceImpl implements SurveyMSService {

	@Override
	public List<SurveyResponseModel> fetchServeyResponse() {
		// TODO Call MS using rest template after authentication
		
		//1.Obtain token and call Survey MS.
		//2.Return the useful data.
		return null;
	}

}
