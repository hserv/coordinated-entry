package com.servinglynk.hmis.warehouse.service;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.TrustedApp;

public interface ClientValidator {

	public String validateClient(UUID clientId,TrustedApp trustedApp,Session session) throws Exception  ;
}
