package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("survies")
public class Surveys extends PaginatedModel{


       @JsonProperty("survies") 
       List<Survey>survies = new ArrayList<Survey>();
       public List<Survey> getSurvies() {
           return survies;
       }

        public void setSurvies(List<Survey> survies) {
           this.survies = survies;
       }
 
       public void addSurvey(Survey survey) {
           this.survies.add(survey);
       }
 }
