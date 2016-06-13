package com.servinglynk.hmis.warehouse.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SECTION_SCORE",schema="survey")
public class SectionScoreEntity extends BaseEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="SECTION_SCORE")
	private int sectionScore;
	
	@ManyToOne
	@JoinColumn(name="SECTION_ID")
	private SurveySectionEntity sectionEntity;
	
	@ManyToOne
	@JoinColumn(name="SURVEY_ID")
	private SurveyEntity surveyEntity;
	
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="CLIENT_ID")
	private UUID clientId;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getSectionScore() {
		return sectionScore;
	}
	public void setSectionScore(int sectionScore) {
		this.sectionScore = sectionScore;
	}
	public SurveySectionEntity getSectionEntity() {
		return sectionEntity;
	}
	public void setSectionEntity(SurveySectionEntity sectionEntity) {
		this.sectionEntity = sectionEntity;
	}
	public SurveyEntity getSurveyEntity() {
		return surveyEntity;
	}
	public void setSurveyEntity(SurveyEntity surveyEntity) {
		this.surveyEntity = surveyEntity;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
}