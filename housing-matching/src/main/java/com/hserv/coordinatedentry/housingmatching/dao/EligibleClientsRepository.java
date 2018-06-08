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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;

@Repository
public interface EligibleClientsRepository extends JpaRepository<EligibleClient, Serializable>,JpaSpecificationExecutor<EligibleClient> {
	
	public EligibleClient findByClientIdAndProjectGroupCodeAndDeletedOrderBySurveyDateAsc(UUID clientID,String projectGroup,boolean deleted);
	Page<EligibleClient> findByProjectGroupCodeAndDeletedAndIgnoreMatchProcessOrderBySurveyDateAsc(String projectGroupCode,boolean deleted,boolean ignoreMatchProcess,Pageable pageableignore);
	Page<EligibleClient> findByProjectGroupCodeAndDeletedOrderBySurveyDateAsc(String projectGroupCode,boolean deleted,Pageable pageableignore);
	
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

}