package com.servinglynk.hmis.warehouse.service;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientEntity;

public interface ClientService {
	
	ClientEntity getClientInfo(UUID dedupClentId);

}