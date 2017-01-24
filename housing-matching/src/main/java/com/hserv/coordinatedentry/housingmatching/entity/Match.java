package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "match_reservations", schema = "housing_inventory")
public class Match extends BaseEntity {

	@Id
	@Column(name = "reservation_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID reservationId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = false)
	private EligibleClient eligibleClient;

	@Column(name = "note_id")
	private String noteId;

	@Temporal(TemporalType.DATE)
	@Column(name = "match_date", length = 13)
	private Date matchDate;

	@Column(name = "match_status")
	private Integer matchStatus;

	@Column(name = "reservation_adults")
	private Integer reservationAdults;

	@Column(name = "reservation_children")
	private Integer reservationChildren;

	@Column(name = "manual_match")
	private Boolean manualMatch;

	@Column(name = "inactive")
	private Boolean inactive;

	@Column(name = "housing_unit_id", nullable = false)
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID housingUnitId;

	@Column(name = "process_id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID processId;

	@Column(name = "program_type")
	private String programType;

	@Column(name = "status_id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID statusId;

	@Column(name="deleted")
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	

	
	public UUID getReservationId() {
		return this.reservationId;
	}

	public void setReservationId(UUID reservationId) {
		this.reservationId = reservationId;
	}

	public EligibleClient getEligibleClient() {
		return this.eligibleClient;
	}

	public void setEligibleClient(EligibleClient eligibleClient) {
		this.eligibleClient = eligibleClient;
	}

	public String getNoteId() {
		return this.noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public Date getMatchDate() {
		return this.matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Integer getMatchStatus() {
		return this.matchStatus;
	}

	public void setMatchStatus(Integer matchStatus) {
		this.matchStatus = matchStatus;
	}

	public Integer getReservationAdults() {
		return this.reservationAdults;
	}

	public void setReservationAdults(Integer reservationAdults) {
		this.reservationAdults = reservationAdults;
	}

	public Integer getReservationChildren() {
		return this.reservationChildren;
	}

	public void setReservationChildren(Integer reservationChildren) {
		this.reservationChildren = reservationChildren;
	}

	public Boolean getManualMatch() {
		return this.manualMatch;
	}

	public void setManualMatch(Boolean manualMatch) {
		this.manualMatch = manualMatch;
	}

	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public UUID getHousingUnitId() {
		return this.housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	public String getProgramType() {
		return this.programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public UUID getStatusId() {
		return statusId;
	}

	public void setStatusId(UUID statusId) {
		this.statusId = statusId;
	}

	public UUID getProcessId() {
		return processId;
	}

	public void setProcessId(UUID processId) {
		this.processId = processId;
	}
}