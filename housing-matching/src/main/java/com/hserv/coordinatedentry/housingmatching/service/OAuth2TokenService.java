package com.hserv.coordinatedentry.housingmatching.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuth2TokenService {
	
	String startPattern = "?code=";
	String grantTypePattern = ".*?";
	String endPattern = "&";
	
	String CODE = "code";
	
	String GRANT_TYPE = "grant_type";
	
	public OAuth2AccessToken getAccessToken();
}
