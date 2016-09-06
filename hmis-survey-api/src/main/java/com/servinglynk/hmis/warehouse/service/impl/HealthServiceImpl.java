package com.servinglynk.hmis.warehouse.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.service.HealthService;

@Component
public class HealthServiceImpl extends ServiceBase implements HealthService {

	@Transactional
	public boolean checkConnectionHealth() {
		return daoFactory.getHealthDao().checkConnectionHealth();
	}

}
