package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.ClientEntity;

@Component
public class ClientDaoImpl extends QueryExecutorImpl implements ClientDao {

	public ClientEntity getClient(UUID dedupClientId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientEntity.class);
		criteria.add(Restrictions.eq("dedupClientId", dedupClientId));
		criteria.addOrder(Order.asc("schemaYear"));
		List<ClientEntity> clientEntities = (List<ClientEntity>) findByCriteria(criteria);
		if(!clientEntities.isEmpty()) return clientEntities.get(0);
		return null;
	}
}