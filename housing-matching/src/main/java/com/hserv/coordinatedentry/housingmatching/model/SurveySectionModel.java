package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveySectionModel {

	@JsonProperty(value = "section_score")
	private Integer sectionScore;

	@JsonProperty(value = "client_id")
	private String clientId;

	@JsonProperty(value = "spdat_label")
	private String spdatLabel;

	@JsonProperty(value="survey_date")
	private Date surveyDate;

	public String getSpdatLabel() {
		return spdatLabel;
	}

	public void setSpdatLabel(String spdatLabel) {
		this.spdatLabel = spdatLabel;
	}

	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	public Integer getSectionScore() {
		return sectionScore;
	}

	public void setSectionScore(Integer sectionScore) {
		this.sectionScore = sectionScore;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
