package com.servinglynk.hmis.warehouse.dao;

import java.util.List;

import com.servinglynk.hmis.warehouse.model.PropertyEntity;

public interface PropertyDao {

	public List<PropertyEntity> readProperties(String serviceName);
	public List<PropertyEntity> readCommonProperties();
	PropertyEntity readConsentProperty();
}
