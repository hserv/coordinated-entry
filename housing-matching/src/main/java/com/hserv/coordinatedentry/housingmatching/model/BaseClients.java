package com.hserv.coordinatedentry.housingmatching.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("clients")
public class BaseClients {
	
	private List<BaseClient> clients = new ArrayList<BaseClient>();

	public List<BaseClient> getClients() {
		return clients;
	}

	public void setClients(List<BaseClient> clients) {
		this.clients = clients;
	}
	

	public void addClient(BaseClient client){
		this.clients.add(client);
	}

}
