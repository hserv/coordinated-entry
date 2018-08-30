package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Questionsv2;
import com.servinglynk.hmis.warehouse.core.model.Questionv2;
public interface QuestionServicev2 {

   Questionv2 createQuestion(Questionv2 Question,String caller);
   Questionv2 updateQuestion(Questionv2 Question,String caller) throws Exception ;
   Questionv2 deleteQuestion(UUID QuestionId,String caller);
   Questionv2 getQuestionById(UUID QuestionId) throws Exception ;
   Questionsv2 getAllQuestions(UUID questionGroupId, Integer startIndex, Integer maxItems) throws Exception ;
   Questionsv2 filterQuestions(String displayText,String description,Integer startIndex, Integer maxItems) throws Exception;
}
