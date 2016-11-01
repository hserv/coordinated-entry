package com.servinglynk.hmis.warehouse.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="RESPONSE",schema="survey")
public class ResponseEntity extends BaseEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="RESPONSE_TEXT")
	private String responseText;
	
	@Column(name="QUESTION_SCORE")
	private int questionScore;
	
	@Column(name="APP_ID")
	private String appId;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")	
	@Column(name="EFFECTIVE_DATE")
	private LocalDateTime effectiveDate;
	
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="CLIENT_ID")
	private UUID clientId;
	
	@ManyToOne
	@JoinColumn(name="SECTION_ID")
	private SurveySectionEntity surveySectionEntity;
	
	@ManyToOne
	@JoinColumn(name="SURVEY_ID")
	private SurveyEntity surveyEntity;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private QuestionEntity questionEntity;
	
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="submission_id")
	private UUID submissionId;
	
	
	public UUID getId() {
		return id;
	}
	
	@Column(name="REFUSED")
	public boolean refused;
	
	
	public void setId(UUID id) {
		this.id = id;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public int getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(int questionScore) {
		this.questionScore = questionScore;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public LocalDateTime getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public SurveySectionEntity getSurveySectionEntity() {
		return surveySectionEntity;
	}
	public void setSurveySectionEntity(SurveySectionEntity surveySectionEntity) {
		this.surveySectionEntity = surveySectionEntity;
	}
	public SurveyEntity getSurveyEntity() {
		return surveyEntity;
	}
	public void setSurveyEntity(SurveyEntity surveyEntity) {
		this.surveyEntity = surveyEntity;
	}
	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}
	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}
	public boolean isRefused() {
		return refused;
	}
	public void setRefused(boolean refused) {
		this.refused = refused;
	}
	public UUID getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(UUID submissionId) {
		this.submissionId = submissionId;
	}
}