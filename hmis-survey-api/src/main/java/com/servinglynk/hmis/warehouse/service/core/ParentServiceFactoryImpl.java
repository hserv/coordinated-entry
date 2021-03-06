package com.servinglynk.hmis.warehouse.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.service.ClientService;
import com.servinglynk.hmis.warehouse.service.ClientSurveySubmissionService;
import com.servinglynk.hmis.warehouse.service.HealthService;
import com.servinglynk.hmis.warehouse.service.PickListGroupService;
import com.servinglynk.hmis.warehouse.service.PickListValueService;
import com.servinglynk.hmis.warehouse.service.QuestionGroupService;
import com.servinglynk.hmis.warehouse.service.QuestionService;
import com.servinglynk.hmis.warehouse.service.QuestionServicev2;
import com.servinglynk.hmis.warehouse.service.ResponseService;
import com.servinglynk.hmis.warehouse.service.ResponseServiceV3;
import com.servinglynk.hmis.warehouse.service.SectionQuestionMappingService;
import com.servinglynk.hmis.warehouse.service.SectionScoreService;
import com.servinglynk.hmis.warehouse.service.SectionScoreServiceV3;
import com.servinglynk.hmis.warehouse.service.SharingRuleService;
import com.servinglynk.hmis.warehouse.service.SurveyCategoryService;
import com.servinglynk.hmis.warehouse.service.SurveyProjectService;
import com.servinglynk.hmis.warehouse.service.SurveySectionService;
import com.servinglynk.hmis.warehouse.service.SurveyService;
import com.servinglynk.hmis.warehouse.service.SurveyServicev2;
import com.servinglynk.hmis.warehouse.service.SurveyServicev3;

@Component
public class ParentServiceFactoryImpl  implements ParentServiceFactory {


	@Autowired SurveyService surveyService;
	@Autowired SurveyServicev2 surveyServicev2;
	@Autowired SurveyServicev3 surveyServicev3;
	@Autowired QuestionServicev2 questionServicev2;
	@Autowired ResponseServiceV3 responseServiceV3;
	@Autowired SectionScoreServiceV3 sectionScoreServiceV3;
	@Autowired ClientService clientService;
	@Autowired ClientSurveySubmissionService clientSurveySubmissionService;
	@Autowired SharingRuleService sharingRuleService;
	@Autowired SurveyProjectService surveyProjectService;
	@Autowired SurveyCategoryService surveyCategoryService;

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
	
	@Autowired HealthService healthService;

	public HealthService getHealthService() {
		return healthService;
	}

	public void setHealthService(HealthService healthService) {
		this.healthService = healthService;
	}

	public SurveyServicev2 getSurveyServicev2() {
		return surveyServicev2;
	}

	public void setSurveyServicev2(SurveyServicev2 surveyServicev2) {
		this.surveyServicev2 = surveyServicev2;
	}

	public QuestionServicev2 getQuestionServicev2() {
		return questionServicev2;
	}

	public void setQuestionServicev2(QuestionServicev2 questionServicev2) {
		this.questionServicev2 = questionServicev2;
	}

	public ResponseServiceV3 getResponseServiceV3() {
		return responseServiceV3;
	}

	public void setResponseServiceV3(ResponseServiceV3 responseServiceV3) {
		this.responseServiceV3 = responseServiceV3;
	}

	public SectionScoreServiceV3 getSectionScoreServiceV3() {
		return sectionScoreServiceV3;
	}

	public void setSectionScoreServiceV3(SectionScoreServiceV3 sectionScoreServiceV3) {
		this.sectionScoreServiceV3 = sectionScoreServiceV3;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientSurveySubmissionService getClientSurveySubmissionService() {
		return clientSurveySubmissionService;
	}

	public void setClientSurveySubmissionService(ClientSurveySubmissionService clientSurveySubmissionService) {
		this.clientSurveySubmissionService = clientSurveySubmissionService;
	}

	public SharingRuleService getSharingRuleService() {
		return sharingRuleService;
	}

	public void setSharingRuleService(SharingRuleService sharingRuleService) {
		this.sharingRuleService = sharingRuleService;
	}

	public SurveyProjectService getSurveyProjectService() {
		return surveyProjectService;
	}

	public void setSurveyProjectService(SurveyProjectService surveyProjectService) {
		this.surveyProjectService = surveyProjectService;
	}

	public SurveyCategoryService getSurveyCategoryService() {
		return surveyCategoryService;
	}

	public void setSurveyCategoryService(SurveyCategoryService surveyCategoryService) {
		this.surveyCategoryService = surveyCategoryService;
	}

	public SurveyServicev3 getSurveyServicev3() {
		return surveyServicev3;
	}

	public void setSurveyServicev3(SurveyServicev3 surveyServicev3) {
		this.surveyServicev3 = surveyServicev3;
	}
}