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

	@Query("select ec from EligibleClients as ec where ec.matched = false")
	public List<EligibleClients> findTopEligibleClients(Pageable pageable);
	
	public List<EligibleClients> findAll();
	
	@Transactional(readOnly = false)
	public Long deleteByClientId(UUID clientId);
	
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClients as ec set ec.surveyScore = 0")
	public void deleteScores();
	
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClients as ec set ec.surveyScore = 0 where ec.clientId = ?1")
	public void deleteScoreByClientId(String clientId);
	
	@Modifying(clearAutomatically=true)
	@Query("update EligibleClients as ec set ec.surveyScore = ?1 where ec.clientId = ?2")
	public void updateScoreByClientId(int score, String clientId);
	
	

}
