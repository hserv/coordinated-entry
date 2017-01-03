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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="SURVEY_SECTION",schema="survey")
public class SurveySectionEntity extends BaseEntity {
	
	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="SURVEY_ID")
	private SurveyEntity surveyEntity;
	
	@Column(name="SECTION_TEXT")
	private String sectionText;
	
	@Column(name="SECTION_DETAIL")
	private String sectionDetail;
	
	@Column(name="SECTION_WEIGHT")
	private int sectionweight;
	
	@Column(name="SECTION_ORDER")
	private int order;
	
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
	public String getSectionText() {
		return sectionText;
	}
	public void setSectionText(String sectionText) {
		this.sectionText = sectionText;
	}
	public String getSectionDetail() {
		return sectionDetail;
	}
	public void setSectionDetail(String sectionDetail) {
		this.sectionDetail = sectionDetail;
	}
	public int getSectionweight() {
		return sectionweight;
	}
	public void setSectionweight(int sectionweight) {
		this.sectionweight = sectionweight;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}