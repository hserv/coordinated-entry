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
@Table(name="QUESTION",schema="survey")
public class QuestionEntity extends BaseEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="PROJECT_GROUP_ID")
	private String projectGroupId;
	
	@Column(name="QUESTION_DESCRIPTION")
	private String questionDescription;
	
	@Column(name="DISPLAY_TEXT")
	private String displayText;
	
	@Column(name="QUESTION_TYPE")
	private String questionType;
	
	@Column(name="QUESTION_DATA_TYPE")
	private String questionDataType;
	
	@Column(name="CORRECT_VALUE_FOR_ASSESSMENT")
	private String correctValueForAssessment;
	
	@Column(name="IS_COPY_QUESTION_ID")
	private boolean isCopyQuestionId;
	
	@Column(name="HUD_QUESTION")
	private boolean hudQuestion;
	
	@Column(name="LOCKED")
	private boolean locked;
	@Column(name="QUESTION_WEIGHT")
	private int questionWeight;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_GROUP_ID")
	private QuestionGroupEntity questionGroupEntity;
	
	@ManyToOne
	@JoinColumn(name="PICKLIST_GROUP_ID")
	private PickListGroupEntity pickListGroupEntity;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getProjectGroupId() {
		return projectGroupId;
	}
	public void setProjectGroupId(String projectGroupId) {
		this.projectGroupId = projectGroupId;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionDataType() {
		return questionDataType;
	}
	public void setQuestionDataType(String questionDataType) {
		this.questionDataType = questionDataType;
	}
	public String getCorrectValueForAssessment() {
		return correctValueForAssessment;
	}
	public void setCorrectValueForAssessment(String correctValueForAssessment) {
		this.correctValueForAssessment = correctValueForAssessment;
	}
	public boolean isCopyQuestionId() {
		return isCopyQuestionId;
	}
	public void setCopyQuestionId(boolean isCopyQuestionId) {
		this.isCopyQuestionId = isCopyQuestionId;
	}
	public boolean isHudQuestion() {
		return hudQuestion;
	}
	public void setHudQuestion(boolean hudQuestion) {
		this.hudQuestion = hudQuestion;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public int getQuestionWeight() {
		return questionWeight;
	}
	public void setQuestionWeight(int questionWeight) {
		this.questionWeight = questionWeight;
	}
	public QuestionGroupEntity getQuestionGroupEntity() {
		return questionGroupEntity;
	}
	public void setQuestionGroupEntity(QuestionGroupEntity questionGroupEntity) {
		this.questionGroupEntity = questionGroupEntity;
	}
	public PickListGroupEntity getPickListGroupEntity() {
		return pickListGroupEntity;
	}
	public void setPickListGroupEntity(PickListGroupEntity pickListGroupEntity) {
		this.pickListGroupEntity = pickListGroupEntity;
	}
}