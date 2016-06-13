package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("questionGroups")
public class QuestionGroups extends PaginatedModel{


       @JsonProperty("questionGroups") 
       List<QuestionGroup>questionGroups = new ArrayList<QuestionGroup>();
       public List<QuestionGroup> getQuestionGroups() {
           return questionGroups;
       }

        public void setQuestionGroups(List<QuestionGroup> questionGroups) {
           this.questionGroups = questionGroups;
       }
 
       public void addQuestionGroup(QuestionGroup questionGroup) {
           this.questionGroups.add(questionGroup);
       }
 }
