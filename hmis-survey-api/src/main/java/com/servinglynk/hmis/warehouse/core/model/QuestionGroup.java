package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("questionGroup")
public class QuestionGroup extends ClientModel{


      private UUID questionGroupId;

      @NotBlank(message="Question Group Name is required")
      @NotEmpty(message="Question Group Name is required")
      private String questionGroupName;



      public UUID getQuestionGroupId(){
          return questionGroupId;
      }
      public void setQuestionGroupId(UUID questionGroupId){
          this.questionGroupId = questionGroupId;
      }
      public String getQuestionGroupName(){
          return questionGroupName;
      }
      public void setQuestionGroupName(String questionGroupName){
          this.questionGroupName = questionGroupName;
      }

 }
