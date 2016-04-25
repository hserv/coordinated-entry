package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "eligible_clients", schema = "ces")
public class EligibleClients implements java.io.Serializable {

	private UUID clientId;
	private ClientInfo clientInfo;
	private String surveyType;
	private Integer surveyScore;
	private String programType;
	private Boolean matched;
	private Date surveyDate;
	private String spdatLabel;
	private Set<MatchReservations> matchReservationses = new HashSet<>(0);

	public EligibleClients() {
	}

	public EligibleClients(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public EligibleClients(ClientInfo clientInfo, String surveyType, Integer surveyScore, String programType,
			Boolean matched, Date surveyDate, String spdatLabel, Set<MatchReservations> matchReservationses) {
		this.clientInfo = clientInfo;
		this.surveyType = surveyType;
		this.surveyScore = surveyScore;
		this.programType = programType;
		this.matched = matched;
		this.surveyDate = surveyDate;
		this.spdatLabel = spdatLabel;
		this.matchReservationses = matchReservationses;
	}

	@Id
	@Column(name = "client_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getClientId() {
		return this.clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public ClientInfo getClientInfo() {
		return this.clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	@Column(name = "survey_type")
	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	@Column(name = "survey_score")
	public Integer getSurveyScore() {
		return this.surveyScore;
	}

	public void setSurveyScore(Integer surveyScore) {
		this.surveyScore = surveyScore;
	}

	@Column(name = "program_type")
	public String getProgramType() {
		return this.programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	@Column(name = "matched")
	public Boolean getMatched() {
		return this.matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "survey_date")
	public Date getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@Column(name = "spdat_label")
	public String getSpdatLabel() {
		return this.spdatLabel;
	}

	public void setSpdatLabel(String spdatLabel) {
		this.spdatLabel = spdatLabel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eligibleClients" , cascade=CascadeType.ALL)
	
	public Set<MatchReservations> getMatchReservationses() {
		return this.matchReservationses;
	}

	public void setMatchReservationses(Set<MatchReservations> matchReservationses) {
		this.matchReservationses = matchReservationses;
	}

}
