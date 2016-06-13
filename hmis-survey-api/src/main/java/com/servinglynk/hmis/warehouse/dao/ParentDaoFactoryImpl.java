package com.servinglynk.hmis.warehouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParentDaoFactoryImpl  implements ParentDaoFactory {

	@Autowired SurveyDao surveyDao;

	public SurveyDao getSurveyDao() {
		return surveyDao;
	}

	public void setSurveyDao(SurveyDao surveyDao) {
		this.surveyDao = surveyDao;
	}

	@Autowired PickListGroupEntityDao pickListGroupEntityDao;

	public PickListGroupEntityDao getPickListGroupEntityDao() {
		return pickListGroupEntityDao;
	}

	public void setPickListGroupEntityDao(PickListGroupEntityDao pickListGroupEntityDao) {
		this.pickListGroupEntityDao = pickListGroupEntityDao;
	}
	
	@Autowired PickListValueEntityDao pickListValueEntityDao;

	public PickListValueEntityDao getPickListValueEntityDao() {
		return pickListValueEntityDao;
	}

	public void setPickListValueEntityDao(PickListValueEntityDao pickListValueEntityDao) {
		this.pickListValueEntityDao = pickListValueEntityDao;
	}
	
	@Autowired QuestionEntityDao questionEntityDao;

	public QuestionEntityDao getQuestionEntityDao() {
		return questionEntityDao;
	}

	public void setQuestionEntityDao(QuestionEntityDao questionEntityDao) {
		this.questionEntityDao = questionEntityDao;
	}
	
	@Autowired QuestionGroupEntityDao questionGroupEntityDao;

	public QuestionGroupEntityDao getQuestionGroupEntityDao() {
		return questionGroupEntityDao;
	}

	public void setQuestionGroupEntityDao(QuestionGroupEntityDao questionGroupEntityDao) {
		this.questionGroupEntityDao = questionGroupEntityDao;
	}
	
	@Autowired ResponseEntityDao responseEntityDao;

	public ResponseEntityDao getResponseEntityDao() {
		return responseEntityDao;
	}

	public void setResponseEntityDao(ResponseEntityDao responseEntityDao) {
		this.responseEntityDao = responseEntityDao;
	}
	
	@Autowired SectionQuestionMappingEntityDao sectionQuestionMappingEntityDao;

	public SectionQuestionMappingEntityDao getSectionQuestionMappingEntityDao() {
		return sectionQuestionMappingEntityDao;
	}

	public void setSectionQuestionMappingEntityDao(SectionQuestionMappingEntityDao sectionQuestionMappingEntityDao) {
		this.sectionQuestionMappingEntityDao = sectionQuestionMappingEntityDao;
	}
	
	

	@Autowired SurveyEntityDao surveyEntityDao;

	public SurveyEntityDao getSurveyEntityDao() {
		return surveyEntityDao;
	}

	public void setSurveyEntityDao(SurveyEntityDao surveyEntityDao) {
		this.surveyEntityDao = surveyEntityDao;
	}
	
	@Autowired SurveySectionEntityDao surveySectionEntityDao;

	public SurveySectionEntityDao getSurveySectionEntityDao() {
		return surveySectionEntityDao;
	}

	public void setSurveySectionEntityDao(SurveySectionEntityDao surveySectionEntityDao) {
		this.surveySectionEntityDao = surveySectionEntityDao;
	}
	
	@Autowired SectionScoreDao sectionScoreDao;

	public SectionScoreDao getSectionScoreDao() {
		return sectionScoreDao;
	}

	public void setSectionScoreDao(SectionScoreDao sectionScoreDao) {
		this.sectionScoreDao = sectionScoreDao;
	}
}