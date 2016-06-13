package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SectionQuestionMappingEntity;

import java.util.List;

public interface SectionQuestionMappingEntityDao {

   SectionQuestionMappingEntity createSectionQuestionMappingEntity(SectionQuestionMappingEntity SectionQuestionMappingEntity);
   SectionQuestionMappingEntity updateSectionQuestionMappingEntity(SectionQuestionMappingEntity SectionQuestionMappingEntity);
   void deleteSectionQuestionMappingEntity(SectionQuestionMappingEntity SectionQuestionMappingEntity);
   SectionQuestionMappingEntity getSectionQuestionMappingEntityById(UUID SectionQuestionMappingEntityId);
   List<SectionQuestionMappingEntity> getAllSectionQuestionMappingEntities(UUID surveysectionid, Integer startIndex, Integer maxItems);
   long getSectionQuestionMappingEntitiesCount(UUID surveysectionid);
   
   public SectionQuestionMappingEntity getSectionQuestionMappingEntityByQuestionId(UUID questionId);
}
