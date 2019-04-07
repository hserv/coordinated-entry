package com.hserv.coordinatedentry.housingmatching.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.SharingRuleEntity;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.common.security.AuditUtil;


@Component
public class SharingRuleDaoImpl implements SharingRuleDao {

	@Autowired
	private EntityManager entityManager;
	
	public List<SharingRuleEntity> getSharingRules(UUID profileId,UUID userId){
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		
		DetachedCriteria criteria = DetachedCriteria.forClass(SharingRuleEntity.class);
		criteria.createAlias("profile","profile",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("toUser", "toUser",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("toProjectGroup", SecurityContextUtil.getUserProjectGroup()));
		if(userId!=null) {
			Criterion criterion = Restrictions.isNull("toUser.id");
			Criterion criterion2 = Restrictions.eq("toUser.id",userId);
            Disjunction inDisjunction = Restrictions.disjunction();
            		inDisjunction.add(criterion);
            		inDisjunction.add(criterion2);
			criteria.add(inDisjunction);
		}
		if(profileId!=null) {
			Criterion criterion = Restrictions.isNull("profile.id");
			Criterion criterion2 = Restrictions.eq("profile.id",profileId);
            Disjunction inDisjunction = Restrictions.disjunction();
            		inDisjunction.add(criterion);
            		inDisjunction.add(criterion2);
			criteria.add(inDisjunction);
		}
		Criteria eCriteria = criteria.getExecutableCriteria(session);
		return (List<SharingRuleEntity>) eCriteria.list();
	}


	public List<UUID> getSharedEnrollments(List<UUID> projects, String schemaYear) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
			
		String query = "select id from "+schemaYear+".enrollment where deleted =false and  projectid in ('"+StringUtils.join(projects.toArray(), ',').replaceAll(",", "','")+"')";
		System.out.println(query);
		
		return session.createSQLQuery(query).addScalar("id", org.hibernate.type.PostgresUUIDType.INSTANCE)
				.list();
	}

	public List<UUID> getSharedClients(List<UUID> enrollments, String schemaYear) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		if(!enrollments.isEmpty()) {
		String query = "SELECT * FROM "+schemaYear+".client WHERE deleted = FALSE " + 
				"AND ID IN ( SELECT client_id FROM "+schemaYear+".enrollment WHERE ID in ('"+StringUtils.join(enrollments.toArray(), ',').replaceAll(",", "','")+"'))";
		System.out.println(query);
		return session.createSQLQuery(query).addScalar("id", org.hibernate.type.PostgresUUIDType.INSTANCE)
				.list();
		}else {
			return new ArrayList<UUID>();
		}
	}
}