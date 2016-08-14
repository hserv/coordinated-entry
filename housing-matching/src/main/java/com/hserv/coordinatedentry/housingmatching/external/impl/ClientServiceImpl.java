package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hserv.coordinatedentry.housingmatching.external.ClientService;
import com.hserv.coordinatedentry.housingmatching.model.BaseClient;
import com.hserv.coordinatedentry.housingmatching.model.BaseClients;
import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;
import com.hserv.coordinatedentry.housingmatching.model.Session;


@Service
public class ClientServiceImpl implements ClientService {

	@Override
	public ClientInfoModel getClientInfo(String clientId) {
		ClientInfoModel clientInfoModel = null;
		return clientInfoModel;
	}
	
	@Value(value="${GET_CLIENTS_URL}")
	private String GET_CLIENTS_URL;
	
	
	@Autowired
	RestTemplate restTemplate;
	
	public BaseClients getClients(Session session){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-HMIS-TrustedApp-Id",session.getTrustedAppId());
		headers.add("Authorization","HMISUserAuth session_token="+session.getToken());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<BaseClients> responseEntity = restTemplate.exchange(GET_CLIENTS_URL, HttpMethod.GET,entity,BaseClients.class);
		return responseEntity.getBody();
	}
	
	
	public List<UUID> getClientIds(BaseClients clients) {
		
		List<UUID> ids = new ArrayList<UUID>();
		
		for(BaseClient client : clients.getClients()){
			ids.add(client.getClientId());
		}
		return ids;
	}
	

}
