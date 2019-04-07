package com.hserv.coordinatedentry.housingmatching.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	
	public List<EligibleClient> getActiveEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients,Integer limit,Integer start){
	
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";

		
		String query="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id as clientDedupId, client_id as clientId,survey_type as surveyType,survey_score as surveyScore,program_type as programType,matched,survey_date as surveyDate, " + 
				"					spdat_label as spdatLabel,zip_code as zipCode,coc_score as cocScore,client_link as clientLink,ignore_match_process ignoreMatchProcess,remarks  FROM housing_inventory.eligible_clients T " + 
				"			INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients "
				+ "			tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"				AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate" + 
				"			 AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				"			 AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  "+limit+" OFFSET "+start+""; 
		
		System.out.println("query is "+query);
		
		return session.createSQLQuery(query).addScalar("clientId", org.hibernate.type.PostgresUUIDType.INSTANCE).addScalar("clientDedupId",org.hibernate.type.PostgresUUIDType.INSTANCE)
		.setResultTransformer(Transformers.aliasToBean(EligibleClient.class))
		.list();
	}
	
	public List<EligibleClient> getAllEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients, Integer limit,Integer start){
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		
		String query ="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id as clientDedupId, client_id as clientId,survey_type as surveyType,survey_score as surveyScore,program_type as programType,matched,survey_date as surveyDate, " + 
				" spdat_label as spdatLabel,zip_code as zipCode,coc_score as cocScore,client_link as clientLink,ignore_match_process ignoreMatchProcess,remarks FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				" AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				" ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ";
		
		System.out.println("query is "+query);
		
		return session.createSQLQuery(query).addScalar("clientId", org.hibernate.type.PostgresUUIDType.INSTANCE).addScalar("clientDedupId",org.hibernate.type.PostgresUUIDType.INSTANCE)
		.setResultTransformer(Transformers.aliasToBean(EligibleClient.class))
		.list();
	}
	

	public List<EligibleClient> getInactiveEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients,@Param("limit") Integer limit,@Param("start") Integer start){
		
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		
		String query="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id as clientDedupId, client_id as clientId,survey_type as surveyType,survey_score as surveyScore,program_type as programType,matched,survey_date as surveyDate, " + 
				" spdat_label as spdatLabel,zip_code as zipCode,coc_score as cocScore,client_link as clientLink,ignore_match_process ignoreMatchProcess,remarks FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				" AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ";
		
		System.out.println("query is "+query);
		
		return session.createSQLQuery(query).addScalar("clientId", org.hibernate.type.PostgresUUIDType.INSTANCE).addScalar("clientDedupId",org.hibernate.type.PostgresUUIDType.INSTANCE)
		.setResultTransformer(Transformers.aliasToBean(EligibleClient.class))
		.list();
	}
	


	public Long getAllEligibleClientsCountWithSharedClients(String projectGroupCode,List<UUID> sharedclients) {
		
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		 
		String query ="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id  IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				" AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				" ORDER BY T .client_dedup_id, survey_score desc ) ABC ";
		return ((BigInteger) session.createSQLQuery(query).uniqueResult()).longValue();
	}
	

	public Long getInactiveEligibleClientsCountWithSharedClients(String projectGroupCode,List<UUID> sharedclients) {
		
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		 
		 
		String query = "SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				"  AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc ) ABC ";
		return ((BigInteger) session.createSQLQuery(query).uniqueResult()).longValue();
	}
	
	
	public Long getActiveEligibleClientsCountWithSharedClients(String projectGroupCode,List<UUID> sharedclients) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		
		String query ="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR  tm.client_id IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				"  AND ( T.project_group_code = '"+projectGroupCode+"' OR cast(T.client_id  as varchar) IN ( "+clients+" ) ) " + 
				" AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc ) ABC";
		return ((BigInteger) session.createSQLQuery(query).uniqueResult()).longValue();
	}
	
}
