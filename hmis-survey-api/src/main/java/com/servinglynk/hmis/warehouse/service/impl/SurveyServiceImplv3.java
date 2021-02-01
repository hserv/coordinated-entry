package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.core.model.Surveysv3;
import com.servinglynk.hmis.warehouse.core.model.Surveyv3;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.service.SurveyServicev3;
import com.servinglynk.hmis.warehouse.service.converter.SurveyConverterv3;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;


@Component
public class SurveyServiceImplv3 extends ServiceBase implements SurveyServicev3  {

   @Transactional
   public Surveyv3 createSurvey(Surveyv3 survey,Session session){
       SurveyEntity pSurvey = SurveyConverterv3.modelToEntity(survey, null);
       pSurvey.setCreatedAt(LocalDateTime.now());
       pSurvey.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
       pSurvey.setUser(getUser());
       daoFactory.getSurveyEntityDao().createSurveyEntity(pSurvey);
       survey.setSurveyId(pSurvey.getId());
       return survey;
   }


   @Transactional
   public Surveyv3 updateSurvey(Surveyv3 survey,String caller){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(survey.getSurveyId());
       if(pSurvey==null) throw new SurveyNotFoundException();

       SurveyConverterv3.modelToEntity(survey, pSurvey);
       pSurvey.setUpdatedAt(LocalDateTime.now());
       pSurvey.setUser(getUser());
       daoFactory.getSurveyEntityDao().updateSurveyEntity(pSurvey);
       survey.setSurveyId(pSurvey.getId());
       return survey;
   }


   @Transactional
   public Surveyv3 deleteSurvey(UUID surveyId,String caller){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
       if(pSurvey==null) throw new SurveyNotFoundException();

       pSurvey.setUser(getUser());
       daoFactory.getSurveyEntityDao().deleteSurveyEntity(pSurvey);
       return new Surveyv3();
   }


   @Transactional
   public Surveyv3 getSurveyById(UUID surveyId){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
       if(pSurvey==null) throw new SurveyNotFoundException();

       return SurveyConverterv3.entityToModel( pSurvey );
   }


   @Transactional
   public Surveysv3 getAllSurveys(Integer startIndex, Integer maxItems,String projectGroupCode){
       Surveysv3 surveys = new Surveysv3();
        List<SurveyEntity> entities = daoFactory.getSurveyEntityDao().getAllSurveyEntitys(startIndex,maxItems,projectGroupCode);
        for(SurveyEntity entity : entities){
           surveys.addSurvey(SurveyConverterv3.entityToModel(entity));
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
