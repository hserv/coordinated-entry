package com.hserv.coordinatedentry.housingmatching.external;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.servinglynk.hmis.warehouse.core.model.Recipients;

public interface NotificationService {
	
	void notifyStatusUpdate(Match match,Recipients recipients);
}