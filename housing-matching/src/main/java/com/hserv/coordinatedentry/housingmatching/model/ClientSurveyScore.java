package com.hserv.coordinatedentry.housingmatching.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.servinglynk.hmis.warehouse.core.model.JsonDateDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonDateTimeSerializer;

public class ClientSurveyScore {

	private UUID surveyId;
	private UUID clientId;
	private Long surveyScore;
	private String programType;
	@JsonDeserialize(using=JsonDateDeserializer.class)
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	private LocalDateTime surveyDate;
	private String surveyTagValue;
	private String projectGroupCode;
	
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
	public String getProgramType() {
		return programType;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public LocalDateTime getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(LocalDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}
	public String getSurveyTagValue() {
		return surveyTagValue;
	}
	public void setSurveyTagValue(String surveyTagValue) {
		this.surveyTagValue = surveyTagValue;
	}
	public String getProjectGroupCode() {
		return projectGroupCode;
	}
	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}
}