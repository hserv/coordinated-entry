package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "survey_section", schema = "survey")
public class SurveySection implements java.io.Serializable {

	private UUID id;
	private Integer sectionScore;
	private String clientId;
	private Integer responseId;

	public SurveySection() {
	}

	public SurveySection(UUID id) {
		this.id = id;
	}

	public SurveySection(UUID id, Integer sectionScore, String clientId, Integer responseId) {
		this.id = id;
		this.sectionScore = sectionScore;
		this.clientId = clientId;
		this.responseId = responseId;
	}

	@Id
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	@Column(name = "id", unique = true, nullable = false)
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Column(name = "section_score")
	public Integer getSectionScore() {
		return this.sectionScore;
	}

	public void setSectionScore(Integer sectionScore) {
		this.sectionScore = sectionScore;
	}

	@Column(name = "client_id")
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "response_id")
	public Integer getResponseId() {
		return this.responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

}
