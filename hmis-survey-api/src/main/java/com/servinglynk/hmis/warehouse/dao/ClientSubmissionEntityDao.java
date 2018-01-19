package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.ClientSubmissionEntity;

public interface ClientSubmissionEntityDao {
	
	ClientSubmissionEntity createClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity);
	ClientSubmissionEntity updateClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity);
	void deleteClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity);
	ClientSubmissionEntity getClientSubmissionEntityById(UUID clientSubmissionEntityId);
	  

}
