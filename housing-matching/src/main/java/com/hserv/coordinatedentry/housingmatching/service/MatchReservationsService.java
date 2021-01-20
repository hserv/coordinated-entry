package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.entity.StatusNotesEntity;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchStatusModel;
import com.hserv.coordinatedentry.housingmatching.model.NoteModel;
import com.servinglynk.hmis.warehouse.core.model.Session;

public interface MatchReservationsService {

	public boolean createMatchReservation(MatchReservationModel matchReservationModel);
	
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels);
	
//	public int updateMatchStatus(String matchStatus, String reservationId);
	
//	public int updateMatchStatusAndMAnualMatch(String matchStatus, boolean manualMatch, String reservationId);
	
	public boolean deleteAll();
	
	public Page<Match> findAll(String q, Pageable pageable, String projectGroupCode);
	
	public boolean deleteByClientId(UUID clientId);
	
	public MatchReservationModel findByClientId(UUID clientId);
	
	public boolean updateByClientId(UUID clientId, MatchReservationModel matchReservationModel);
	
	
	public void updateMatchStatus(UUID reservationID,UUID clientId,MatchStatusModel statusModel,String auditUser,Session session,String trustedApp) throws Exception;
	
	List<MatchStatusModel> getMatchStatusHistory(UUID reservationId,UUID clientId,String projectGroupCode) throws Exception;
	
	
	void matchingProcess(Integer maxClients, Session session , String trustedAppId, UUID processId);
	void addStatusNote(UUID reservationId,Integer statusCode,NoteModel noteModel) throws Exception;
	void deleteNote(UUID nodeId) throws Exception;

	public Page<StatusNotesEntity> getStatusNote(UUID resevationId, Integer statuscode,Pageable pageable);

	public void createManualMatch(UUID dedulpClient, MatchReservationModel matchReservationModel) throws Exception;
 }
