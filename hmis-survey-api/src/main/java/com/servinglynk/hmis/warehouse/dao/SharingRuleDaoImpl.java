package com.servinglynk.hmis.warehouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.common.security.AuditUtil;
import com.servinglynk.hmis.warehouse.model.SharingRuleEntity;

@Component
public class SharingRuleDaoImpl extends QueryExecutorImpl implements SharingRuleDao {

	
	public List<SharingRuleEntity> getSharingRules(UUID profileId,UUID userId){
		DetachedCriteria criteria = DetachedCriteria.forClass(SharingRuleEntity.class);
		criteria.createAlias("profile","profile",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("toUser", "toUser",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("toProjectGroup", AuditUtil.getLoginUserProjectGroup()));
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
		return (List<SharingRuleEntity>) getByCriteria(criteria);
	}


	public List<UUID> getSharedEnrollments(List<UUID> projects, String schemaYear) {
			
		String query = "select id from "+schemaYear+".enrollment where deleted =false and  projectid in ('"+StringUtils.join(projects.toArray(), ',').replaceAll(",", "','")+"')";
		System.out.println(query);
		return findIdsByNativeQuery(query);
	}

	public List<UUID> getSharedClients(List<UUID> enrollments, String schemaYear) {
		if(!enrollments.isEmpty()) {
		String query = "SELECT * FROM "+schemaYear+".client WHERE deleted = FALSE " + 
				"AND ID IN ( SELECT client_id FROM "+schemaYear+".enrollment WHERE ID in ('"+StringUtils.join(enrollments.toArray(), ',').replaceAll(",", "','")+"'))";
		System.out.println(query);
		return findIdsByNativeQuery(query);}else {
			return new ArrayList<UUID>();
		}
	}
}