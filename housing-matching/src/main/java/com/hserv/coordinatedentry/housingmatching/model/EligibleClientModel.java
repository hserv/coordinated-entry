package com.hserv.coordinatedentry.housingmatching.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.housingmatching.enums.SpdatLabelEnum;
import com.servinglynk.hmis.warehouse.core.model.JsonDateDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonDateTimeSerializer;

public class EligibleClientModel {

	private UUID clientId;
	private String surveyType;
	private Integer surveyScore;
	private String programType;
	private Boolean matched;
	@JsonDeserialize(using=JsonDateDeserializer.class)
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	private LocalDateTime surveyDate;
	private SpdatLabelEnum spdatLabel;
	@Length(min=5,max=5,message="Invalid zip code")
	private Integer zipcode;
	
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public String getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	public Integer getSurveyScore() {
		return surveyScore;
	}
	public void setSurveyScore(Integer surveyScore) {
		this.surveyScore = surveyScore;
	}
	public String getProgramType() {
		return programType;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public Boolean getMatched() {
		return matched;
	}
	public void setMatched(Boolean matched) {
		this.matched = matched;
	}
	public LocalDateTime getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(LocalDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}
	public SpdatLabelEnum getSpdatLabel() {
		return spdatLabel;
	}
	public void setSpdatLabel(SpdatLabelEnum spdatLabel) {
		this.spdatLabel = spdatLabel;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
}