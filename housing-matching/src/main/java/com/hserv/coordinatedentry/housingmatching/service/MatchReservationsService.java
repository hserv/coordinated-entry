package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

public interface MatchReservationsService {

	public boolean createMatchReservation(MatchReservationModel matchReservationModel);
	
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels);
	
	public int updateMatchStatus(String matchStatus, String reservationId);
	
	public int updateMatchStatusAndMAnualMatch(String matchStatus, boolean manualMatch, String reservationId);
	
	public boolean deleteAll();
	
	public Set<MatchReservationModel> findAll();
	
	public boolean deleteByClientId(UUID clientId);
	
	public MatchReservationModel findByClientId(UUID clientId);
	
	public boolean updateByClientId(UUID clientId, MatchReservationModel matchReservationModel);
	
	public void createMatch();
 }
