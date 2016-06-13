package com.servinglynk.hmis.warehouse.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.service.PickListGroupService;
import com.servinglynk.hmis.warehouse.service.PickListValueService;
import com.servinglynk.hmis.warehouse.service.QuestionGroupService;
import com.servinglynk.hmis.warehouse.service.QuestionService;
import com.servinglynk.hmis.warehouse.service.ResponseService;
import com.servinglynk.hmis.warehouse.service.SectionQuestionMappingService;
import com.servinglynk.hmis.warehouse.service.SectionScoreService;
import com.servinglynk.hmis.warehouse.service.SurveySectionService;
import com.servinglynk.hmis.warehouse.service.SurveyService;

@Component
public class ParentServiceFactoryImpl  implements ParentServiceFactory {


	@Autowired SurveyService surveyService;

	public SurveyService getSurveyService() {
		return surveyService;
	}

	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	

	@Autowired PickListGroupService pickListGroupService;

	public PickListGroupService getPickListGroupService() {
		return pickListGroupService;
	}

	public void setPickListGroupService(PickListGroupService pickListGroupService) {
		this.pickListGroupService = pickListGroupService;
	}
	

	@Autowired PickListValueService pickListValueService;

	public PickListValueService getPickListValueService() {
		return pickListValueService;
	}

	public void setPickListValueService(PickListValueService pickListValueService) {
		this.pickListValueService = pickListValueService;
	}


	@Autowired QuestionService questionService;

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void QuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Autowired QuestionGroupService questionGroupService;

	public QuestionGroupService getQuestionGroupService() {
		return questionGroupService;
	}

	public void QuestionGroupService(QuestionGroupService questionGroupService) {
		this.questionGroupService = questionGroupService;
	}
	
	@Autowired ResponseService responseService;

	public ResponseService getResponseService() {
		return responseService;
	}

	public void ResponseService(ResponseService responseService) {
		this.responseService = responseService;
	}
	
	@Autowired SectionQuestionMappingService sectionQuestionMappingService;

	public SectionQuestionMappingService getSectionQuestionMappingService() {
		return sectionQuestionMappingService;
	}

	public void SectionQuestionMappingService(SectionQuestionMappingService sectionQuestionMappingService) {
		this.sectionQuestionMappingService = sectionQuestionMappingService;
	}
	
	
	@Autowired SurveySectionService surveySectionService;

	public SurveySectionService getSurveySectionService() {
		return surveySectionService;
	}

	public void SurveySectionService(SurveySectionService surveySectionService) {
		this.surveySectionService = surveySectionService;
	}
	
	@Autowired SectionScoreService sectionScoreService;

	public SectionScoreService getSectionScoreService() {
		return sectionScoreService;
	}

	public void setSectionScoreService(SectionScoreService sectionScoreService) {
		this.sectionScoreService = sectionScoreService;
	}
	
	
}