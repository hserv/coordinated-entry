package com.hserv.coordinatedentry.housingmatching.external;

import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;

public interface ClientService {

	public ClientInfoModel getClientInfo(String clientId);

}
