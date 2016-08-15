package com.hserv.coordinatedentry.housingmatching.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hserv.coordinatedentry.housingmatching.external.ClientService;
import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;


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
}