package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.QuestionGroup;
import com.servinglynk.hmis.warehouse.core.model.QuestionGroups;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.QuestionGroupEntity;
import com.servinglynk.hmis.warehouse.service.QuestionGroupService;
import com.servinglynk.hmis.warehouse.service.converter.QuestionGroupConverter;
import com.servinglynk.hmis.warehouse.service.exception.QuestionGroupNotFoundException;


@Component
public class QuestionGroupServiceImpl extends ServiceBase implements QuestionGroupService  {

   @Transactional
   public QuestionGroup createQuestionGroup(QuestionGroup QuestionGroup,String caller){
       QuestionGroupEntity pQuestionGroup = QuestionGroupConverter.modelToEntity(QuestionGroup, null);
       pQuestionGroup.setCreatedAt(LocalDateTime.now());
       pQuestionGroup.setUser(caller);
       daoFactory.getQuestionGroupEntityDao().createQuestionGroupEntity(pQuestionGroup);
       QuestionGroup.setQuestionIGroupId(pQuestionGroup.getId());
       return QuestionGroup;
   }


   @Transactional
   public QuestionGroup updateQuestionGroup(QuestionGroup QuestionGroup,UUID enrollmentId,String caller){
       QuestionGroupEntity pQuestionGroup = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntityById(QuestionGroup.getQuestionIGroupId());
       if(pQuestionGroup==null) throw new QuestionGroupNotFoundException();

       QuestionGroupConverter.modelToEntity(QuestionGroup, pQuestionGroup);
       pQuestionGroup.setUpdatedAt(LocalDateTime.now());
       pQuestionGroup.setUser(caller);
       daoFactory.getQuestionGroupEntityDao().updateQuestionGroupEntity(pQuestionGroup);
       QuestionGroup.setQuestionIGroupId(pQuestionGroup.getId());
       return QuestionGroup;
   }


   @Transactional
   public QuestionGroup deleteQuestionGroup(UUID QuestionGroupId,String caller){
       QuestionGroupEntity pQuestionGroup = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntityById(QuestionGroupId);
       if(pQuestionGroup==null) throw new QuestionGroupNotFoundException();

       pQuestionGroup.setUser(caller);
       daoFactory.getQuestionGroupEntityDao().deleteQuestionGroupEntity(pQuestionGroup);
       return new QuestionGroup();
   }


   @Transactional
   public QuestionGroup getQuestionGroupById(UUID QuestionGroupId){
       QuestionGroupEntity pQuestionGroup = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntityById(QuestionGroupId);
       if(pQuestionGroup==null) throw new QuestionGroupNotFoundException();

       return QuestionGroupConverter.entityToModel( pQuestionGroup );
   }


   @Transactional
   public QuestionGroups getAllQuestionGroups(Integer startIndex, Integer maxItems){
       QuestionGroups QuestionGroups = new QuestionGroups();
        List<QuestionGroupEntity> entities = daoFactory.getQuestionGroupEntityDao().getAllQuestionGroupEntitys(startIndex,maxItems);
        for(QuestionGroupEntity entity : entities){
           QuestionGroups.addQuestionGroup(QuestionGroupConverter.entityToModel(entity));
        }
        long count = daoFactory.getQuestionGroupEntityDao().getQuestionGroupEntitysCount();
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(QuestionGroups.getQuestionGroups().size());
        pagination.setTotal((int)count);
        QuestionGroups.setPagination(pagination);
        return QuestionGroups; 
   }


public QuestionGroup updateQuestionGroup(QuestionGroup QuestionGroup, String caller) {
	// TODO Auto-generated method stub
	return null;
}


}
