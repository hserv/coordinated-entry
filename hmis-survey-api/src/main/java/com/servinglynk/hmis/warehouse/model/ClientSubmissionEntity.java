package com.servinglynk.hmis.warehouse.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Table(name="CLIENT_SUBMISSION_ENTITY",schema="survey")
public class ClientSubmissionEntity {


	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="CLIENT_ID")
	private UUID clientId;
	
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="SURVEY_ID")
	private UUID surveyId; 
	
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="SUBMISSION_ID")
	private UUID submissionId;
    
    @org.hibernate.annotations.Type(type="pg-uuid")
   	@Column(name="GLOBAL_ENROLLMENT_ID")
   	private UUID globalEnrollmentId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	public UUID getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(UUID surveyId) {
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
	
    
    

}