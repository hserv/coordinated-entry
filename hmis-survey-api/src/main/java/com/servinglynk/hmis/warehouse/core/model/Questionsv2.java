package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("questions")
public class Questionsv2 extends PaginatedModel{


       @JsonProperty("questions") 
       List<Questionv2> questions = new ArrayList<Questionv2>();
       public List<Questionv2> getQuestions() {
           return questions;
       }

        public void setQuestions(List<Questionv2> questions) {
           this.questions = questions;
       }
 
       public void addQuestion(Questionv2 question) {
           this.questions.add(question);
       }
 }
