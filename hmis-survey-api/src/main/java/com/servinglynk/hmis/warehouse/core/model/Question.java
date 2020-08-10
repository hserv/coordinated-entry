package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.servinglynk.hmis.warehouse.util.QuestionDataTypeEnum;
import com.servinglynk.hmis.warehouse.util.QuestionTypeEnum;

@JsonRootName("question")
public class Question extends ClientModel{


      private UUID questionId;

      private String projectGroupId;
      
      private String questionDescription;

      @NotBlank(message="Question Display Text is required")
      @NotEmpty(message="Question Display Text is required")
      private String displayText;

      private QuestionDataTypeEnum questionDataType;

      private QuestionTypeEnum questionType;

//  	  @NotNull(message="Correct Value for Assessment is required.")
//  	  @NotEmpty(message="Correct Value for Assessment is required.")
      private String correctValueForAssessment;

      private boolean copyQuestionId;

      private boolean hudQuestion;

      private boolean locked;

      private int questionWeight;

      private UUID questionGroupId;

      private UUID pickListGroupId;
      
      private String updateUrlTemplate;
      
  	private String uriObjectField;
  	  
  	  private String pickListValues;


      public UUID getQuestionId(){
          return questionId;
      }
      public void setQuestionId(UUID questionId){
          this.questionId = questionId;
      }
      public String getProjectGroupId(){
          return projectGroupId;
      }
      public void setProjectGroupId(String projectGroupId){
          this.projectGroupId = projectGroupId;
      }
      public String getQuestionDescription(){
          return questionDescription;
      }
      public void setQuestionDescription(String questionDescription){
          this.questionDescription = questionDescription;
      }
      public String getDisplayText(){
          return displayText;
      }
      public void setDisplayText(String displayText){
          this.displayText = displayText;
      }
      public QuestionDataTypeEnum getQuestionDataType(){
          return questionDataType;
      }
      public void setQuestionDataType(QuestionDataTypeEnum questionDataType){
          this.questionDataType = questionDataType;
      }
      public QuestionTypeEnum getQuestionType(){
          return questionType;
      }
      public void setQuestionType(QuestionTypeEnum questionType){
          this.questionType = questionType;
      }
      public String getCorrectValueForAssessment(){
          return correctValueForAssessment;
      }
      public void setCorrectValueForAssessment(String correctValueForAssessment){
          this.correctValueForAssessment = correctValueForAssessment;
      }
      public boolean getCopyQuestionId(){
          return copyQuestionId;
      }
      public void setCopyQuestionId(boolean copyQuestionId){
          this.copyQuestionId = copyQuestionId;
      }
      public boolean getHudQuestion(){
          return hudQuestion;
      }
      public void setHudQuestion(boolean hudQuestion){
          this.hudQuestion = hudQuestion;
      }
      public boolean getLocked(){
          return locked;
      }
      public void setLocked(boolean locked){
          this.locked = locked;
      }
      public int getQuestionWeight(){
          return questionWeight;
      }
      public void setQuestionWeight(int questionWeight){
          this.questionWeight = questionWeight;
      }
      public UUID getQuestionGroupId(){
          return questionGroupId;
      }
      public void setQuestionGroupId(UUID questionGroupId){
          this.questionGroupId = questionGroupId;
      }
      public UUID getPickListGroupId(){
          return pickListGroupId;
      }
      public void setPickListGroupId(UUID pickListGroupId){
          this.pickListGroupId = pickListGroupId;
      }
	public String getUpdateUrlTemplate() {
		return updateUrlTemplate;
	}
	public void setUpdateUrlTemplate(String updateUrlTemplate) {
		this.updateUrlTemplate = updateUrlTemplate;
	}
	public String getUriObjectField() {
		return uriObjectField;
	}
	public void setUriObjectField(String uriObjectField) {
		this.uriObjectField = uriObjectField;
	}
	public String getPickListValues() {
		return pickListValues;
	}
	public void setPickListValues(String pickListValues) {
		this.pickListValues = pickListValues;
	}
 }
