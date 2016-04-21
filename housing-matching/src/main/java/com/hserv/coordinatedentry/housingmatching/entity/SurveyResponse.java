package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "survey_response", schema = "ces")
public class SurveyResponse implements java.io.Serializable {

	private String surveyQuestionId;
	private String responseValue;
	private String responseSubassessment;
	private Integer questionScore;
	private String clientId;
	private String appId;
	private Date effectiveDate;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;

	public SurveyResponse() {
	}

	@Id
	@Column(name = "survey_question_id", length = 10)
	public String getSurveyQuestionId() {
		return this.surveyQuestionId;
	}

	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	@Column(name = "response_value", length = 50)
	public String getResponseValue() {
		return this.responseValue;
	}

	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}

	@Column(name = "response_subassessment", length = 50)
	public String getResponseSubassessment() {
		return this.responseSubassessment;
	}

	public void setResponseSubassessment(String responseSubassessment) {
		this.responseSubassessment = responseSubassessment;
	}

	@Column(name = "question_score")
	public Integer getQuestionScore() {
		return this.questionScore;
	}

	public void setQuestionScore(Integer questionScore) {
		this.questionScore = questionScore;
	}

	@Column(name = "client_id", length = 10)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "app_id", length = 10)
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "effective_date", length = 13)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "date_created", length = 13)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "date_updated", length = 13)
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "user_id", length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}


