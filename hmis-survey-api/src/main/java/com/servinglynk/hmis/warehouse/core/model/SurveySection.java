package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveySection")
public class SurveySection extends ClientModel{


      private UUID surveySectionId;

      @NotBlank(message="Section Text is required")
      @NotEmpty(message="Section Text is required")
      private String sectionText;

      private String sectionDetail;

      private int sectionWeight;
      
      @NotEmpty(message="Order is required")
      private int order;

      private UUID surveyId;



      public UUID getSurveySectionId(){
          return surveySectionId;
      }
      public void setSurveySectionId(UUID surveySectionId){
          this.surveySectionId = surveySectionId;
      }
      public String getSectionText(){
          return sectionText;
      }
      public void setSectionText(String sectionText){
          this.sectionText = sectionText;
      }
      public String getSectionDetail(){
          return sectionDetail;
      }
      public void setSectionDetail(String sectionDetail){
          this.sectionDetail = sectionDetail;
      }
      public int getSectionWeight(){
          return sectionWeight;
      }
      public void setSectionWeight(int sectionWeight){
          this.sectionWeight = sectionWeight;
      }
      public int getOrder(){
          return order;
      }
      public void setOrder(int order){
          this.order = order;
      }
      public UUID getSurveyId(){
          return surveyId;
      }
      public void setSurveyId(UUID surveyId){
          this.surveyId = surveyId;
      }

 }
