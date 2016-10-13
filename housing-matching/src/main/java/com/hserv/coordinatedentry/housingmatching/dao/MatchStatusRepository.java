package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.MatchStatus;

@Repository
public interface MatchStatusRepository extends JpaRepository<MatchStatus, Serializable>{

  List<MatchStatus> findByClientId(UUID clientId);
  
  List<MatchStatus> findByClientIdOrderByDateCreatedDesc(UUID clientId);
	
}