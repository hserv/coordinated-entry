package com.hserv.coordinatedentry.housingmatching.external;

import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.Project;
import com.servinglynk.hmis.warehouse.core.model.BaseProject;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface ProjectService {

	BaseProject getProjectInfo(UUID projectId,Session session, String trustedAppId);
}