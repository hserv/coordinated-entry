package com.hserv.coordinatedentry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class SectionScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="response_fk_id")
	@JsonBackReference
	private ResponseStorage responseStorage;
	
	private String clientId;
	
	private Integer surveyId;
	
	private Integer sectionId;
	
	private float sectionScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ResponseStorage getResponseStorage() {
		return responseStorage;
	}

	public void setResponseStorage(ResponseStorage responseStorage) {
		this.responseStorage = responseStorage;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public float getSectionScore() {
		return sectionScore;
	}

	public void setSectionScore(float sectionScore) {
		this.sectionScore = sectionScore;
	}
	
}
