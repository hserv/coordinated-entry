package com.hserv.coordinatedentry.housingmatching.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hserv.coordinatedentry.housingmatching.service.OAuth2AuthorizationService;

@Service
public class OAuth2AuthorizationServiceImpl implements OAuth2AuthorizationService {

	@Value("${OAUTH_AUTHORIZATION_URL}")
	private String authorizarionUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${OAuth2.authorize.accessType}")
	private String accessType;

	@Value("${OAuth2.authorization.approvalPrompt}")
	private String approvalPrompt;

	@Value("${OAuth2.authorization.redirectUri}")
	private String redirectUri;

	@Value("${OAuth2.authorization.responsetype}")
	private String responseType;

	@Value("${OAuth2.authorization.state}")
	private String state;

	@Value("${OAuth2.ClientId}")
	private String trustedAppId;

	@Override
	public ResponseEntity<String> authorize() {
		
		// prepare URI to authorize before getting accessToken.
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(authorizarionUrl)
				.queryParam(ACCESS_TYPE, accessType).queryParam(APPROVAL_PROMPT, approvalPrompt)
				.queryParam(REDIRECT_URI, redirectUri).queryParam(RESPONSE_TYPE, responseType).queryParam(STATE, state)
				.queryParam(TRUSTED_APP_ID, trustedAppId);

		// set the header 
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		// get the response from /authorize entry point which consist authorization code
		// which is returned with redirect url provided, 
		// this code will be used as query parameter for code and grant type in /token entry point
		// example : Location: https://client.example.com/cb?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz
		ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
				entity, String.class);
		return response;

	}

}
