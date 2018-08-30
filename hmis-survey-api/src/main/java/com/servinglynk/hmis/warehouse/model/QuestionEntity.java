package com.servinglynk.hmis.warehouse.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

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
	
	@Column(name="UPDATE_URL_TEMPLATE")
	private String updateUrlTemplate;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_GROUP_ID")
	private QuestionGroupEntity questionGroupEntity;
	
	@ManyToOne
	@JoinColumn(name="PICKLIST_GROUP_ID")
	private PickListGroupEntity pickListGroupEntity;
	
	@Column(name="PICKLIST_VALUES")
	private String picklistValues;
	
	@Column(name="definition")
	private String definition;
	
	@Column(name="visibility")
	private Boolean visibility;

	@Column(name="category")
	private String category;

	@Column(name="subcategory")
	private String subcategory;
	
	
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
	public String getPicklistValues() {
		return picklistValues;
	}
	public void setPicklistValues(String picklistValues) {
		this.picklistValues = picklistValues;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public Boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getUpdateUrlTemplate() {
		return updateUrlTemplate;
	}
	public void setUpdateUrlTemplate(String updateUrlTemplate) {
		this.updateUrlTemplate = updateUrlTemplate;
	}
}