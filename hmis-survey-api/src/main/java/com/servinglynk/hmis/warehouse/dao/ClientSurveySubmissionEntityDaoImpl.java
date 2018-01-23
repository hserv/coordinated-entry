package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;



@Component
public class ClientSurveySubmissionEntityDaoImpl extends QueryExecutorImpl implements ClientSurveySubmissionEntityDao {

   public ClientSurveySubmissionEntity createClientSubmissionEntity(ClientSurveySubmissionEntity  clientSurveySubmissionEntity){
       insert(clientSurveySubmissionEntity);
       return clientSurveySubmissionEntity;
   }
   public ClientSurveySubmissionEntity updateClientSubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity){
       update(clientSurveySubmissionEntity);
       return clientSurveySubmissionEntity;
   }
   public void deleteClientSubmissionEntity(ClientSurveySubmissionEntity clientSurveySubmissionEntity){
       delete(clientSurveySubmissionEntity);
   }

   public ClientSurveySubmissionEntity getClientSubmissionEntityById(UUID clientSurveySubmissionEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
	   criteria.add(Restrictions.eq("id", clientSurveySubmissionEntityId));
	   List<ClientSurveySubmissionEntity> entities = (List<ClientSurveySubmissionEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }

}
