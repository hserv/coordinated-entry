package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientEntity;

public interface ClientDao {
	
	ClientEntity getClient(UUID dedupClientId);
	ClientEntity getClientById(UUID clientId);

}