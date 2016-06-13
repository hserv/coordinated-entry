package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("sectionQuestionMappings")
public class SectionQuestionMappings extends PaginatedModel{


       @JsonProperty("sectionQuestionMappings") 
       List<SectionQuestionMapping>sectionQuestionMappings = new ArrayList<SectionQuestionMapping>();
       public List<SectionQuestionMapping> getSectionQuestionMappings() {
           return sectionQuestionMappings;
       }

        public void setSectionQuestionMappings(List<SectionQuestionMapping> sectionQuestionMappings) {
           this.sectionQuestionMappings = sectionQuestionMappings;
       }
 
       public void addSectionQuestionMapping(SectionQuestionMapping sectionQuestionMapping) {
           this.sectionQuestionMappings.add(sectionQuestionMapping);
       }
 }
