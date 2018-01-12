package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.core.model.Questions;
public interface QuestionService {

   Question createQuestion(Question Question,String caller);
   Question updateQuestion(Question Question,String caller);
   Question deleteQuestion(UUID QuestionId,String caller);
   Question getQuestionById(UUID QuestionId);
   Questions getAllQuestions(UUID questionGroupId, Integer startIndex, Integer maxItems);
}
