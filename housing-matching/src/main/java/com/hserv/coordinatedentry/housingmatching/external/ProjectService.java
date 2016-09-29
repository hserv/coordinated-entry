package com.hserv.coordinatedentry.housingmatching.external;

import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.Project;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface ProjectService {

	Project getProjectInfo(UUID projectId,Session session, String trustedAppId);
}