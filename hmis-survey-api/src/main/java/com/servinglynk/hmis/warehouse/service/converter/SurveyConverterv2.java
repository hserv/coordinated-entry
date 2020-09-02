package com.servinglynk.hmis.warehouse.service.converter; 

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.servinglynk.hmis.warehouse.core.model.Surveyv2;
import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
public class SurveyConverterv2{

   public static SurveyEntity modelToEntity (Surveyv2 model ,SurveyEntity entity) {
       if(entity==null) entity = new SurveyEntity();
       entity.setId(model.getSurveyId());
       entity.setSurveyTitle(model.getSurveyTitle());
       entity.setSurveyOwner(model.getSurveyOwner());
       entity.setTagValue(model.getTagValue());
       entity.setLocked(model.getLocked());
       entity.setCopySurveyId(model.getCopySurveyId());
       entity.setLocked(model.getLocked());
       entity.setHmisVersion(model.getHmisVersion());
       return entity;    
   }


   public static Surveyv2 entityToModel (SurveyEntity entity) {
       Surveyv2 model = new Surveyv2();
       model.setSurveyId(entity.getId());
       model.setSurveyTitle(entity.getSurveyTitle());
       model.setSurveyOwner(entity.getSurveyOwner());
       model.setTagValue(entity.getTagValue());
       model.setProjectGroupCode(entity.getProjectGroupCode());
       model.setLocked(entity.isLocked());
       model.setCopySurveyId(entity.isCopySurveyId());
       Set<String> surveyCategories = new HashSet<String>();
       List<SurveyCategoryEntity> surveyCategoryEntities = entity.getSurveyCategoryEntities();
       if(CollectionUtils.isNotEmpty(surveyCategoryEntities)) {
    	   for(SurveyCategoryEntity surveyCategoryEntity: surveyCategoryEntities)
    		   if(surveyCategoryEntity != null) {
    			   surveyCategories.add(surveyCategoryEntity.getSurveyCategory());
    		   }
       }
       if(entity.getHmisVersion() != null)
    	   model.setHmisVersion(entity.getHmisVersion());
       return model;
   }
}