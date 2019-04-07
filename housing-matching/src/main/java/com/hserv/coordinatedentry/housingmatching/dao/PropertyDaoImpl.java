package com.hserv.coordinatedentry.housingmatching.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.PropertyEntity;


@Component
public class PropertyDaoImpl implements PropertyDao {

	@Autowired
	private EntityManager entityManager;
	@Override
	public PropertyEntity readSharingRuleProperty() {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(PropertyEntity.class);
		criteria.add(Restrictions.eq("keyName","applySharingRule"));
		Criteria eCriteria = criteria.getExecutableCriteria(session);
		List<PropertyEntity> entities = (List<PropertyEntity>) eCriteria.list();
		if(!entities.isEmpty()) return entities.get(0);
		return null;
	}

}
