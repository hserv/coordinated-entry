package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.MatchReservations;

@Repository
public interface MatchReservationsRepository extends JpaRepository<MatchReservations, Serializable>{

	@Modifying(clearAutomatically=true)
	@Query("update MatchReservations ms set ms.matchStatus = ?1 where ms.reservationId = ?2")
	int updateMatchStatus(String matchStatus, UUID reservationId);
	
	@Modifying(clearAutomatically=true)
	@Query("update MatchReservations ms set ms.matchStatus = ?1, ms.manualMatch = ?2 where ms.reservationId = ?3")
	int updateMatchStatusAndManualMatch(String matchStatus, boolean manualMatch, UUID reservationId);
	
	void deleteByEligibleClientsClientId(UUID clientId);
	
	MatchReservations findByEligibleClientsClientId(UUID clientId);
	
}
