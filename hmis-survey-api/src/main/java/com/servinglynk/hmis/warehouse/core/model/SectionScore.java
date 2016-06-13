package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("sectionScore")
public class SectionScore extends ClientModel{


      private UUID sectionScoreId;

      private UUID sectionId;

      private UUID surveyId;

      private UUID clientId;

      private int sectionScore;



      public UUID getSectionScoreId(){
          return sectionScoreId;
      }
      public void setSectionScoreId(UUID sectionScoreId){
          this.sectionScoreId = sectionScoreId;
      }
      public UUID getSectionId(){
          return sectionId;
      }
      public void setSectionId(UUID sectionId){
          this.sectionId = sectionId;
      }
      public UUID getSurveyId(){
          return surveyId;
      }
      public void setSurveyId(UUID surveyId){
          this.surveyId = surveyId;
      }
      public UUID getClientId(){
          return clientId;
      }
      public void setClientId(UUID clientId){
          this.clientId = clientId;
      }
      public int getSectionScore(){
          return sectionScore;
      }
      public void setSectionScore(int sectionScore){
          this.sectionScore = sectionScore;
      }

 }
