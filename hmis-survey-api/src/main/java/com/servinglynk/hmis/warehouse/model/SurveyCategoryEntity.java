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
@Table(name="SURVEY_CATEGORY",schema="survey")
public class SurveyCategoryEntity extends BaseEntity {
	
	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="SURVEY_ID")
	private SurveyEntity surveyEntity;
	
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="SURVEY_CATEGORY")
	private String surveyCategory;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public SurveyEntity getSurveyEntity() {
		return surveyEntity;
	}
	public void setSurveyEntity(SurveyEntity surveyEntity) {
		this.surveyEntity = surveyEntity;
	}
	public String getSurveyCategory() {
		return surveyCategory;
	}
	public void setSurveyCategory(String surveyCategory) {
		this.surveyCategory = surveyCategory;
	}
}