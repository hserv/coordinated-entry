package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

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
}