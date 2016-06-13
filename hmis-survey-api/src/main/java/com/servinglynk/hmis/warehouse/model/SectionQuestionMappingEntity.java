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
@Table(name="SECTION_QUESTION_MAPPING",schema="survey")
public class SectionQuestionMappingEntity extends BaseEntity {
	
	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="SECTION_ID")
	private SurveySectionEntity surveySectionEntity;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private QuestionEntity questionEntity;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_GROUP_ID")
	private QuestionGroupEntity questionGroupEntity;
	
	@Column(name="IS_REQUIRED")
	private boolean required;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public SurveySectionEntity getSurveySectionEntity() {
		return surveySectionEntity;
	}
	public void setSurveySectionEntity(SurveySectionEntity surveySectionEntity) {
		this.surveySectionEntity = surveySectionEntity;
	}
	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}
	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}
	public QuestionGroupEntity getQuestionGroupEntity() {
		return questionGroupEntity;
	}
	public void setQuestionGroupEntity(QuestionGroupEntity questionGroupEntity) {
		this.questionGroupEntity = questionGroupEntity;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
}