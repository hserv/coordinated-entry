package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "match_reservations", schema = "housing_match")
public class Match implements java.io.Serializable {

	private UUID reservationId;
	private EligibleClient eligibleClient;
	private String noteId;
	private Date matchDate;
	private String matchStatus;
	private Integer reservationAdults;
	private Integer reservationChildren;
	private Boolean manualMatch;
	private Boolean inactive;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;
	private UUID housingUnitId;
	private Set<Note> notes = new HashSet<>(0);

	public Match() {
	}

	public Match(UUID reservationId, EligibleClient eligibleClient, UUID housingUnitId) {
		this.reservationId = reservationId;
		this.eligibleClient = eligibleClient;
		this.housingUnitId = housingUnitId;
	}

	public Match(UUID reservationId, EligibleClient eligibleClients, String noteId, Date matchDate,
			String matchStatus, Integer reservationAdults, Integer reservationChildren, Boolean manualMatch,
			Boolean inactive, Date dateCreated, Date dateUpdated, String userId, UUID housingUnitId,
			Set<Note> notes) {
		this.reservationId = reservationId;
		this.eligibleClient = eligibleClients;
		this.noteId = noteId;
		this.matchDate = matchDate;
		this.matchStatus = matchStatus;
		this.reservationAdults = reservationAdults;
		this.reservationChildren = reservationChildren;
		this.manualMatch = manualMatch;
		this.inactive = inactive;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.userId = userId;
		this.housingUnitId = housingUnitId;
		this.notes = notes;
	}

	@Id
	@Column(name = "reservation_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getReservationId() {
		return this.reservationId;
	}

	public void setReservationId(UUID reservationId) {
		this.reservationId = reservationId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	public EligibleClient getEligibleClient() {
		return this.eligibleClient;
	}

	public void setEligibleClient(EligibleClient eligibleClient) {
		this.eligibleClient = eligibleClient;
	}

	@Column(name = "note_id")
	public String getNoteId() {
		return this.noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "match_date", length = 13)
	public Date getMatchDate() {
		return this.matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	@Column(name = "match_status")
	public String getMatchStatus() {
		return this.matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	@Column(name = "reservation_adults")
	public Integer getReservationAdults() {
		return this.reservationAdults;
	}

	public void setReservationAdults(Integer reservationAdults) {
		this.reservationAdults = reservationAdults;
	}

	@Column(name = "reservation_children")
	public Integer getReservationChildren() {
		return this.reservationChildren;
	}

	public void setReservationChildren(Integer reservationChildren) {
		this.reservationChildren = reservationChildren;
	}

	@Column(name = "manual_match")
	public Boolean getManualMatch() {
		return this.manualMatch;
	}

	public void setManualMatch(Boolean manualMatch) {
		this.manualMatch = manualMatch;
	}

	@Column(name = "inactive")
	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created", length = 13)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_updated", length = 13)
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "housing_unit_id", nullable = false)
	public UUID getHousingUnitId() {
		return this.housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "matchReservations")
	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

}
