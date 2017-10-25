package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.core.model.Surveysv2;
import com.servinglynk.hmis.warehouse.core.model.Surveyv2;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.service.SurveyServicev2;
import com.servinglynk.hmis.warehouse.service.converter.SurveyConverterv2;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;


@Component
public class SurveyServiceImplv2 extends ServiceBase implements SurveyServicev2  {

   @Transactional
   public Surveyv2 createSurvey(Surveyv2 survey,Session session){
       SurveyEntity pSurvey = SurveyConverterv2.modelToEntity(survey, null);
       pSurvey.setCreatedAt(LocalDateTime.now());
       pSurvey.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
       pSurvey.setUser(session.getAccount().getUsername());
       daoFactory.getSurveyEntityDao().createSurveyEntity(pSurvey);
       survey.setSurveyId(pSurvey.getId());
       return survey;
   }


   @Transactional
   public Surveyv2 updateSurvey(Surveyv2 survey,String caller){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(survey.getSurveyId());
       if(pSurvey==null) throw new SurveyNotFoundException();

       SurveyConverterv2.modelToEntity(survey, pSurvey);
       pSurvey.setUpdatedAt(LocalDateTime.now());
       pSurvey.setUser(caller);
       daoFactory.getSurveyEntityDao().updateSurveyEntity(pSurvey);
       survey.setSurveyId(pSurvey.getId());
       return survey;
   }


   @Transactional
   public Surveyv2 deleteSurvey(UUID surveyId,String caller){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
       if(pSurvey==null) throw new SurveyNotFoundException();

       pSurvey.setUser(caller);
       daoFactory.getSurveyEntityDao().deleteSurveyEntity(pSurvey);
       return new Surveyv2();
   }


   @Transactional
   public Surveyv2 getSurveyById(UUID surveyId){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
       if(pSurvey==null) throw new SurveyNotFoundException();

       return SurveyConverterv2.entityToModel( pSurvey );
   }


   @Transactional
   public Surveysv2 getAllSurveys(Integer startIndex, Integer maxItems,String projectGroupCode){
       Surveysv2 surveys = new Surveysv2();
        List<SurveyEntity> entities = daoFactory.getSurveyEntityDao().getAllSurveyEntitys(startIndex,maxItems,projectGroupCode);
        for(SurveyEntity entity : entities){
           surveys.addSurvey(SurveyConverterv2.entityToModel(entity));
        }
        long count = daoFactory.getSurveyEntityDao().getSurveyEntitysCount(projectGroupCode);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(surveys.getSurvies().size());
        pagination.setTotal((int)count);
        surveys.setPagination(pagination);
        return surveys; 
   }
}
