package com.hserv.coordinatedentry.housingmatching.external;

import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.BaseClients;
import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;
import com.hserv.coordinatedentry.housingmatching.model.Session;

public interface ClientService {

	public ClientInfoModel getClientInfo(String clientId);
	BaseClients getClients(Session session);
	List<UUID> getClientIds(BaseClients clients);

}
