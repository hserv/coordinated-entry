package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EligibleClientModel {

	private String clientId;
	private ClientInfoModel clientInfo;
	private Integer surveyScore;
	private String programType;
	private Boolean matched;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
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
	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
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
				+ surveyScore + ", programType=" + programType + ", matched=" + matched + ", surveyDate=" + surveyDate
				+ ", spdatLabel=" + spdatLabel + ", matchReservations=" + matchReservations + "]";
	}

}