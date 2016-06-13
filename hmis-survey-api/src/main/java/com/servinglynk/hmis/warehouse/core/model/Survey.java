package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("survey")
public class Survey extends ClientModel{


      private UUID surveyId;

      private String surveyTitle;

      private String surveyOwner;

      private String tagValue;

      private String projectGroupId;

      private boolean locked;

      private boolean copySurveyId;



      public UUID getSurveyId(){
          return surveyId;
      }
      public void setSurveyId(UUID surveyId){
          this.surveyId = surveyId;
      }
      public String getSurveyTitle(){
          return surveyTitle;
      }
      public void setSurveyTitle(String surveyTitle){
          this.surveyTitle = surveyTitle;
      }
      public String getSurveyOwner(){
          return surveyOwner;
      }
      public void setSurveyOwner(String surveyOwner){
          this.surveyOwner = surveyOwner;
      }
      public String getTagValue(){
          return tagValue;
      }
      public void setTagValue(String tagValue){
          this.tagValue = tagValue;
      }
      public String getProjectGroupId(){
          return projectGroupId;
      }
      public void setProjectGroupId(String projectGroupId){
          this.projectGroupId = projectGroupId;
      }
   
      public boolean getCopySurveyId(){
          return copySurveyId;
      }
      public void setCopySurveyId(boolean copySurveyId){
          this.copySurveyId = copySurveyId;
      }
      public boolean getLocked(){
          return locked;
      }
      public void setLocked(boolean locked){
          this.locked = locked;
      }

 }
