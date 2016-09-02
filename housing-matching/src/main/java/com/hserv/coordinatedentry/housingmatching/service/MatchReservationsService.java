package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface MatchReservationsService {

	public boolean createMatchReservation(MatchReservationModel matchReservationModel);
	
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels);
	
	public int updateMatchStatus(String matchStatus, String reservationId);
	
	public int updateMatchStatusAndMAnualMatch(String matchStatus, boolean manualMatch, String reservationId);
	
	public boolean deleteAll();
	
	public Page<Match> findAll(Pageable pageable);
	
	public boolean deleteByClientId(UUID clientId);
	
	public MatchReservationModel findByClientId(UUID clientId);
	
	public boolean updateByClientId(UUID clientId, MatchReservationModel matchReservationModel);
	
	public void createMatch(Session session, String trustedAppId) throws Exception ;
	
	public void create(Session session, String trustedAppId) throws Exception ;
 }
