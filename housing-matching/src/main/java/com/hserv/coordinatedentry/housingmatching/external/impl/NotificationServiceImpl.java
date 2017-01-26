package com.hserv.coordinatedentry.housingmatching.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.external.NotificationService;
import com.hserv.coordinatedentry.housingmatching.external.ProjectService;
import com.hserv.coordinatedentry.housingmatching.model.Project;
import com.servinglynk.hmis.warehouse.client.notificationservice.NotificationServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseProject;
import com.servinglynk.hmis.warehouse.core.model.Notification;
import com.servinglynk.hmis.warehouse.core.model.Parameter;
import com.servinglynk.hmis.warehouse.core.model.Recipients;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationServiceClient notificationServiceClient;
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	@Autowired
	ProjectService projectService;
	
	@Async
	public void notifyStatusUpdate(Match match,Recipients recipients,Session session,String trustedApp){
		
		
		HousingInventory unit = repositoryFactory.getHousingUnitsRepository().findOne(match.getHousingUnitId());
		
		BaseProject project = projectService.getProjectInfo(unit.getProjectId(), session, trustedApp);

		Notification notification = new Notification();
		notification.setType("HOUSING_MATCHING_STATUS_UPDATE");
		notification.setMethod("EMAIL");
		notification.setRecipients(recipients);
		notification.getParameters().addParameter(new Parameter("status",match.getMatchStatus()));
		notification.getParameters().addParameter(new Parameter("aliasName",unit.getAliasName()!=null ? unit.getAliasName() : "housing unit" ));
		notification.getParameters().addParameter(new Parameter("projectId",project.getProjectName()));
		notification.getParameters().addParameter(new Parameter("clientId",match.getEligibleClient().getClientId()));
		notificationServiceClient.createNotification(notification);
		
	}
}