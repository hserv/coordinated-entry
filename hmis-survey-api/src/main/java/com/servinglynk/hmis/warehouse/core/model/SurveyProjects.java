package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveyProjects")
public class SurveyProjects extends PaginatedModel{


       @JsonProperty("surveyProjects") 
       List<SurveyProject>surveyProjects = new ArrayList<SurveyProject>();
       public List<SurveyProject> getSurveyProjects() {
           return surveyProjects;
       }

        public void setSurveyProjects(List<SurveyProject> surveyProjects) {
           this.surveyProjects = surveyProjects;
       }
 
       public void addSurveyProject(SurveyProject surveySection) {
           this.surveyProjects.add(surveySection);
       }
 }
