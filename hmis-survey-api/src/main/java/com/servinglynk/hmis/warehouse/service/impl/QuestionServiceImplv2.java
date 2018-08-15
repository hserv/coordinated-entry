package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.JSONObjectMapper;
import com.servinglynk.hmis.warehouse.core.model.PickListValues2;
import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.core.model.Questionsv2;
import com.servinglynk.hmis.warehouse.core.model.Questionv2;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.PickListValueEntity;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;
import com.servinglynk.hmis.warehouse.service.QuestionServicev2;
import com.servinglynk.hmis.warehouse.service.converter.PickListValueConverter;
import com.servinglynk.hmis.warehouse.service.converter.QuestionConverterv2;
import com.servinglynk.hmis.warehouse.service.exception.QuestionGroupNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.QuestionNotFoundException;
import com.thoughtworks.xstream.mapper.Mapper;


@Component
public class QuestionServiceImplv2 extends ServiceBase implements QuestionServicev2  {
	
	JSONObjectMapper mapper = new JSONObjectMapper();

   @Transactional
   public Questionv2 createQuestion(Questionv2 question,String caller){
	   QuestionGroupEntity questionGroupEntity = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntityById(question.getQuestionGroupId());
	   if(questionGroupEntity==null) throw new QuestionGroupNotFoundException();
	   
       QuestionEntity pQuestion = QuestionConverterv2.modelToEntity(question, null);
       pQuestion.setQuestionGroupEntity(questionGroupEntity);
     
      
       pQuestion.setCreatedAt(LocalDateTime.now());
       pQuestion.setUser(getUser());
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
       pQuestion.setUser(getUser());
       daoFactory.getQuestionEntityDao().updateQuestionEntity(pQuestion);
       question.setQuestionId(pQuestion.getId());
       return question;
   }


   @Transactional
   public Questionv2 deleteQuestion(UUID QuestionId,String caller){
       QuestionEntity pQuestion = daoFactory.getQuestionEntityDao().getQuestionEntityById(QuestionId);
       if(pQuestion==null) throw new QuestionNotFoundException();

       pQuestion.setUser(getUser());
       daoFactory.getQuestionEntityDao().deleteQuestionEntity(pQuestion);
       return new Questionv2();
   }


   @Transactional
   public Questionv2 getQuestionById(UUID QuestionId){
       QuestionEntity entity = daoFactory.getQuestionEntityDao().getQuestionEntityById(QuestionId);
       if(entity==null) throw new QuestionNotFoundException();
	   Questionv2 questionv2 = QuestionConverterv2.entityToModel(entity);
	   if(entity.getPickListGroupEntity()!=null) {
		   PickListValues2 values2 = new PickListValues2();
		   List<PickListValueEntity> pickListValueEntities =	entity.getPickListGroupEntity().getPickListValueEntities();
		   for(PickListValueEntity valueEntity : pickListValueEntities) {
			   
			   values2.addPickListValue(PickListValueConverter.entityToModel(valueEntity));
		   }
		   
		   if(!values2.getPickListValues().isEmpty()) questionv2.setPickList(values2);
	   }
       return questionv2;
   }
   
   
   @Transactional
   public Questionsv2 filterQuestions(String displayText,String description,Integer startIndex, Integer maxItems)  throws Exception {
	  
	   Questionsv2 questions = new Questionsv2();
	   List<QuestionEntity> entities = daoFactory.getQuestionEntityDao().getAllQuestionEntitys(displayText,description,startIndex,maxItems);
       for(QuestionEntity entity : entities){
    	   Questionv2 questionv2 = QuestionConverterv2.entityToModel(entity);
    	   if(entity.getPickListGroupEntity()!=null) {
    		   PickListValues2 values2 = new PickListValues2();
    		   List<PickListValueEntity> pickListValueEntities =	entity.getPickListGroupEntity().getPickListValueEntities();
    		   for(PickListValueEntity valueEntity : pickListValueEntities) {
    			   
    			   values2.addPickListValue(PickListValueConverter.entityToModel(valueEntity));
    		   }
    		   
    		   if(!values2.getPickListValues().isEmpty()) questionv2.setPickList(values2);
    	   }
       	questions.addQuestion(questionv2);
       }
       long count = daoFactory.getQuestionEntityDao().getAllQuestionEntitiesCount(displayText,description);
       SortedPagination pagination = new SortedPagination();

       pagination.setFrom(startIndex);
       pagination.setReturned(questions.getQuestions().size());
       pagination.setTotal((int)count);
       questions.setPagination(pagination);
       return questions; 
   }


   @Transactional
   public Questionsv2 getAllQuestions(UUID questionGroupId,Integer startIndex, Integer maxItems) throws Exception{
	   Questionsv2 questions = new Questionsv2();
        List<QuestionEntity> entities = daoFactory.getQuestionEntityDao().getAllQuestionEntitys(questionGroupId,startIndex,maxItems);
        for(QuestionEntity entity : entities){
        	  Questionv2 questionv2 = QuestionConverterv2.entityToModel(entity);
     	   if(entity.getPickListGroupEntity()!=null) {
    		   PickListValues2 values2 = new PickListValues2();
    		   List<PickListValueEntity> pickListValueEntities =	entity.getPickListGroupEntity().getPickListValueEntities();
    		   for(PickListValueEntity valueEntity : pickListValueEntities) {
    			   
    			   values2.addPickListValue(PickListValueConverter.entityToModel(valueEntity));
    		   }
    		   
    		  if(!values2.getPickListValues().isEmpty()) questionv2.setPickList(values2);
    	   }
       	questions.addQuestion(questionv2);
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