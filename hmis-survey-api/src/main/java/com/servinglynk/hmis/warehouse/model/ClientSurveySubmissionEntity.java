package com.servinglynk.hmis.warehouse.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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

		
	@Type(type="pg-uuid")
	@Column(name="SURVEY_ID")
	private UUID surveyId; 
	
	@Type(type="pg-uuid")
	@Column(name="SUBMISSION_ID")
	private UUID submissionId;
    
	@Type(type="pg-uuid")
   	@Column(name="GLOBAL_ENROLLMENT_ID")
   	private UUID globalEnrollmentId;

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