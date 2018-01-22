package com.servinglynk.hmis.warehouse.service;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSubmissionEntity;

public interface ClientSubmissionService {
	
	void createClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity);
	void createClientSubmissionEntity(List<ClientSubmissionEntity> clientSubmissionEntities);
	void updateClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity);
}

