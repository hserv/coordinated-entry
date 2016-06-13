package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMapping;
import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMappings;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.SectionQuestionMappingEntity;
import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;
import com.servinglynk.hmis.warehouse.service.SectionQuestionMappingService;
import com.servinglynk.hmis.warehouse.service.converter.SectionQuestionMappingConverter;
import com.servinglynk.hmis.warehouse.service.exception.QuestionNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SectionQuestionMappingNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveySectionNotFoundException;


@Component
public class SectionQuestionMappingServiceImpl extends ServiceBase implements SectionQuestionMappingService  {

   @Transactional
   public void createSectionQuestionMapping(UUID surveysectionid,SectionQuestionMappings questions,String caller){
	   
	   for(SectionQuestionMapping mapping : questions.getSectionQuestionMappings()){
       
		   QuestionEntity questionEntity = daoFactory.getQuestionEntityDao().getQuestionEntityById(mapping.getQuestion().getQuestionId());
		   if(questionEntity==null) throw new QuestionNotFoundException();
		   
		   SurveySectionEntity sectionEntity = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(surveysectionid);
		   if(sectionEntity==null) throw new SurveySectionNotFoundException();
		   
		   SectionQuestionMappingEntity pSectionQuestionMapping = new SectionQuestionMappingEntity();
		   pSectionQuestionMapping.setQuestionEntity(questionEntity);
		   pSectionQuestionMapping.setQuestionGroupEntity(questionEntity.getQuestionGroupEntity());
		   pSectionQuestionMapping.setSurveySectionEntity(sectionEntity);
		   pSectionQuestionMapping.setRequired(mapping.isRequired());
		   pSectionQuestionMapping.setCreatedAt(LocalDateTime.now());
		   pSectionQuestionMapping.setUser(caller);
		   daoFactory.getSectionQuestionMappingEntityDao().createSectionQuestionMappingEntity(pSectionQuestionMapping);
	   }
   }

   @Transactional
   public SectionQuestionMapping deleteSectionQuestionMapping(UUID questionid,String caller){
       SectionQuestionMappingEntity pSectionQuestionMapping = daoFactory.getSectionQuestionMappingEntityDao().getSectionQuestionMappingEntityByQuestionId(questionid);
       if(pSectionQuestionMapping==null) throw new SectionQuestionMappingNotFoundException();

       pSectionQuestionMapping.setUser(caller);
       daoFactory.getSectionQuestionMappingEntityDao().deleteSectionQuestionMappingEntity(pSectionQuestionMapping);
       return new SectionQuestionMapping();
   }

   @Transactional
   public SectionQuestionMappings getAllSectionQuestionMappings(UUID surveysectionid,Integer startIndex, Integer maxItems){
       SectionQuestionMappings SectionQuestionMappings = new SectionQuestionMappings();
        List<SectionQuestionMappingEntity> entities = daoFactory.getSectionQuestionMappingEntityDao().getAllSectionQuestionMappingEntities(surveysectionid,startIndex,maxItems);
        for(SectionQuestionMappingEntity entity : entities){
           SectionQuestionMappings.addSectionQuestionMapping(SectionQuestionMappingConverter.entityToModel(entity));
        }
        long count = daoFactory.getSectionQuestionMappingEntityDao().getSectionQuestionMappingEntitiesCount(surveysectionid);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(SectionQuestionMappings.getSectionQuestionMappings().size());
        pagination.setTotal((int)count);
        SectionQuestionMappings.setPagination(pagination);
        return SectionQuestionMappings; 
   }
}