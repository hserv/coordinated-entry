package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;

@Repository
public interface EligibleClientsRepository extends JpaRepository<EligibleClients, Serializable> {
	
	public EligibleClients findByClientId(UUID clientID);

	@Query("select ec from EligibleClients as ec where ec.matched = false and programType=?1")
	List<EligibleClients> findTopEligibleClients(String programType, Pageable pageable);
	
	public List<EligibleClients> findAll();
	
	@Transactional(readOnly = false)
	Long deleteByClientId(UUID clientId);
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClients as ec set ec.surveyScore = 0")
	void deleteScores();
	
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClients as ec set ec.surveyScore = 0 where ec.clientId = ?1")
	void deleteScoreByClientId(UUID clientId);
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClients as ec set ec.surveyScore = ?1 where ec.clientId = ?2")
	void updateScoreByClientId(int score, UUID clientId);
}
