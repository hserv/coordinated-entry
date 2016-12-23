package com.servinglynk.hmis.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.client.search.SearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.TrustedApp;
import com.servinglynk.hmis.warehouse.service.ClientValidator;
import com.servinglynk.hmis.warehouse.service.exception.ResponseNotFoundException;

public class ClientValidatorImpl  implements ClientValidator {
	
	@Autowired
	ISearchServiceClient searchServiceClient;


	public String validateClient(UUID clientId,TrustedApp trustedApp,Session session) throws Exception {
		
		SearchRequest request = new SearchRequest();
		request.setTrustedAppId(trustedApp.getTrustedAppId());
		request.setSearchEntity("clients");
		request.setSessionToken(session.getToken());
		request.addSearchParam("q", clientId);
		List<BaseClient> clients=new ArrayList<BaseClient>();
		clients = (List<BaseClient>) searchServiceClient.search(request);
	
		if(clients.isEmpty()){
			throw new ResponseNotFoundException("Invalid Client Identification "+clientId);
		}else{
			return clients.get(0).getLink();
		}
	}

}
