package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.core.model.Surveys;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.service.SurveyService;
import com.servinglynk.hmis.warehouse.service.converter.SurveyConverter;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;


@Component
public class SurveyServiceImpl extends ServiceBase implements SurveyService  {

   @Transactional
   public Survey createSurvey(Survey survey,Session session){
       SurveyEntity pSurvey = SurveyConverter.modelToEntity(survey, null);
       pSurvey.setCreatedAt(LocalDateTime.now());
       pSurvey.setProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
       pSurvey.setUser(session.getAccount().getUsername());
       daoFactory.getSurveyEntityDao().createSurveyEntity(pSurvey);
       survey.setSurveyId(pSurvey.getId());
       return survey;
   }


   @Transactional
   public Survey updateSurvey(Survey survey,String caller){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(survey.getSurveyId());
       if(pSurvey==null) throw new SurveyNotFoundException();

       SurveyConverter.modelToEntity(survey, pSurvey);
       pSurvey.setUpdatedAt(LocalDateTime.now());
       pSurvey.setUser(caller);
       daoFactory.getSurveyEntityDao().updateSurveyEntity(pSurvey);
       survey.setSurveyId(pSurvey.getId());
       return survey;
   }


   @Transactional
   public Survey deleteSurvey(UUID surveyId,String caller){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
       if(pSurvey==null) throw new SurveyNotFoundException();

       pSurvey.setUser(caller);
       daoFactory.getSurveyEntityDao().deleteSurveyEntity(pSurvey);
       return new Survey();
   }


   @Transactional
   public Survey getSurveyById(UUID surveyId){
       SurveyEntity pSurvey = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
       if(pSurvey==null) throw new SurveyNotFoundException();

       return SurveyConverter.entityToModel( pSurvey );
   }


   @Transactional
   public Surveys getAllSurveys(Integer startIndex, Integer maxItems,String projectGroupCode){
       Surveys surveys = new Surveys();
        List<SurveyEntity> entities = daoFactory.getSurveyEntityDao().getAllSurveyEntitys(startIndex,maxItems,projectGroupCode);
        for(SurveyEntity entity : entities){
           surveys.addSurvey(SurveyConverter.entityToModel(entity));
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
