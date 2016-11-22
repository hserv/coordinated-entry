package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.core.model.ClientSurveyScore;
import com.servinglynk.hmis.warehouse.model.SectionScoreEntity;

@Component
public class SectionScoreDaoImpl extends QueryExecutorImpl implements SectionScoreDao {

	public SectionScoreEntity createSectionScore(SectionScoreEntity sectionScoreEntity) {
		sectionScoreEntity.setId(UUID.randomUUID());
		insert(sectionScoreEntity);
		return sectionScoreEntity;
	}
	

	public void deleteSectionScore(SectionScoreEntity sectionScoreEntity) {
		delete(sectionScoreEntity);
	}

	@SuppressWarnings("unchecked")
	public List<SectionScoreEntity> getAllSectionScores(UUID surveyId, UUID sectionId, Integer startIndex,
			Integer maxItems) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("sectionEntity", "sectionEntity");
		criteria.createAlias("surveyEntity", "surveyEntity");
		if (surveyId != null)
			criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
		if (sectionId != null)
			criteria.add(Restrictions.eq("sectionEntity.id", sectionId));
		if(startIndex==0 && maxItems ==0) return (List<SectionScoreEntity>) findByCriteria(criteria);
		return (List<SectionScoreEntity>) findByCriteria(criteria, startIndex, maxItems);
	}
	
	public List<SectionScoreEntity> getClientScores(UUID clientId,UUID surveyId, UUID sectionId,Integer startIndex,Integer maxItems){
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("sectionEntity", "sectionEntity");
		criteria.createAlias("surveyEntity", "surveyEntity");
		criteria.add(Restrictions.eq("clientId", clientId));
		if (surveyId != null)
			criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
		if (sectionId != null)
			criteria.add(Restrictions.eq("sectionEntity.id", sectionId));
		if(startIndex==0 && maxItems ==0) return (List<SectionScoreEntity>) findByCriteria(criteria);
		return (List<SectionScoreEntity>) findByCriteria(criteria, startIndex, maxItems);
	}
	
	public long getClientScoresCount(UUID clientId,UUID surveyId, UUID sectionId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("sectionEntity", "sectionEntity");
		criteria.createAlias("surveyEntity", "surveyEntity");
		criteria.add(Restrictions.eq("clientId", clientId));
		if (surveyId != null)
			criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
		if (sectionId != null)
			criteria.add(Restrictions.eq("sectionEntity.id", sectionId));
		return countRows(criteria);
	}


	public long getAllSectionScoresCount(UUID surveyId, UUID sectionId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("sectionEntity", "sectionEntity");
		criteria.createAlias("surveyEntity", "surveyEntity");
		if (surveyId != null)
			criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
		if (sectionId != null)
			criteria.add(Restrictions.eq("sectionEntity.id", sectionId));
		return countRows(criteria);
	}

	public List<ClientSurveyScore> calculateClientSurveyScore(Integer startIndex,Integer maxItems,String projectGroupCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("surveyEntity", "surveyEntity");
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.sum("sectionScore"),"surveyScore");
		projectionList.add(Projections.property("surveyEntity.id"),"surveyId");
		projectionList.add(Projections.property("clientId"),"clientId");
		projectionList.add(Projections.property("surveyEntity.projectGroupCode"),"projectGroupCode");
		projectionList.add(Projections.property("surveyEntity.createdAt"),"surveyDate");
		projectionList.add(Projections.groupProperty("surveyEntity.id"));
		projectionList.add(Projections.groupProperty("clientId"));
		projectionList.add(Projections.groupProperty("surveyEntity.tagValue"),"surveyTagValue");
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("surveyEntity.projectGroupCode",projectGroupCode));
		criteria.setResultTransformer(Transformers.aliasToBean(ClientSurveyScore.class));
		return (List<ClientSurveyScore>) findByCriteria(criteria,startIndex,maxItems);
	}

	public Long countOfClientSurveyScore(String projectGroupCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("surveyEntity", "surveyEntity");
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.sum("sectionScore"),"surveyScore");
		projectionList.add(Projections.property("surveyEntity.id"),"surveyId");
		projectionList.add(Projections.property("clientId"),"clientId");

		projectionList.add(Projections.groupProperty("surveyEntity.id"));
		projectionList.add(Projections.groupProperty("clientId"));
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("surveyEntity.projectGroupCode",projectGroupCode));
		return countRows(criteria);
	}

	
	
	public SectionScoreEntity getSectionScoreById(UUID sectionScoreId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
			criteria.add(Restrictions.eq("id", sectionScoreId));
			List<SectionScoreEntity> scoreEntities = (List<SectionScoreEntity>) findByCriteria(criteria);
			if(!scoreEntities.isEmpty()) return scoreEntities.get(0);
		return null;
	}

	public void updateSectionScore(SectionScoreEntity sectionScoreEntity) {
				update(sectionScoreEntity);
	}
}