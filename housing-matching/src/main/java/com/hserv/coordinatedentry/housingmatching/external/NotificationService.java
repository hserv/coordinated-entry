package com.hserv.coordinatedentry.housingmatching.external;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.servinglynk.hmis.warehouse.core.model.Recipients;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface NotificationService {
	
	void notifyStatusUpdate(Match match,Recipients recipients,Session session,String trustedApp);
}