package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

@Component
public class ClientSurveySubmissionEntityDaoImpl extends QueryExecutorImpl implements ClientSurveySubmissionEntityDao {

   public ClientSurveySubmissionEntity updateClientSubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity){
       update(clientSurveySubmissionEntity);
       return clientSurveySubmissionEntity;
   }
   
   public ClientSurveySubmissionEntity createClientSubmissionEntity(ClientSurveySubmissionEntity  clientSurveySubmissionEntity){
       insert(clientSurveySubmissionEntity);
       return clientSurveySubmissionEntity;
   }
   
   @SuppressWarnings("unchecked")
   public List<ClientSurveySubmissionEntity> getClientSurveySubmissionEntityByClienId(UUID clientId){
	   DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
	   criteria.add(Restrictions.eq("clientId", clientId));
	   List<ClientSurveySubmissionEntity> entities = (List<ClientSurveySubmissionEntity>) findByCriteria(criteria);
	   return entities;
   }
   @SuppressWarnings("unchecked")
   public ClientSurveySubmissionEntity getClientSurveySubmissionEntityById(UUID clientSurveySubmissionEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
	   criteria.add(Restrictions.eq("id", clientSurveySubmissionEntityId));
	   List<ClientSurveySubmissionEntity> entities = (List<ClientSurveySubmissionEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   @SuppressWarnings("unchecked")
   public ClientSurveySubmissionEntity getClientSubmissionEntitybyClientSurveySubmission(UUID clientId,UUID surveyId,UUID submissionId) {
	   DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
	   criteria.add(Restrictions.eq("clientId", clientId));
	   criteria.add(Restrictions.eq("surveyId", surveyId));
	   criteria.add(Restrictions.eq("submissionId", submissionId));
	   List<ClientSurveySubmissionEntity> entities = (List<ClientSurveySubmissionEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }
   
   
   
   }
   

