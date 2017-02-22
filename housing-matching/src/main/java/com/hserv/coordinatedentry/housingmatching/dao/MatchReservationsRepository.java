package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.Match;

@Repository
public interface MatchReservationsRepository extends JpaRepository<Match, Serializable>{

//	void deleteByEligibleClientClientId(UUID clientId);
	
	List<Match> findByEligibleClientAndDeletedOrderByDateCreatedDesc(EligibleClient clientId,boolean deleted);
	
	

	@Query(value="select 1",nativeQuery=true)
	Integer validateConnection();
	
	
	Page<Match> findByProgramTypeAndDeleted(String projectGroupCode,boolean deleted,Pageable pageable);

	Page<Match> findByProgramTypeAndMatchStatusInAndDeleted(String projectGroupCode, String[] status,boolean deleted ,Pageable pageable);
	
	
}