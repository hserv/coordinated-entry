package com.hserv.coordinatedentry.housingmatching.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.external.NotificationService;
import com.servinglynk.hmis.warehouse.client.notificationservice.NotificationServiceClient;
import com.servinglynk.hmis.warehouse.core.model.Notification;
import com.servinglynk.hmis.warehouse.core.model.Parameter;
import com.servinglynk.hmis.warehouse.core.model.Recipients;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationServiceClient notificationServiceClient;
	
	@Async
	public void notifyStatusUpdate(Match match,Recipients recipients){
		
		Notification notification = new Notification();
		notification.setType("HOUSING_MATCHING_STATUS_UPDATE");
		notification.setMethod("EMAIL");
		notification.setRecipients(recipients);
		notification.getParameters().addParameter(new Parameter("status",match.getMatchStatus()));
		notificationServiceClient.createNotification(notification);
		
	}
}