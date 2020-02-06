package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;

@Repository
public interface EligibleClientsRepository extends JpaRepository<EligibleClient, Serializable>,JpaSpecificationExecutor<EligibleClient> {
	
	public EligibleClient findByClientIdAndProjectGroupCodeAndDeletedOrderBySurveyDateDesc(UUID clientID,String projectGroup,boolean deleted);
	//Page<EligibleClient> findByProjectGroupCodeAndDeletedAndIgnoreMatchProcessOrderBySurveyDateDesc(String projectGroupCode,boolean deleted,boolean ignoreMatchProcess,Pageable pageableignore);
	Page<EligibleClient> findByProjectGroupCodeAndDeletedOrderBySurveyDateDesc(String projectGroupCode,boolean deleted,Pageable pageableignore);
	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND T .project_group_code = :projectGroupCode " + 
			" ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ",nativeQuery=true)
	List<EligibleClient> getAllEligibleClients(String projectGroupCode, Integer limit,Integer start);
	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND T .project_group_code = :projectGroupCode " + 
			" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ",nativeQuery=true)
	List<EligibleClient> getInactiveEligibleClients(@Param("projectGroupCode") String projectGroupCode,@Param("limit") Integer limit,@Param("start") Integer start);
	
	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND T .project_group_code = :projectGroupCode " + 
			" AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ",nativeQuery=true)
	List<EligibleClient> getActiveEligibleClients(@Param("projectGroupCode") String projectGroupCode,@Param("limit") Integer limit,@Param("start") Integer start);
	
	
	@Query(value="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND T .project_group_code = :projectGroupCode " + 
			" ORDER BY T .client_dedup_id, survey_score desc ) ABC ",nativeQuery=true)
	Long getAllEligibleClientsCount(@Param("projectGroupCode") String projectGroupCode);
	
	@Query(value="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			"  AND T .project_group_code = :projectGroupCode " + 
			" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc ) ABC ",nativeQuery=true)
	Long getInactiveEligibleClientsCount(@Param("projectGroupCode") String projectGroupCode);
	
	
	@Query(value="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			"  AND T .project_group_code = :projectGroupCode " + 
			" AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc ) ABC ",nativeQuery=true)
	Long getActiveEligibleClientsCount(@Param("projectGroupCode") String projectGroupCode);
	
	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = :projectGroupCode OR tm.client_id IN ( :clients ) ) " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND ( T.project_group_code = :projectGroupCode OR T.client_id IN ( :clients ) ) " + 
			" ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ",nativeQuery=true)
	List<EligibleClient> getAllEligibleClientsWithSharedClients(String projectGroupCode,String clients, Integer limit,Integer start);
	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = :projectGroupCode OR tm.client_id IN ( :clients ) ) " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND ( T.project_group_code = :projectGroupCode OR T.client_id IN ( :clients ) ) " + 
			" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ",nativeQuery=true)
	List<EligibleClient> getInactiveEligibleClientsWithSharedClients(@Param("projectGroupCode") String projectGroupCode,@Param("clients") String clients,@Param("limit") Integer limit,@Param("start") Integer start);
	
	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = :projectGroupCode OR cast( tm.client_id as varchar) IN ( :clients ) ) " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND ( T.project_group_code = :projectGroupCode OR cast( T.client_id as varchar) IN ( :clients ) ) " + 
			" AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc  LIMIT  :limit OFFSET :start ",nativeQuery=true)
	List<EligibleClient> getActiveEligibleClientsWithSharedClients(@Param("projectGroupCode") String projectGroupCode,@Param("clients") List<UUID> clients,@Param("limit") Integer limit,@Param("start") Integer start);
	
	
	@Query(value="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = :projectGroupCode OR cast( tm.client_id  as varchar) IN ( :clients ) ) " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND ( T.project_group_code = :projectGroupCode OR cast( T.client_id as varchar) IN ( :clients ) ) " + 
			" ORDER BY T .client_dedup_id, survey_score desc ) ABC ",nativeQuery=true)
	Long getAllEligibleClientsCountWithSharedClients(@Param("projectGroupCode") String projectGroupCode,@Param("clients") String clients);
	
	@Query(value="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = :projectGroupCode OR tm.client_id IN ( :clients ) ) " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			"  AND ( T.project_group_code = :projectGroupCode OR T.client_id IN ( :clients ) ) " + 
			" AND T .ignore_match_process = TRUE ORDER BY T .client_dedup_id, survey_score desc ) ABC ",nativeQuery=true)
	Long getInactiveEligibleClientsCountWithSharedClients(@Param("projectGroupCode") String projectGroupCode,@Param("clients") String clients);
	
	
	@Query(value="SELECT COUNT(*) FROM (SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE ( tm.project_group_code = :projectGroupCode OR cast( tm.client_id  as varchar) IN ( :clients ) ) " + 
			"	AND deleted = FALSE GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			"  AND ( T.project_group_code = :projectGroupCode OR cast(T.client_id  as varchar) IN ( :clients ) ) " + 
			" AND T .ignore_match_process = FALSE ORDER BY T .client_dedup_id, survey_score desc ) ABC ",nativeQuery=true)
	Long getActiveEligibleClientsCountWithSharedClients(@Param("projectGroupCode") String projectGroupCode,@Param("clients") List<UUID> clients);
	
	
	@Transactional(readOnly = false)
	Long deleteByClientId(UUID clientId);
	
	EligibleClient findByClientIdAndDeleted(UUID clientID, boolean deleted);
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClient as ec set ec.surveyScore = 0 where deleted = false")
	void deleteScores();
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClient as ec set ec.surveyScore = 0 where ec.clientId = ?1")
	void deleteScoreByClientId(UUID clientId);

	public List<EligibleClient> findByClientDedupIdAndProjectGroupCodeAndDeletedOrderByDateCreatedDesc(UUID clientID,String projectGroup,boolean deleted);
	
	public EligibleClient findByClientIdAndProjectGroupCodeAndDeleted(UUID clientID, String projectGroup, boolean b);

	
	@Query(value="SELECT DISTINCT ON (T.client_dedup_id)  T.client_dedup_id,* FROM housing_inventory.eligible_clients T " + 
			"INNER JOIN ( SELECT client_dedup_id, MAX (survey_date) AS maxDate FROM housing_inventory.eligible_clients tm WHERE tm.project_group_code = :projectGroupCode " + 
			"	AND deleted = FALSE and cast( tm.client_dedup_id as varchar) = :clientDedupId GROUP BY client_dedup_id ) tm ON T .client_dedup_id = tm.client_dedup_id AND T .survey_date = tm.maxDate " + 
			" AND T .project_group_code = :projectGroupCode " + 
			"  ORDER BY T.client_dedup_id, survey_score desc ",nativeQuery=true)
	List<EligibleClient> getActiveEligibleClientByDedupId(@Param("projectGroupCode") String projectGroupCode,@Param("clientDedupId") String clientDedupId);

	
	
}
