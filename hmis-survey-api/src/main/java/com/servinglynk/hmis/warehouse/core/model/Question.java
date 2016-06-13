package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("question")
public class Question extends ClientModel{


      private UUID questionId;

      private String projectGroupId;

      private String questionDescription;

      private String displayText;

      private String questionDataType;

      private String questionType;

      private String correctValueForAssessment;

      private boolean copyQuestionId;

      private boolean hudQuestion;

      private boolean locked;

      private int questionWeight;

      private UUID questionGroupId;

      private UUID pickListGroupId;



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
      public String getQuestionDataType(){
          return questionDataType;
      }
      public void setQuestionDataType(String questionDataType){
          this.questionDataType = questionDataType;
      }
      public String getQuestionType(){
          return questionType;
      }
      public void setQuestionType(String questionType){
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

 }
