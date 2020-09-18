package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("surveys")
public class Surveysv3 extends PaginatedModel{


       @JsonProperty("surveys") 
       List<Surveyv3> survies = new ArrayList<Surveyv3>();
       public List<Surveyv3> getSurvies() {
           return survies;
       }

        public void setSurvies(List<Surveyv3> survies) {
           this.survies = survies;
       }
 
       public void addSurvey(Surveyv3 survey) {
           this.survies.add(survey);
       }
 }
