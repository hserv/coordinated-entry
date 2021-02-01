package com.servinglynk.hmis.warehouse.dao; 

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;

public interface SurveyCategoryEntityDao {

   SurveyCategoryEntity createSurveyCategoryEntity(SurveyCategoryEntity SurveyCategoryEntity);
   SurveyCategoryEntity updateSurveyCategoryEntity(SurveyCategoryEntity SurveyCategoryEntity);
   void deleteSurveyCategoryEntity(SurveyCategoryEntity SurveyCategoryEntity);
   SurveyCategoryEntity getSurveyCategoryEntityById(UUID SurveyCategoryEntityId);
   List<SurveyCategoryEntity> getAllSurveySurveyCategoryEntities(UUID surveyId, Integer startIndex, Integer maxItems);
   long getSurveyCategoryEntitiesCount(UUID surveyId);
}
