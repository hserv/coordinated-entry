package com.servinglynk.hmis.warehouse.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.PropertyEntity;

@Component
public class PropertyDaoImpl extends QueryExecutorImpl implements PropertyDao {


	@SuppressWarnings("unchecked")
	public List<PropertyEntity> readProperties(String serviceName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PropertyEntity.class);
		criteria.add(Restrictions.eq("service", serviceName));
		return (List<PropertyEntity>) criteria.getExecutableCriteria(getCurrentSession()).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PropertyEntity> readCommonProperties() {
		DetachedCriteria criteria = DetachedCriteria.forClass(PropertyEntity.class);
		criteria.add(Restrictions.eq("service", "COMMON"));
		return (List<PropertyEntity>) criteria.getExecutableCriteria(getCurrentSession()).list();
	}
	
	public PropertyEntity readConsentProperty(){
		DetachedCriteria criteria = DetachedCriteria.forClass(PropertyEntity.class);
		criteria.add(Restrictions.eq("keyName","consentCheck"));
		List<PropertyEntity> entities = (List<PropertyEntity>) criteria.getExecutableCriteria(getCurrentSession()).list();
		if(!entities.isEmpty()) return entities.get(0);
		return null;
	}

}
