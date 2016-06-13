package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;

import java.util.List;

public interface SurveySectionEntityDao {

   SurveySectionEntity createSurveySectionEntity(SurveySectionEntity SurveySectionEntity);
   SurveySectionEntity updateSurveySectionEntity(SurveySectionEntity SurveySectionEntity);
   void deleteSurveySectionEntity(SurveySectionEntity SurveySectionEntity);
   SurveySectionEntity getSurveySectionEntityById(UUID SurveySectionEntityId);
   List<SurveySectionEntity> getAllSurveySurveySectionEntities(UUID surveyId, Integer startIndex, Integer maxItems);
   long getSurveySectionEntitiesCount(UUID surveyId);
}
