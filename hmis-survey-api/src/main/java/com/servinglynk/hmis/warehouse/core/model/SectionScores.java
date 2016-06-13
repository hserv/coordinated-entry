package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("sectionScores")
public class SectionScores extends PaginatedModel{


       @JsonProperty("sectionScores") 
       List<SectionScore>sectionScores = new ArrayList<SectionScore>();
       public List<SectionScore> getSectionScores() {
           return sectionScores;
       }

        public void setSectionScores(List<SectionScore> sectionScores) {
           this.sectionScores = sectionScores;
       }
 
       public void addSectionScore(SectionScore sectionScore) {
           this.sectionScores.add(sectionScore);
       }
 }
