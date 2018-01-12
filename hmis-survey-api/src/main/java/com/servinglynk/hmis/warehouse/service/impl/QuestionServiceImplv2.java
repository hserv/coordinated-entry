package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.Questionsv2;
import com.servinglynk.hmis.warehouse.core.model.Questionv2;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;
import com.servinglynk.hmis.warehouse.service.QuestionServicev2;
import com.servinglynk.hmis.warehouse.service.converter.QuestionConverterv2;
import com.servinglynk.hmis.warehouse.service.exception.QuestionGroupNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.QuestionNotFoundException;


@Component
public class QuestionServiceImplv2 extends ServiceBase implements QuestionServicev2  {

   @Transactional
   public Questionv2 createQuestion(Questionv2 question,String caller){
	   QuestionGroupEntity questionGroupEntity = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntityById(question.getQuestionGroupId());
	   if(questionGroupEntity==null) throw new QuestionGroupNotFoundException();
	   
       QuestionEntity pQuestion = QuestionConverterv2.modelToEntity(question, null);
       pQuestion.setQuestionGroupEntity(questionGroupEntity);
     
      
       pQuestion.setCreatedAt(LocalDateTime.now());
       pQuestion.setUser(caller);
       daoFactory.getQuestionEntityDao().createQuestionEntity(pQuestion);
       question.setQuestionId(pQuestion.getId());
       return question;
   }


   @Transactional
   public Questionv2 updateQuestion(Questionv2 question,String caller){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(question.getQuestionId());
       if(pQuestion==null) throw new QuestionNotFoundException();

       QuestionConverterv2.modelToEntity(question, pQuestion);

       pQuestion.setUpdatedAt(LocalDateTime.now());
       pQuestion.setUser(caller);
       daoFactory.getQuestionEntityDao().updateQuestionEntity(pQuestion);
       question.setQuestionId(pQuestion.getId());
       return question;
   }


   @Transactional
   public Questionv2 deleteQuestion(UUID QuestionId,String caller){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(QuestionId);
       if(pQuestion==null) throw new QuestionNotFoundException();

       pQuestion.setUser(caller);
       daoFactory.getQuestionEntityDao().deleteQuestionEntity(pQuestion);
       return new Questionv2();
   }


   @Transactional
   public Questionv2 getQuestionById(UUID QuestionId){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(QuestionId);
       if(pQuestion==null) throw new QuestionNotFoundException();

       return QuestionConverterv2.entityToModel( pQuestion );
   }


   @Transactional
   public Questionsv2 getAllQuestions(UUID questionGroupId,Integer startIndex, Integer maxItems){
	   Questionsv2 questions = new Questionsv2();
        List<QuestionEntity> entities = daoFactory.getQuestionEntityDao().getAllQuestionEntitys(questionGroupId,startIndex,maxItems);
        for(QuestionEntity entity : entities){
        	questions.addQuestion(QuestionConverterv2.entityToModel(entity));
        }
        long count = daoFactory.getQuestionEntityDao().getQuestionEntitysCount(questionGroupId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(questions.getQuestions().size());
        pagination.setTotal((int)count);
        questions.setPagination(pagination);
        return questions; 
   }
}