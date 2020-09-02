package com.servinglynk.hmis.warehouse.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="CLIENT_SURVEY_SUBMISSION",schema="survey")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientSurveySubmissionEntity extends BaseEntity {


	@Id
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	

	@JoinColumn(name="CLIENT_ID")
	@OneToOne(fetch=FetchType.LAZY)
	private ClientEntity clientId;
	
	@JoinColumn(name="SURVEY_ID")
	@OneToOne(fetch=FetchType.LAZY)
	private SurveyEntity surveyId; 
	
	@Type(type="pg-uuid")
	@Column(name="SUBMISSION_ID")
	private UUID submissionId;
    
	@Type(type="pg-uuid")
   	@Column(name="GLOBAL_ENROLLMENT_ID")
   	private UUID globalEnrollmentId;
	
   	@Column(name="HMIS_POST_STATUS")
   	private String hmisPostStatus;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")	
	@Column(name="SUBMISSION_DATE")
	private LocalDateTime submissionDate;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")	
	@Column(name="ENTRY_DATE")
	private LocalDate entryDate;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")	
	@Column(name="EXIT_DATE")
	private LocalDate exitDate;

	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")	
	@Column(name="INFORMATION_DATE")
	private LocalDate informationDate;
	
	@Column(name="SURVEY_CATEGORY")
	private String surveyCategory;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ClientEntity getClientId() {
		return clientId;
	}

	public void setClientId(ClientEntity clientId) {

		this.clientId = clientId;
	}

	public SurveyEntity getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(SurveyEntity surveyId) {
		this.surveyId = surveyId;
	}

	public UUID getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(UUID submissionId) {
		this.submissionId = submissionId;
	}

	public UUID getGlobalEnrollmentId() {
		return globalEnrollmentId;
	}

	public void setGlobalEnrollmentId(UUID globalEnrollmentId) {
		this.globalEnrollmentId = globalEnrollmentId;
	}

	public LocalDateTime getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDateTime submissionDate) {
		this.submissionDate = submissionDate;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public LocalDate getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}

	public LocalDate getInformationDate() {
		return informationDate;
	}

	public void setInformationDate(LocalDate informationDate) {
		this.informationDate = informationDate;
	}
	
	public String getHmisPostStatus() {
		return hmisPostStatus;
	}

	public void setHmisPostStatus(String hmisPostStatus) {
		this.hmisPostStatus = hmisPostStatus;
	}

	public String getSurveyCategory() {
		return surveyCategory;
	}

	public void setSurveyCategory(String surveyCaterogy) {
		this.surveyCategory = surveyCaterogy;
	}
}