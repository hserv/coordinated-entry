package com.hserv.coordinatedentry.housingmatching.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class MatchReservationModel {
	private UUID reservationId;
	private EligibleClientModel eligibleClients;
	private HousingInventoryModel housingInventory;
	private UUID housingUnitId;
	private String noteId;
	private Date matchDate;
	private Integer matchStatus;
	private Integer reservationAdults;
	private Integer reservationChildren;
	private Boolean manualMatch;
	private Boolean inactive;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
	private UUID processId;

	public UUID getReservationId() {
		return reservationId;
	}
	public void setReservationId(UUID reservationId) {
		this.reservationId = reservationId;
	}
	public EligibleClientModel getEligibleClients() {
		return eligibleClients;
	}
	public void setEligibleClients(EligibleClientModel eligibleClients) {
		this.eligibleClients = eligibleClients;
	}
	public HousingInventoryModel getHousingInventory() {
		return housingInventory;
	}
	public void setHousingInventory(HousingInventoryModel housingInventory) {
		this.housingInventory = housingInventory;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public Integer getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(Integer matchStatus) {
		this.matchStatus = matchStatus;
	}
	public Integer getReservationAdults() {
		return reservationAdults;
	}
	public void setReservationAdults(Integer reservationAdults) {
		this.reservationAdults = reservationAdults;
	}
	public Integer getReservationChildren() {
		return reservationChildren;
	}
	public void setReservationChildren(Integer reservationChildren) {
		this.reservationChildren = reservationChildren;
	}
	public Boolean getManualMatch() {
		return manualMatch;
	}
	public void setManualMatch(Boolean manualMatch) {
		this.manualMatch = manualMatch;
	}
	public Boolean getInactive() {
		return inactive;
	}
	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public UUID getHousingUnitId() {
		return housingUnitId;
	}
	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}
	public UUID getProcessId() {
		return processId;
	}
	public void setProcessId(UUID processId) {
		this.processId = processId;
	}
}