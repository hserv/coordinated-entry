package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class EligibleClientModel {

	private String clientId;
	private ClientInfoModel clientInfo;
	private Integer surveyScore;
	private String category;
	private Boolean matched;
	private Date surveyDate;
	private String spdatLabel;

	private Set<MatchReservationModel> matchReservations = new HashSet<>(0);

	@NotNull
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	//@NotNull
	public ClientInfoModel getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfoModel clientInfo) {
		this.clientInfo = clientInfo;
	}

	@NotNull
	public Integer getSurveyScore() {
		return surveyScore;
	}

	public void setSurveyScore(Integer surveyScore) {
		this.surveyScore = surveyScore;
	}

	@NotNull
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@NotNull
	public Boolean getMatched() {
		return matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	@NotNull
	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@NotNull
	public String getSpdatLabel() {
		return spdatLabel;
	}

	public void setSpdatLabel(String spdatLabel) {
		this.spdatLabel = spdatLabel;
	}

	public Set<MatchReservationModel> getMatchReservations() {
		return matchReservations;
	}

	public void setMatchReservations(Set<MatchReservationModel> matchReservations) {
		this.matchReservations = matchReservations;
	}

	@Override
	public String toString() {
		return "EligibleClientModel [clientId=" + clientId + ", clientInfo=" + clientInfo + ", spdatscore="
				+ surveyScore + ", category=" + category + ", matched=" + matched + ", surveyDate=" + surveyDate
				+ ", spdatLabel=" + spdatLabel + ", matchReservations=" + matchReservations + "]";
	}

}