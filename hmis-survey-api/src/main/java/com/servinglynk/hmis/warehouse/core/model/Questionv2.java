package com.servinglynk.hmis.warehouse.core.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.servinglynk.hmis.warehouse.util.QuestionDataTypeEnum;
import com.servinglynk.hmis.warehouse.util.QuestionTypeEnum;

@JsonRootName("question")
public class Questionv2 extends ClientModel {

	private UUID questionId;

	private String projectGroupId;

	private String questionDescription;

	@NotBlank(message = "Question Display Text is required")
	@NotEmpty(message = "Question Display Text is required")
	private String displayText;

	private String questionDataType;

	private String questionType;

	// @NotNull(message="Correct Value for Assessment is required.")
	// @NotEmpty(message="Correct Value for Assessment is required.")
	private String correctValueForAssessment;

	private boolean copyQuestionId;

	private boolean hudQuestion;

	private boolean locked;

	private int questionWeight;

	private UUID questionGroupId;

	private String pickListValues;

	private String definition;

	private boolean visibility;

	private String category;

	private String subcategory;

	public UUID getQuestionId() {
		return questionId;
	}

	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
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

	public String getQuestionDataType() {
		return questionDataType;
	}

	public void setQuestionDataType(String questionDataType) {
		this.questionDataType = questionDataType;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getCorrectValueForAssessment() {
		return correctValueForAssessment;
	}

	public void setCorrectValueForAssessment(String correctValueForAssessment) {
		this.correctValueForAssessment = correctValueForAssessment;
	}

	public boolean getCopyQuestionId() {
		return copyQuestionId;
	}

	public void setCopyQuestionId(boolean copyQuestionId) {
		this.copyQuestionId = copyQuestionId;
	}

	public boolean getHudQuestion() {
		return hudQuestion;
	}

	public void setHudQuestion(boolean hudQuestion) {
		this.hudQuestion = hudQuestion;
	}

	public boolean getLocked() {
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

	public UUID getQuestionGroupId() {
		return questionGroupId;
	}

	public void setQuestionGroupId(UUID questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	public String getPickListValues() {
		return pickListValues;
	}

	public void setPickListValues(String pickListValues) {
		this.pickListValues = pickListValues;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
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
}