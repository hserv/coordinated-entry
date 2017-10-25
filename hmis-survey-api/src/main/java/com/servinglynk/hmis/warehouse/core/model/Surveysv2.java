package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("survies")
public class Surveysv2 extends PaginatedModel{


       @JsonProperty("survies") 
       List<Surveyv2>survies = new ArrayList<Surveyv2>();
       public List<Surveyv2> getSurvies() {
           return survies;
       }

        public void setSurvies(List<Surveyv2> survies) {
           this.survies = survies;
       }
 
       public void addSurvey(Surveyv2 survey) {
           this.survies.add(survey);
       }
 }
