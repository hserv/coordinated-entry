package com.hserv.coordinatedentry.housingmatching.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.dao.PropertyDao;
import com.hserv.coordinatedentry.housingmatching.entity.PropertyEntity;


@Component
public class PropertyReaderServiceImpl {

	@Autowired
	PropertyDao propertyDao;
	
	@Autowired
	Environment env;

	@Transactional
	public String readSharingRuleProperty() {
			PropertyEntity propertyEntity =propertyDao.readSharingRuleProperty();
			if(propertyEntity==null)
								return null;
			else
			return	propertyEntity.getValue();
	}
	
}
