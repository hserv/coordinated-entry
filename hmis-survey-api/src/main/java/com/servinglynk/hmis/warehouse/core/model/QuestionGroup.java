package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("questionGroup")
public class QuestionGroup extends ClientModel{


      private UUID questionIGroupId;

      @NotBlank(message="Question Group Name is required")
      @NotEmpty(message="Question Group Name is required")
      private String questionGroupName;



      public UUID getQuestionIGroupId(){
          return questionIGroupId;
      }
      public void setQuestionIGroupId(UUID questionIGroupId){
          this.questionIGroupId = questionIGroupId;
      }
      public String getQuestionGroupName(){
          return questionGroupName;
      }
      public void setQuestionGroupName(String questionGroupName){
          this.questionGroupName = questionGroupName;
      }

 }
