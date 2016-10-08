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
	
	public EligibleClient findByClientId(UUID clientID);

	@Query("select ec from EligibleClient as ec where ec.matched=false and ec.programType<>?1")
	List<EligibleClient> findTopEligibleClients(String programType, Pageable pageable);
	
	List<EligibleClient> findByProgramTypeAndMatched(String programType,Boolean mathed);
	
	public List<EligibleClient> findAll();
	
	Page<EligibleClient> findByProjectGroupCode(String projectGroupCode,Pageable pageable);
	
	@Transactional(readOnly = false)
	Long deleteByClientId(UUID clientId);
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClient as ec set ec.surveyScore = 0")
	void deleteScores();
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClient as ec set ec.surveyScore = 0 where ec.clientId = ?1")
	void deleteScoreByClientId(UUID clientId);
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClient as ec set ec.surveyScore = ?1 where ec.clientId = ?2")
	void updateScoreByClientId(int score, UUID clientId);

	List<EligibleClient> findByProgramTypeAndMatched(String string, boolean b);
	
//	List<EligibleClient> findByProgramTypeAndMatchedObderBySurveyScoreAscSurveyDate(String projectGroup,boolean match);
	
}
