package com.hserv.coordinatedentry.housingmatching.service;

import org.springframework.http.ResponseEntity;

public interface OAuth2AuthorizationService {

	String ACCESS_TYPE = "access_type";
	
	String APPROVAL_PROMPT = "approval_prompt";
	
	String REDIRECT_URI = "redirect_uri";
	
	String RESPONSE_TYPE = "response_type";
	
	String STATE = "state";
	
	String TRUSTED_APP_ID = "trustedApp_id";
	
	public ResponseEntity<String> authorize();
	
}
