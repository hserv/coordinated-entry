package com.servinglynk.hmis.warehouse.service.converter; 

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.servinglynk.hmis.warehouse.core.model.Surveyv3;
import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
public class SurveyConverterv3{

   public static SurveyEntity modelToEntity (Surveyv3 model ,SurveyEntity entity) {
       if(entity==null) entity = new SurveyEntity();
       entity.setId(model.getSurveyId());
       entity.setSurveyTitle(model.getSurveyTitle());
       entity.setSurveyOwner(model.getSurveyOwner());
       entity.setTagValue(model.getTagValue());
       entity.setLocked(model.getLocked());
       entity.setSurveyDefinition(model.getSurveyDefinition());
       entity.setCopySurveyId(model.getCopySurveyId());
       entity.setLocked(model.getLocked());
       entity.setHmisVersion(model.getHmisVersion());
       return entity;    
   }


   public static Surveyv3 entityToModel (SurveyEntity entity) {
       Surveyv3 model = new Surveyv3();
       model.setSurveyId(entity.getId());
       model.setSurveyTitle(entity.getSurveyTitle());
       model.setSurveyOwner(entity.getSurveyOwner());
       model.setTagValue(entity.getTagValue());
       model.setProjectGroupCode(entity.getProjectGroupCode());
       model.setLocked(entity.isLocked());
       model.setSurveyDefinition(entity.getSurveyDefinition());
       model.setCopySurveyId(entity.isCopySurveyId());
      
       if(entity.getHmisVersion() != null)
    	   model.setHmisVersion(entity.getHmisVersion());
       return model;
   }
}