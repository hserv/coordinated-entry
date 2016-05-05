package com.hserv.coordinatedentry.housingmatching.external.impl;

import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.external.ClientService;
import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;


@Service
public class ClientServiceImpl implements ClientService {

	@Override
	public ClientInfoModel getClientInfo(String clientId) {
		ClientInfoModel clientInfoModel = null;
		return clientInfoModel;
	}

}
