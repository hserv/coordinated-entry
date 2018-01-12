package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.core.model.Questions;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;
import com.servinglynk.hmis.warehouse.service.QuestionService;
import com.servinglynk.hmis.warehouse.service.converter.QuestionConverter;
import com.servinglynk.hmis.warehouse.service.exception.PickListGroupNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.QuestionGroupNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.QuestionNotFoundException;


@Component
public class QuestionServiceImpl extends ServiceBase implements QuestionService  {

   @Transactional
   public Question createQuestion(Question question,String caller){
	   QuestionGroupEntity questionGroupEntity = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntityById(question.getQuestionGroupId());
	   if(questionGroupEntity==null) throw new QuestionGroupNotFoundException();
	   
       QuestionEntity pQuestion = QuestionConverter.modelToEntity(question, null);
       pQuestion.setQuestionGroupEntity(questionGroupEntity);
     
       if(question.getPickListGroupId()!=null) {
    	   PickListGroupEntity pickListGroupEntity = daoFactory.getPickListGroupEntityDao().getPickListGroupEntityById(question.getPickListGroupId());
    	   	if(pickListGroupEntity==null) throw new PickListGroupNotFoundException();
    	   pQuestion.setPickListGroupEntity(pickListGroupEntity);
       }
       pQuestion.setCreatedAt(LocalDateTime.now());
       pQuestion.setUser(caller);
       daoFactory.getQuestionEntityDao().createQuestionEntity(pQuestion);
       question.setQuestionId(pQuestion.getId());
       return question;
   }


   @Transactional
   public Question updateQuestion(Question question,String caller){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(question.getQuestionId());
       if(pQuestion==null) throw new QuestionNotFoundException();

       QuestionConverter.modelToEntity(question, pQuestion);

       if(question.getPickListGroupId()!=null) {
    	   PickListGroupEntity pickListGroupEntity = daoFactory.getPickListGroupEntityDao().getPickListGroupEntityById(question.getPickListGroupId());
    	   	if(pickListGroupEntity==null) throw new PickListGroupNotFoundException();
    	   pQuestion.setPickListGroupEntity(pickListGroupEntity);
       }

       pQuestion.setUpdatedAt(LocalDateTime.now());
       pQuestion.setUser(caller);
       daoFactory.getQuestionEntityDao().updateQuestionEntity(pQuestion);
       question.setQuestionId(pQuestion.getId());
       return question;
   }


   @Transactional
   public Question deleteQuestion(UUID QuestionId,String caller){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(QuestionId);
       if(pQuestion==null) throw new QuestionNotFoundException();

       pQuestion.setUser(caller);
       daoFactory.getQuestionEntityDao().deleteQuestionEntity(pQuestion);
       return new Question();
   }


   @Transactional
   public Question getQuestionById(UUID QuestionId){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(QuestionId);
       if(pQuestion==null) throw new QuestionNotFoundException();

       return QuestionConverter.entityToModel( pQuestion );
   }


   @Transactional
   public Questions getAllQuestions(UUID questionGroupId,Integer startIndex, Integer maxItems){
       Questions Questions = new Questions();
        List<QuestionEntity> entities = daoFactory.getQuestionEntityDao().getAllQuestionEntitys(questionGroupId,startIndex,maxItems);
        for(QuestionEntity entity : entities){
           Questions.addQuestion(QuestionConverter.entityToModel(entity));
        }
        long count = daoFactory.getQuestionEntityDao().getQuestionEntitysCount(questionGroupId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(Questions.getQuestions().size());
        pagination.setTotal((int)count);
        Questions.setPagination(pagination);
        return Questions; 
   }
}