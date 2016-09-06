package com.servinglynk.hmis.warehouse.dao;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HealthDaoImpl extends QueryExecutorImpl implements HealthDao {


	public boolean checkConnectionHealth() {

		boolean flag=false;
		
		List result = getCurrentSession().createSQLQuery("select 1").list();
		
		if(!result.isEmpty()) flag = true;
		
		return flag;
	}

}
