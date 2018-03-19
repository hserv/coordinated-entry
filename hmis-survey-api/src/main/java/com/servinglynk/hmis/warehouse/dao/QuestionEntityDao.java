package com.servinglynk.hmis.warehouse.dao;

import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.QuestionEntity;

import java.util.List;

public interface QuestionEntityDao {

	QuestionEntity createQuestionEntity(QuestionEntity QuestionEntity);

	QuestionEntity updateQuestionEntity(QuestionEntity QuestionEntity);

	void deleteQuestionEntity(QuestionEntity QuestionEntity);

	QuestionEntity getQuestionEntityById(UUID QuestionEntityId);

	List<QuestionEntity> getAllQuestionEntitys(UUID questionGroupId, Integer startIndex, Integer maxItems);

	long getQuestionEntitysCount(UUID questionGroupId);

	List<QuestionEntity> getAllQuestionEntitys(String displayText, String description, Integer startIndex,
			Integer maxItems);

	Long getAllQuestionEntitiesCount(String displayText, String description);
}
