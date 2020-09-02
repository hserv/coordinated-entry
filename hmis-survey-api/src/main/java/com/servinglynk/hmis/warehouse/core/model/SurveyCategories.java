package com.servinglynk.hmis.warehouse.core.model; 

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyCategories")
public class SurveyCategories extends PaginatedModel{


       @JsonProperty("surveyCategories") 
       Set<SurveyCategory>  surveyCategories = new HashSet<SurveyCategory>();
       public Set<SurveyCategory> getSurveyCategories() {
           return surveyCategories;
       }

        public void setSurveyCategories(Set<SurveyCategory> surveyProjects) {
           this.surveyCategories = surveyProjects;
       }
 
       public void addSurveyCategory(SurveyCategory surveyCategory) {
           this.surveyCategories.add(surveyCategory);
       }
 }
