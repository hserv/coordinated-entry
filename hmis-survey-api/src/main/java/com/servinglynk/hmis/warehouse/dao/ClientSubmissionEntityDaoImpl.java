package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.ClientSubmissionEntity;



@Component
public class ClientSubmissionEntityDaoImpl extends QueryExecutorImpl implements ClientSubmissionEntityDao {

   public ClientSubmissionEntity createClientSubmissionEntity(ClientSubmissionEntity  clientSubmissionEntity){
       insert(clientSubmissionEntity);
            return clientSubmissionEntity;
   }
   public ClientSubmissionEntity updateClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity){
       update(clientSubmissionEntity);
       return clientSubmissionEntity;
   }
   public void deleteClientSubmissionEntity(ClientSubmissionEntity clientSubmissionEntity){
       delete(clientSubmissionEntity);
   }

   public ClientSubmissionEntity getClientSubmissionEntityById(UUID clientSubmissionEntityId){ 
	   DetachedCriteria criteria = DetachedCriteria.forClass(ClientSubmissionEntity.class);
	   criteria.add(Restrictions.eq("id", clientSubmissionEntityId));
	   List<ClientSubmissionEntity> entities = (List<ClientSubmissionEntity>) findByCriteria(criteria);
	   if(entities.isEmpty()) return null;
	   return entities.get(0);
   }

}
