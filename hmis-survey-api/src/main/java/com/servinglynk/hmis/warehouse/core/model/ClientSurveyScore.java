package com.servinglynk.hmis.warehouse.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ClientSurveyScore {

	private UUID surveyId;
	private UUID clientId;
	private Long surveyScore;
	private String projectGroupCode;
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	private LocalDateTime surveyDate;
	public UUID getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(UUID surveyId) {
		this.surveyId = surveyId;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public Long getSurveyScore() {
		return surveyScore;
	}
	public void setSurveyScore(Long surveyScore) {
		this.surveyScore = surveyScore;
	}
	public String getProjectGroupCode() {
		return projectGroupCode;
	}
	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}
	public LocalDateTime getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(LocalDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}
}