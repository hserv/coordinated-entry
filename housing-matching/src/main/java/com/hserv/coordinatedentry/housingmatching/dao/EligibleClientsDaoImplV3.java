package com.hserv.coordinatedentry.housingmatching.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
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
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<EligibleClient> getActiveEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients,Integer limit,Integer start){
	
		List<EligibleClient> eligibleClients = new ArrayList<>();
		
		SessionImpl session = entityManager.unwrap(SessionImpl.class);
		Connection connection =  session.connection();
		
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";

		
			String query ="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id as \"clientDedupId\", client_id as \"clientId\",survey_type as \"surveyType\",survey_score as \"surveyScore\",program_type as \"programType\",matched,survey_date as \"surveyDate\", " + 
					" spdat_label as \"spdatLabel\",zip_code as \"zipCode\",coc_score as \"cocScore\",client_link as \"clientLink\",ignore_match_process as \"ignoreMatchProcess\",remarks,total_score, bonus_score, readded_reason FROM housing_inventory.eligible_clients T " +  
				"			INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients "
				+ "			tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"				AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate" + 
				"			 AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				"			 AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  "+limit+" OFFSET "+start+""; 
		
		System.out.println("query is "+query);
		
//		List<Object[]> data = session.createSQLQuery(query)
//				.addScalar("clientId", org.hibernate.type.PostgresUUIDType.INSTANCE).addScalar("clientDedupId",org.hibernate.type.PostgresUUIDType.INSTANCE)
		//.setResultTransformer(Transformers.aliasToBean(EligibleClient.class))
//		.list();
		Statement statement=null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			 resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				EligibleClient eligibleClient = new EligibleClient();
				eligibleClient.setClientDedupId(UUID.fromString(resultSet.getObject("clientDedupId")+""));
				eligibleClient.setClientId(UUID.fromString(resultSet.getObject("clientId")+""));
				eligibleClient.setSurveyType(resultSet.getString("surveyType"));
				eligibleClient.setSurveyScore(resultSet.getInt("surveyScore"));
				eligibleClient.setProgramType(resultSet.getString("programType"));
				eligibleClient.setMatched(resultSet.getBoolean("matched"));
				eligibleClient.setSurveyDate(resultSet.getTimestamp("surveyDate").toLocalDateTime());
				eligibleClient.setSpdatLabel(resultSet.getString("spdatLabel"));
				eligibleClient.setZipCode(resultSet.getString("zipCode"));
				eligibleClient.setCocScore(resultSet.getInt("cocScore"));
				eligibleClient.setClientLink(resultSet.getString("clientLink"));
				eligibleClient.setRemarks(resultSet.getString("remarks"));
				eligibleClient.setIgnoreMatchProcess(resultSet.getBoolean("ignoreMatchProcess"));
				eligibleClient.setBonusScore(resultSet.getInt("bonus_score"));
				eligibleClient.setTotalScore(resultSet.getInt("total_score"));
				eligibleClient.setReaddedReason(resultSet.getString("readded_reason"));
				if(eligibleClient.getClientId()!=null) {
					eligibleClient.setClient(clientRepository.findById(eligibleClient.getClientId()));
				}
			eligibleClients.add(eligibleClient);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
				}
			if(resultSet!=null)
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
		}
				
		return eligibleClients;
	}
	
	public List<EligibleClient> getAllEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients, Integer limit,Integer start){
		List<EligibleClient> eligibleClients = new ArrayList<>();
		
		SessionImpl session = entityManager.unwrap(SessionImpl.class);
		Connection connection =  session.connection();
		
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		
		String query ="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id as \"clientDedupId\", client_id as \"clientId\",survey_type as \"surveyType\",survey_score as \"surveyScore\",program_type as \"programType\",matched,survey_date as \"surveyDate\", " + 
				" spdat_label as \"spdatLabel\",zip_code as \"zipCode\",coc_score as \"cocScore\",client_link as \"clientLink\",ignore_match_process as \"ignoreMatchProcess\",remarks,total_score, bonus_score, readded_reason FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				" AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				" ORDER BY T .client_dedup_id, survey_score desc  LIMIT "+limit+" OFFSET "+start+"";
		
		System.out.println("query is "+query);
		
		Statement statement=null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			 resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				EligibleClient eligibleClient = new EligibleClient();
				eligibleClient.setClientDedupId(UUID.fromString(resultSet.getObject("clientDedupId")+""));
				eligibleClient.setClientId(UUID.fromString(resultSet.getObject("clientId")+""));
				eligibleClient.setSurveyType(resultSet.getString("surveyType"));
				eligibleClient.setSurveyScore(resultSet.getInt("surveyScore"));
				eligibleClient.setProgramType(resultSet.getString("programType"));
				eligibleClient.setMatched(resultSet.getBoolean("matched"));
				eligibleClient.setSurveyDate(resultSet.getTimestamp("surveyDate").toLocalDateTime());
				eligibleClient.setSpdatLabel(resultSet.getString("spdatLabel"));
				eligibleClient.setZipCode(resultSet.getString("zipCode"));
				eligibleClient.setCocScore(resultSet.getInt("cocScore"));
				eligibleClient.setClientLink(resultSet.getString("clientLink"));
				eligibleClient.setRemarks(resultSet.getString("remarks"));
				eligibleClient.setIgnoreMatchProcess(resultSet.getBoolean("ignoreMatchProcess"));
				eligibleClient.setBonusScore(resultSet.getInt("bonus_score"));
				eligibleClient.setTotalScore(resultSet.getInt("total_score"));
				eligibleClient.setReaddedReason(resultSet.getString("readded_reason"));
				if(eligibleClient.getClientId()!=null) {
					eligibleClient.setClient(clientRepository.findById(eligibleClient.getClientId()));
				}
			eligibleClients.add(eligibleClient);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
				}
			if(resultSet!=null)
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
		}
				
		return eligibleClients;
	}
	

	public List<EligibleClient> getInactiveEligibleClientsWithSharedClients(String projectGroupCode,List<UUID> sharedclients,Integer limit, Integer start){
		
	List<EligibleClient> eligibleClients = new ArrayList<>();
		
		SessionImpl session = entityManager.unwrap(SessionImpl.class);
		Connection connection =  session.connection();
	
		 String clients = "'"+StringUtils.join(sharedclients.toArray(), ',').replaceAll(",", "','")+"'";
		
		String query="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id as clientDedupId, client_id as clientId,survey_type as surveyType,survey_score as surveyScore,program_type as programType,matched,survey_date as surveyDate, " + 
				" spdat_label as spdatLabel,zip_code as zipCode,coc_score as cocScore,client_link as clientLink,ignore_match_process ignoreMatchProcess,remarks,total_score, bonus_score, readded_reason FROM housing_inventory.eligible_clients T " + 
				"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = '"+projectGroupCode+"' OR tm.client_id IN ( "+clients+" ) ) " + 
				"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
				" AND ( T.project_group_code = '"+projectGroupCode+"' OR T.client_id IN ( "+clients+" ) ) " + 
				" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  "+limit+" OFFSET "+start+"";
		
		System.out.println("query is "+query);
		
		
		Statement statement=null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			 resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				EligibleClient eligibleClient = new EligibleClient();
				eligibleClient.setClientDedupId(UUID.fromString(resultSet.getObject("clientDedupId")+""));
				eligibleClient.setClientId(UUID.fromString(resultSet.getObject("clientId")+""));
				eligibleClient.setSurveyType(resultSet.getString("surveyType"));
				eligibleClient.setSurveyScore(resultSet.getInt("surveyScore"));
				eligibleClient.setProgramType(resultSet.getString("programType"));
				eligibleClient.setMatched(resultSet.getBoolean("matched"));
				eligibleClient.setSurveyDate(resultSet.getTimestamp("surveyDate").toLocalDateTime());
				eligibleClient.setSpdatLabel(resultSet.getString("spdatLabel"));
				eligibleClient.setZipCode(resultSet.getString("zipCode"));
				eligibleClient.setCocScore(resultSet.getInt("cocScore"));
				eligibleClient.setClientLink(resultSet.getString("clientLink"));
				eligibleClient.setRemarks(resultSet.getString("remarks"));
				eligibleClient.setIgnoreMatchProcess(resultSet.getBoolean("ignoreMatchProcess"));
				eligibleClient.setBonusScore(resultSet.getInt("bonus_score"));
				eligibleClient.setTotalScore(resultSet.getInt("total_score"));
				eligibleClient.setReaddedReason(resultSet.getString("readded_reason"));
				if(eligibleClient.getClientId()!=null) {
					eligibleClient.setClient(clientRepository.findById(eligibleClient.getClientId()));
				}
			eligibleClients.add(eligibleClient);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
				}
			if(resultSet!=null)
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
		}
				
		return eligibleClients;
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
