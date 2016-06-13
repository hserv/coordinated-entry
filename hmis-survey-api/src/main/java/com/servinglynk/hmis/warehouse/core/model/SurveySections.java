package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveySections")
public class SurveySections extends PaginatedModel{


       @JsonProperty("surveySections") 
       List<SurveySection>surveySections = new ArrayList<SurveySection>();
       public List<SurveySection> getSurveySections() {
           return surveySections;
       }

        public void setSurveySections(List<SurveySection> surveySections) {
           this.surveySections = surveySections;
       }
 
       public void addSurveySection(SurveySection surveySection) {
           this.surveySections.add(surveySection);
       }
 }
