package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyCategories")
public class SurveyCategories extends PaginatedModel{


       @JsonProperty("surveyCategories") 
       List<SurveyCategory>  surveyCategories = new ArrayList<SurveyCategory>();
       public List<SurveyCategory> getSurveyCategories() {
           return surveyCategories;
       }

        public void setSurveyCategories(List<SurveyCategory> surveyProjects) {
           this.surveyCategories = surveyProjects;
       }
 
       public void addSurveyCategory(SurveyCategory surveyCategory) {
           this.surveyCategories.add(surveyCategory);
       }
 }
