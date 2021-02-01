package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.CreateSurveyProject;
import com.servinglynk.hmis.warehouse.core.model.GlobalProject;
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
   public SurveyProjects createSurveyProject(UUID surveyid,CreateSurveyProject surveyProject,String caller){
	   SurveyEntity surveyEntity = daoFactory.getSurveyDao().getSurveyById(surveyid);
	   if(surveyEntity==null) throw new SurveyNotFoundException();
	   Set<GlobalProject> globalProjects = surveyProject.getGlobalProjects();
	   SurveyProjects surveyProjects = new SurveyProjects();
	   if(CollectionUtils.isNotEmpty(globalProjects)) {
		   for(GlobalProject globalProject : globalProjects) {
			   if(globalProject != null) {
				   SurveyProjectEntity pSurveyProject = SurveyProjectConverter.modelToEntity(surveyProject, null);
			       pSurveyProject.setSurveyEntity(surveyEntity);
			       pSurveyProject.setCreatedAt(LocalDateTime.now());
			       pSurveyProject.setUser(getUser());
			       pSurveyProject.setProjectName(globalProject.getProjectName());
			       pSurveyProject.setGlobalProjectId(globalProject.getGlobalProjectId());
			       daoFactory.getSurveyProjectEntityDao().createSurveyProjectEntity(pSurveyProject);
			       surveyProject.setSurveyProjectId(pSurveyProject.getId());
			       surveyProjects.addSurveyProject(SurveyProjectConverter.entityToModel(pSurveyProject));
			   }
		   }
	   }
       return surveyProjects;
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
       SurveyProjects surveyProjects = new SurveyProjects();
        List<SurveyProjectEntity> entities = daoFactory.getSurveyProjectEntityDao().getAllSurveySurveyProjectEntities(surveyId,startIndex,maxItems);
        for(SurveyProjectEntity entity : entities){
           surveyProjects.addSurveyProject(SurveyProjectConverter.entityToModel(entity));
        }
        long count = daoFactory.getSurveyProjectEntityDao().getSurveyProjectEntitiesCount(surveyId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(surveyProjects.getSurveyProjects().size());
        pagination.setTotal((int)count);
        surveyProjects.setPagination(pagination);
        return surveyProjects; 
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
