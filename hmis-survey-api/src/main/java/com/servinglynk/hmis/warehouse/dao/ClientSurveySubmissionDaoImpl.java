package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
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
		criteria.createAlias("clientId", "clientId");
		criteria.add(Restrictions.eq("clientId.id", clientId));
		return (List<ClientSurveySubmissionEntity>) findByCriteria(criteria,startIndex,maxItems);
	}


	public long clientSurveySubmissionsCount(UUID clientId){
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
		criteria.createAlias("clientId", "clientId");
		criteria.add(Restrictions.eq("clientId.id", clientId));
		return countRows(criteria);
	}

	public List<ClientSurveySubmissionEntity> getSearchClientSurveySubmissions(String name, UUID globalClientId,
			Integer startIndex, Integer maxItems) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
		criteria.createAlias("clientId", "clientId");
		if(globalClientId!=null)	criteria.add(Restrictions.eq("clientId.id", globalClientId));
		if(name!=null) {
			  Criterion firstName = Restrictions.ilike("clientId.firstName",name,MatchMode.ANYWHERE);
			  Criterion lastName = Restrictions.ilike("clientId.lastName",name,MatchMode.ANYWHERE);
			  Criterion middleName = Restrictions.ilike("clientId.middleName",name,MatchMode.ANYWHERE);
			  Criterion clientName = Restrictions.sqlRestriction("(concat(first_name,last_name) ilike '%"+name.replaceAll(" ","")+"%') ");
			  criteria.add(Restrictions.or(firstName,lastName,middleName,clientName));

		}
		return (List<ClientSurveySubmissionEntity>) findByCriteria(criteria,startIndex,maxItems);

	}

	public long clientSurveySubmissionsCount(String name, UUID globalClientId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ClientSurveySubmissionEntity.class);
		criteria.createAlias("clientId", "clientId");
		if(globalClientId!=null)	criteria.add(Restrictions.eq("clientId.id", globalClientId));
		if(name!=null) {
			  Criterion firstName = Restrictions.ilike("clientId.firstName",name,MatchMode.ANYWHERE);
			  Criterion lastName = Restrictions.ilike("clientId.lastName",name,MatchMode.ANYWHERE);
			  Criterion middleName = Restrictions.ilike("clientId.middleName",name,MatchMode.ANYWHERE);
			  Criterion clientName = Restrictions.sqlRestriction("(concat(first_name,last_name) ilike '%"+name.replaceAll(" ","")+"%') ");
			  criteria.add(Restrictions.or(firstName,lastName,middleName,clientName));

		}
		return countRows(criteria);
	}
}
