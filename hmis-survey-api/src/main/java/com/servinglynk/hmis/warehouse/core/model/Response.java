package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("response")
public class Response extends ClientModel{


      private UUID responseId;

      private String responseText;

      private int questionScore;

      private String appId;

      private UUID sectionId;

      private UUID questionId;

      private UUID clientId;

      private UUID surveyId;
      
      
    public Response() {
		super();
	}
	public Response(UUID responseId) {
		super();
		this.responseId = responseId;
	}
	
	
	public UUID getResponseId(){
          return responseId;
      }
      public void setResponseId(UUID responseId){
          this.responseId = responseId;
      }
      public String getResponseText(){
          return responseText;
      }
      public void setResponseText(String responseText){
          this.responseText = responseText;
      }
      public int getQuestionScore(){
          return questionScore;
      }
      public void setQuestionScore(int questionScore){
          this.questionScore = questionScore;
      }
      public String getAppId(){
          return appId;
      }
      public void setAppId(String appId){
          this.appId = appId;
      }
      public UUID getSectionId(){
          return sectionId;
      }
      public void setSectionId(UUID sectionId){
          this.sectionId = sectionId;
      }
      public UUID getQuestionId(){
          return questionId;
      }
      public void setQuestionId(UUID questionId){
          this.questionId = questionId;
      }
      public UUID getClientId(){
          return clientId;
      }
      public void setClientId(UUID clientId){
          this.clientId = clientId;
      }
      public UUID getSurveyId(){
          return surveyId;
      }
      public void setSurveyId(UUID surveyId){
          this.surveyId = surveyId;
      }

 }
