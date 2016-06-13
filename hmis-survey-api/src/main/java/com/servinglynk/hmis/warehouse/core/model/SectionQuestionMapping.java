package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("question")
public class SectionQuestionMapping extends ClientModel{

    private Question question;
    private boolean required;
    private SurveySection surveySection;
    private UUID sectionQuestionId;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public SurveySection getSurveySection() {
		return surveySection;
	}
	public void setSurveySection(SurveySection surveySection) {
		this.surveySection = surveySection;
	}
	public UUID getSectionQuestionId() {
		return sectionQuestionId;
	}
	public void setSectionQuestionId(UUID sectionQuestionId) {
		this.sectionQuestionId = sectionQuestionId;
	}    
}