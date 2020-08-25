package com.servinglynk.hmis.warehouse.core.model;

import java.util.UUID;

public class QuestionResponseModel  extends ClientModel {

	private UUID questionId;
	private String questionText;
	private String responseText;
	private UUID responseId;
	private String questionClassification;
	public UUID getQuestionId() {
		return questionId;
	}
	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public UUID getResponseId() {
		return responseId;
	}
	public void setResponseId(UUID responseId) {
		this.responseId = responseId;
	}
	public String getQuestionClassification() {
		return questionClassification;
	}
	public void setQuestionClassification(String questionClassification) {
		this.questionClassification = questionClassification;
	}
}
