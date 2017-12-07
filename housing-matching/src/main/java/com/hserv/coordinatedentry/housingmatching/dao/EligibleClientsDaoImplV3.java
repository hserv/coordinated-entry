package com.hserv.coordinatedentry.housingmatching.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;

@Component
public class EligibleClientsDaoImplV3  implements EligibleClientsDaoV3 {
	
	@Autowired
	private EntityManager entityManager;
	
	public EligibleClient getEligibleClients(UUID clientDedupId,String projectGroupCode) {
		
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(EligibleClient.class);
		criteria.createAlias("client", "client");
		criteria.add(Restrictions.eq("projectGroupCode", projectGroupCode));
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("client.dedupClientId", clientDedupId));
		criteria.add(Restrictions.eq("client.projectGroupCode", projectGroupCode));
		criteria.addOrder(Order.desc("dateCreated"));

		Criteria eCriteria = criteria.getExecutableCriteria(session);
		List<EligibleClient> enities =  eCriteria.list();
		if(enities.isEmpty()) return null;
		return enities.get(0);
	}

}
