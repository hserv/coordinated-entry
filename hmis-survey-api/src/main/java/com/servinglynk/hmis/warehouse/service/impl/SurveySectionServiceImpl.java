package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.core.model.SurveySection;
import com.servinglynk.hmis.warehouse.core.model.SurveySections;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;
import com.servinglynk.hmis.warehouse.service.SurveySectionService;
import com.servinglynk.hmis.warehouse.service.converter.SurveySectionConverter;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveySectionNotFoundException;


@Component
public class SurveySectionServiceImpl extends ServiceBase implements SurveySectionService  {

   @Transactional
   public SurveySection createSurveySection(UUID surveyid,SurveySection surveySection,String caller){
	   SurveyEntity surveyEntity = daoFactory.getSurveyDao().getSurveyById(surveyid);
	   if(surveyEntity==null) throw new SurveyNotFoundException();
       SurveySectionEntity pSurveySection = SurveySectionConverter.modelToEntity(surveySection, null);
       pSurveySection.setSurveyEntity(surveyEntity);
       pSurveySection.setCreatedAt(LocalDateTime.now());
       pSurveySection.setUser(caller);
       daoFactory.getSurveySectionEntityDao().createSurveySectionEntity(pSurveySection);
       surveySection.setSurveySectionId(pSurveySection.getId());
       return surveySection;
   }


   @Transactional
   public SurveySection updateSurveySection(UUID surveyid,SurveySection surveySection,String caller){
       SurveySectionEntity pSurveySection = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(surveySection.getSurveySectionId());
       if(pSurveySection==null) throw new SurveySectionNotFoundException();

       SurveySectionConverter.modelToEntity(surveySection, pSurveySection);
       pSurveySection.setUpdatedAt(LocalDateTime.now());
       pSurveySection.setUser(caller);
       daoFactory.getSurveySectionEntityDao().updateSurveySectionEntity(pSurveySection);
       surveySection.setSurveySectionId(pSurveySection.getId());
       return surveySection;
   }


   @Transactional
   public SurveySection deleteSurveySection(UUID SurveySectionId,String caller){
       SurveySectionEntity pSurveySection = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(SurveySectionId);
       if(pSurveySection==null) throw new SurveySectionNotFoundException();

       pSurveySection.setUser(caller);
       daoFactory.getSurveySectionEntityDao().deleteSurveySectionEntity(pSurveySection);
       return new SurveySection();
   }


   @Transactional
   public SurveySection getSurveySectionById(UUID SurveySectionId){
       SurveySectionEntity pSurveySection = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(SurveySectionId);
       if(pSurveySection==null) throw new SurveySectionNotFoundException();

       return SurveySectionConverter.entityToModel( pSurveySection );
   }


   @Transactional
   public SurveySections getAllSurveySurveySections(UUID surveyId,Integer startIndex, Integer maxItems){
       SurveySections SurveySections = new SurveySections();
        List<SurveySectionEntity> entities = daoFactory.getSurveySectionEntityDao().getAllSurveySurveySectionEntities(surveyId,startIndex,maxItems);
        for(SurveySectionEntity entity : entities){
           SurveySections.addSurveySection(SurveySectionConverter.entityToModel(entity));
        }
        long count = daoFactory.getSurveySectionEntityDao().getSurveySectionEntitiesCount(surveyId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(SurveySections.getSurveySections().size());
        pagination.setTotal((int)count);
        SurveySections.setPagination(pagination);
        return SurveySections; 
   }

}
