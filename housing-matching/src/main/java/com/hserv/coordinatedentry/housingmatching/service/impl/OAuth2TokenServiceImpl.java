package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.hserv.coordinatedentry.housingmatching.service.OAuth2AuthorizationService;
import com.hserv.coordinatedentry.housingmatching.service.OAuth2TokenService;


@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {

	@Autowired
	private OAuth2AuthorizationService oAuth2AuthorizationService;

	@Autowired
	private OAuth2RestTemplate oAuth2restTemplate;

	@Value("${OAUTH2_TOKEN_URL}")
	private String tokenUrl;

	@Value("${OAuth2.ClientId}")
	private String clientId;

	@Value("${OAuth2.ClientSecret}")
	private String clientSecret;

	@Value("${OAuth2.authorization.redirectUri}")
	private String redirectUri;

	@Override
	public OAuth2AccessToken getAccessToken() {
		// take the response from HMIS authentication service /authorize entry point.
		ResponseEntity<String> response = oAuth2AuthorizationService.authorize();
		HttpStatus status = response.getStatusCode();
		// if status code is not 200 do not continue.
		if (!HttpStatus.OK.equals(status)) {
			return null;
		}
		String grantType = null;
		// extract grantType/code from the response body.4
		if (response.hasBody()) {
			String body = response.getBody();
			Pattern grantTypePatter = Pattern
					.compile(Pattern.quote(startPattern) + grantTypePattern + Pattern.quote(endPattern));
			Matcher matcher = grantTypePatter.matcher(body);
			if (matcher.matches()) {
				grantType = matcher.group(1);
			}
		}
		
		// prepare URI with query params to access the token from interceptor.
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUrl).queryParam(CODE, grantType)
				.queryParam(GRANT_TYPE, grantType).queryParam(OAuth2AuthorizationService.REDIRECT_URI, redirectUri);
		URI tokenUri = builder.build().encode().toUri();
		HeadersBuilder<?> headerBuilder = RequestEntity.head(builder.build().encode().toUri());
		headerBuilder.accept(MediaType.APPLICATION_JSON);
		headerBuilder.header("Authorization", (clientId + ":" + clientSecret));
		HttpEntity<?> requestEntity = headerBuilder.build();

		ResponseEntity<String> responseEntity = oAuth2restTemplate.exchange(tokenUri, HttpMethod.POST, requestEntity, String.class);

		return oAuth2restTemplate.getAccessToken();
	}

}
