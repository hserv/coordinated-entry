package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.QuestionGroup;
import com.servinglynk.hmis.warehouse.core.model.QuestionGroups;
public interface QuestionGroupService {

   QuestionGroup createQuestionGroup(QuestionGroup QuestionGroup,String caller);
   QuestionGroup updateQuestionGroup(QuestionGroup QuestionGroup,String caller);
   QuestionGroup deleteQuestionGroup(UUID QuestionGroupId,String caller);
   QuestionGroup getQuestionGroupById(UUID QuestionGroupId);
   QuestionGroups getAllQuestionGroups(Integer startIndex, Integer maxItems);
}
