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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "match_reservations", schema = "housing_inventory")
public class Match implements java.io.Serializable {

	private static final long serialVersionUID = 2155758869863436437L;

	private UUID reservationId;
	private EligibleClient eligibleClient;
	private String noteId;
	private Date matchDate;
	private Integer matchStatus;
	private Integer reservationAdults;
	private Integer reservationChildren;
	private Boolean manualMatch;
	private Boolean inactive;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;
	private UUID housingUnitId;
	private String programType;
	private UUID statusId;
	
	@Id
	@Column(name = "reservation_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
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
	public Integer getMatchStatus() {
		return this.matchStatus;
	}

	public void setMatchStatus(Integer matchStatus) {
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
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	public UUID getHousingUnitId() {
		return this.housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}

	@PrePersist
	protected void onCreate() {
		dateCreated = dateUpdated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		dateUpdated = new Date();
	}

	@Column(name = "program_type")
	public String getProgramType() {
		return this.programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	@Column(name="status_id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	public UUID getStatusId() {
		return statusId;
	}

	public void setStatusId(UUID statusId) {
		this.statusId = statusId;
	}
}