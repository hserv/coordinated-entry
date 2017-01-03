package com.servinglynk.hmis.warehouse.dao; 

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.SurveyEntity;

@Component
public class SurveyDaoImpl extends QueryExecutorImpl implements SurveyDao {

   public SurveyEntity createSurvey(SurveyEntity survey){
       survey.setId(UUID.randomUUID()); 
       insert(survey);
       return survey;
   }
   public SurveyEntity updateSurvey(SurveyEntity survey){
       update(survey);
       return survey;
   }
   public void deleteSurvey(SurveyEntity survey){
       delete(survey);
   }
   public SurveyEntity getSurveyById(UUID surveyId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(SurveyEntity.class);
	   criteria.add(Restrictions.eq("id", surveyId));
	   List<SurveyEntity> entities = (List<SurveyEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public List<SurveyEntity> getAllSurveys(Integer startIndex, Integer maxItems){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyEntity.class);
       return (List<SurveyEntity>) findByCriteria(criteria,startIndex,maxItems);
   }
   public long getSurveysCount(){
       DetachedCriteria criteria=DetachedCriteria.forClass(SurveyEntity.class);
       return countRows(criteria);
   }
}
