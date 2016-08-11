package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MatchReservationModel {
	private UUID reservationId;
	private EligibleClientModel eligibleClients;
	private HousingInventoryModel housingInventory;
	private UUID housingUnitId;
	private String noteId;
	private Date matchDate;
	private String matchStatus;
	private Integer reservationAdults;
	private Integer reservationChildren;
	private Boolean manualMatch;
	private Boolean inactive;
	private Date dateCreated;
	private Date dateUpdated;
	private Set<NoteModel> notes = new HashSet(0);
	
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
	public String getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(String matchStatus) {
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Set<NoteModel> getNotes() {
		return notes;
	}
	public void setNotes(Set<NoteModel> notes) {
		this.notes = notes;
	}
	
	
	public UUID getHousingUnitId() {
		return housingUnitId;
	}
	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}
	@Override
	public String toString() {
		return "MatchReservationModel [reservationId=" + reservationId + ", eligibleClients=" + eligibleClients
				+ ", housingInventory=" + housingInventory + ", noteId=" + noteId + ", matchDate=" + matchDate
				+ ", matchStatus=" + matchStatus + ", reservationAdults=" + reservationAdults + ", reservationChildren="
				+ reservationChildren + ", manualMatch=" + manualMatch + ", inactive=" + inactive + ", dateCreated="
				+ dateCreated + ", dateUpdated=" + dateUpdated + ", notes=" + notes + "]";
	}
	
}
