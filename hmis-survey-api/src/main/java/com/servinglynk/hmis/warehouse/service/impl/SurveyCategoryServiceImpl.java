package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.core.model.SurveyCategories;
import com.servinglynk.hmis.warehouse.core.model.SurveyCategory;
import com.servinglynk.hmis.warehouse.model.SurveyCategoryEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.service.SurveyCategoryService;
import com.servinglynk.hmis.warehouse.service.converter.SurveyCategoryConverter;
import com.servinglynk.hmis.warehouse.service.exception.SurveyCategoryNotFoundException;


@Component
public class SurveyCategoryServiceImpl extends ServiceBase implements SurveyCategoryService  {

   @Transactional
   public void createSurveyCategory(UUID surveyid, Set<SurveyCategory> surveyCategories) {
    	   for(SurveyCategory surveyCategory : surveyCategories) {
    		   SurveyCategoryEntity surveyCategoryEntity = new SurveyCategoryEntity();
        	   SurveyEntity surveyEntity = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyid);
        	   surveyCategoryEntity.setSurveyEntity(surveyEntity);
        	   surveyCategoryEntity.setSurveyCategory(surveyCategory.getCategory());
        	   surveyCategoryEntity.setUser(getUser());
        	   daoFactory.getSurveyCategoryEntityDao().createSurveyCategoryEntity(surveyCategoryEntity);
    	   }
    	}
   
   @Transactional
   public SurveyCategory createSurveyCategory(UUID surveyid, SurveyCategory surveyCategory, String caller) {
    		   SurveyCategoryEntity surveyCategoryEntity = new SurveyCategoryEntity();
        	   SurveyEntity surveyEntity = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyid);
        	   surveyCategoryEntity.setSurveyEntity(surveyEntity);
        	   surveyCategoryEntity.setSurveyCategory(surveyCategory.getCategory());
        	   surveyCategoryEntity.setUser(getUser());
        	   daoFactory.getSurveyCategoryEntityDao().createSurveyCategoryEntity(surveyCategoryEntity);
        	   return SurveyCategoryConverter.entityToModel( surveyCategoryEntity );
    	}
	
	   @Transactional
	   public SurveyCategory updateSurveyCategory(UUID surveyid,SurveyCategory surveyCategory,String caller){
	       SurveyCategoryEntity pSurveyCategory = daoFactory.getSurveyCategoryEntityDao().getSurveyCategoryEntityById(surveyCategory.getSurveyCategoryId());
	       if(pSurveyCategory==null) throw new SurveyCategoryNotFoundException();
	       pSurveyCategory.setSurveyCategory(surveyCategory.getCategory());
	       pSurveyCategory.setUpdatedAt(LocalDateTime.now());
	       pSurveyCategory.setUser(getUser());
	       daoFactory.getSurveyCategoryEntityDao().updateSurveyCategoryEntity(pSurveyCategory);
	       return surveyCategory;
	   }


	   @Transactional
	   public SurveyCategory deleteSurveyCategory(UUID SurveyCategoryId,String caller){
	       SurveyCategoryEntity pSurveyCategory = daoFactory.getSurveyCategoryEntityDao().getSurveyCategoryEntityById(SurveyCategoryId);
	       if(pSurveyCategory==null) throw new SurveyCategoryNotFoundException();

	       pSurveyCategory.setUser(getUser());
	       daoFactory.getSurveyCategoryEntityDao().deleteSurveyCategoryEntity(pSurveyCategory);
	       return new SurveyCategory();
	   }


	   @Transactional
	   public SurveyCategory getSurveyCategoryById(UUID SurveyCategoryId){
	       SurveyCategoryEntity pSurveyCategory = daoFactory.getSurveyCategoryEntityDao().getSurveyCategoryEntityById(SurveyCategoryId);
	       if(pSurveyCategory==null) throw new SurveyCategoryNotFoundException();
	       return SurveyCategoryConverter.entityToModel( pSurveyCategory );
	   }
	   
	   @Transactional
	   public SurveyCategories getAllSurveySurveyCategories(UUID surveyId,Integer startIndex, Integer maxItems){
		   SurveyCategories surveyCategories = new SurveyCategories();
	        List<SurveyCategoryEntity> entities = daoFactory.getSurveyCategoryEntityDao().getAllSurveySurveyCategoryEntities(surveyId,startIndex,maxItems);
	        for(SurveyCategoryEntity entity : entities){
	        	surveyCategories.addSurveyCategory(SurveyCategoryConverter.entityToModel(entity));
	        }
	        long count = daoFactory.getSurveyCategoryEntityDao().getSurveyCategoryEntitiesCount(surveyId);
	        SortedPagination pagination = new SortedPagination();
	 
	        pagination.setFrom(startIndex);
	        pagination.setReturned(surveyCategories.getSurveyCategories().size());
	        pagination.setTotal((int)count);
	        surveyCategories.setPagination(pagination);
	        return surveyCategories; 
	   }

}
