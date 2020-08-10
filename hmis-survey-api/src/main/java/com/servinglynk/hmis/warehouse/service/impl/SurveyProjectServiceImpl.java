package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.core.model.SurveyProject;
import com.servinglynk.hmis.warehouse.core.model.SurveyProjects;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.model.SurveyProjectEntity;
import com.servinglynk.hmis.warehouse.service.SurveyProjectService;
import com.servinglynk.hmis.warehouse.service.converter.SurveyProjectConverter;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveyProjectNotFoundException;


@Component
public class SurveyProjectServiceImpl extends ServiceBase implements SurveyProjectService  {

   @Transactional
   public SurveyProject createSurveyProject(UUID surveyid,SurveyProject surveySection,String caller){
	   SurveyEntity surveyEntity = daoFactory.getSurveyDao().getSurveyById(surveyid);
	   if(surveyEntity==null) throw new SurveyNotFoundException();
       SurveyProjectEntity pSurveyProject = SurveyProjectConverter.modelToEntity(surveySection, null);
       pSurveyProject.setSurveyEntity(surveyEntity);
       pSurveyProject.setCreatedAt(LocalDateTime.now());
       pSurveyProject.setUser(getUser());
       daoFactory.getSurveyProjectEntityDao().createSurveyProjectEntity(pSurveyProject);
       surveySection.setSurveyProjectId(pSurveyProject.getId());
       return surveySection;
   }


   @Transactional
   public SurveyProject updateSurveyProject(UUID surveyid,SurveyProject surveySection,String caller){
       SurveyProjectEntity pSurveyProject = daoFactory.getSurveyProjectEntityDao().getSurveyProjectEntityById(surveySection.getSurveyProjectId());
       if(pSurveyProject==null) throw new SurveyProjectNotFoundException();

       SurveyProjectConverter.modelToEntity(surveySection, pSurveyProject);
       pSurveyProject.setUpdatedAt(LocalDateTime.now());
       pSurveyProject.setUser(getUser());
       daoFactory.getSurveyProjectEntityDao().updateSurveyProjectEntity(pSurveyProject);
       surveySection.setSurveyProjectId(pSurveyProject.getId());
       return surveySection;
   }


   @Transactional
   public SurveyProject deleteSurveyProject(UUID SurveyProjectId,String caller){
       SurveyProjectEntity pSurveyProject = daoFactory.getSurveyProjectEntityDao().getSurveyProjectEntityById(SurveyProjectId);
       if(pSurveyProject==null) throw new SurveyProjectNotFoundException();

       pSurveyProject.setUser(getUser());
       daoFactory.getSurveyProjectEntityDao().deleteSurveyProjectEntity(pSurveyProject);
       return new SurveyProject();
   }


   @Transactional
   public SurveyProject getSurveyProjectById(UUID SurveyProjectId){
       SurveyProjectEntity pSurveyProject = daoFactory.getSurveyProjectEntityDao().getSurveyProjectEntityById(SurveyProjectId);
       if(pSurveyProject==null) throw new SurveyProjectNotFoundException();

       return SurveyProjectConverter.entityToModel( pSurveyProject );
   }


   @Transactional
   public SurveyProjects getAllSurveySurveyProjects(UUID surveyId,Integer startIndex, Integer maxItems){
       SurveyProjects SurveyProjects = new SurveyProjects();
        List<SurveyProjectEntity> entities = daoFactory.getSurveyProjectEntityDao().getAllSurveySurveyProjectEntities(surveyId,startIndex,maxItems);
        for(SurveyProjectEntity entity : entities){
           SurveyProjects.addSurveyProject(SurveyProjectConverter.entityToModel(entity));
        }
        long count = daoFactory.getSurveyProjectEntityDao().getSurveyProjectEntitiesCount(surveyId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(SurveyProjects.getSurveyProjects().size());
        pagination.setTotal((int)count);
        SurveyProjects.setPagination(pagination);
        return SurveyProjects; 
   }
   

   @Transactional
   public SurveyProjects getAllSurveyByGlobaProjectId(UUID globalProjectId,Integer startIndex, Integer maxItems){
       SurveyProjects SurveyProjects = new SurveyProjects();
        List<SurveyProjectEntity> entities = daoFactory.getSurveyProjectEntityDao().getAllSurveyByGlobaProjectId(globalProjectId,startIndex,maxItems);
        for(SurveyProjectEntity entity : entities){
           SurveyProjects.addSurveyProject(SurveyProjectConverter.entityToModel(entity));
        }
        long count = daoFactory.getSurveyProjectEntityDao().getAllSurveyByGlobaProjectIdCount(globalProjectId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(SurveyProjects.getSurveyProjects().size());
        pagination.setTotal((int)count);
        SurveyProjects.setPagination(pagination);
        return SurveyProjects; 
   }
}
