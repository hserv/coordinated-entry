package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EligibleClients 
 */
@Entity
@Table(name = "eligible_clients", schema = "housing")
public class EligibleClients implements java.io.Serializable {

	private UUID clientId;
	private ClientInfo clientInfo;
	private Integer spdatscore;
	private String category;
	private Boolean matched;
	private Date surveyDate;
	private String spdatLabel;
	private Set<MatchReservations> matchReservationses = new HashSet(0);

	public EligibleClients() {
	}

	public EligibleClients(UUID clientId, ClientInfo clientInfo) {
		this.clientId = clientId;
		this.clientInfo = clientInfo;
	}

	public EligibleClients(UUID clientId, ClientInfo clientInfo, Integer spdatscore, String category,
			Boolean matched, Date surveyDate, String spdatLabel,
			Set<MatchReservations> matchReservationses) {
		this.clientId = clientId;
		this.clientInfo = clientInfo;
		this.spdatscore = spdatscore;
		this.category = category;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	public ClientInfo getClientInfo() {
		return this.clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	@Column(name = "spdatscore")
	public Integer getSpdatscore() {
		return this.spdatscore;
	}

	public void setSpdatscore(Integer spdatscore) {
		this.spdatscore = spdatscore;
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
