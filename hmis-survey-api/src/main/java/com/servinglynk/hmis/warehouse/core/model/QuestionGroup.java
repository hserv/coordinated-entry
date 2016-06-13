package com.servinglynk.hmis.warehouse.core.model; 

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("questionGroup")
public class QuestionGroup extends ClientModel{


      private UUID questionIGroupId;

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
