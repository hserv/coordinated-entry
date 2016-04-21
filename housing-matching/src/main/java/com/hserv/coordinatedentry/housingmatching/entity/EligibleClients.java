package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "eligible_clients", schema = "ces")
public class EligibleClients implements java.io.Serializable {

	private UUID clientId;
	private ClientInfo clientInfo;
	private String surveyType;
	private Integer surveyScore;
	private String category;
	private Boolean matched;
	private Date surveyDate;
	private String spdatLabel;
	private Set<MatchReservations> matchReservationses = new HashSet<>(0);

	public EligibleClients() {
	}

	public EligibleClients(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public EligibleClients(ClientInfo clientInfo, String surveyType, Integer surveyScore, String category,
			Boolean matched, Date surveyDate, String spdatLabel, Set<MatchReservations> matchReservationses) {
		this.clientInfo = clientInfo;
		this.surveyType = surveyType;
		this.surveyScore = surveyScore;
		this.category = category;
		this.matched = matched;
		this.surveyDate = surveyDate;
		this.spdatLabel = spdatLabel;
		this.matchReservationses = matchReservationses;
	}

	//@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "clientInfo"))
	@Id
	//@GeneratedValue(generator = "generator")
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

	@Column(name = "category")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "matched")
	public Boolean getMatched() {
		return this.matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "survey_date", length = 13)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eligibleClients")
	public Set<MatchReservations> getMatchReservationses() {
		return this.matchReservationses;
	}

	public void setMatchReservationses(Set<MatchReservations> matchReservationses) {
		this.matchReservationses = matchReservationses;
	}

}
