package com.servinglynk.hmis.warehouse.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.servinglynk.hmis.warehouse.model.ClientEntity;
import com.servinglynk.hmis.warehouse.service.ClientService;

@Service
public class ClientServiceImpl extends ServiceBase implements ClientService{

	public ClientEntity getClientInfo(UUID dedupClentId) {
		ClientEntity entity = daoFactory.getClientDao().getClient(dedupClentId);
		return entity;
	}
}