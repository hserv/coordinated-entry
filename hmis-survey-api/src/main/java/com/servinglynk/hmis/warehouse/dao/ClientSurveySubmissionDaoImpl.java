package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

@Component
public class ClientSurveySubmissionDaoImpl extends QueryExecutorImpl implements ClientSurveySubmissionDao {
	
	public void create(ClientSurveySubmissionEntity entity) {
		entity.setId(UUID.randomUUID());
		insert(entity);
	}
	
	public void updateClientSurveySubmission(ClientSurveySubmissionEntity entity) {
		update(entity);
	}
	
	public ClientSurveySubmissionEntity getById(UUID id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
		criteria.add(Restrictions.eq("id", id));
		List<ClientSurveySubmissionEntity> entities = (List<ClientSurveySubmissionEntity>) findByCriteria(criteria);
		if(!entities.isEmpty()) return entities.get(0);
		return null;
	}
	
	
	public List<ClientSurveySubmissionEntity> getAllClientSurveySubmissions(UUID clientId,Integer startIndex, Integer maxItems){
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
		criteria.add(Restrictions.eq("clientId", clientId));
		return (List<ClientSurveySubmissionEntity>) findByCriteria(criteria,startIndex,maxItems);
	}


	public long clientSurveySubmissionsCount(UUID clientId){
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
		criteria.add(Restrictions.eq("clientId", clientId));
		return countRows(criteria);
	}
}
