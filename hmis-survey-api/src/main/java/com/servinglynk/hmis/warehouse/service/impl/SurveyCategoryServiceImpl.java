package com.servinglynk.hmis.warehouse.service.impl; 

import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.service.SurveyCategoryService;


@Component
public class SurveyCategoryServiceImpl extends ServiceBase implements SurveyCategoryService  {

   @Transactional
   public void createSurveyCategory(UUID surveyid, Set<SurveyCategory> surveyCategories,UUID caller) {
       if(CollectionUtils.isNotEmpty(surveyCategories)) {
    	   for(SurveyCategory surveyCategory : surveyCategories) {
    		   SurveyCategoryEntity surveyCategoryEntity = new SurveyCategoryEntity();
        	   SurveyEntity surveyEntity = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyid);
        	   surveyCategoryEntity.setSurveyEntity(surveyEntity);
        	   surveyCategoryEntity.setSurveyCategory(surveyCategory.getCategory());
        	   surveyCategoryEntity.setUser(caller);
        	   daoFactory.getSurveyCategoryEntityDao().createSurveyCategoryEntity(surveyCategoryEntity);
    	   }
    	}
   }
}
