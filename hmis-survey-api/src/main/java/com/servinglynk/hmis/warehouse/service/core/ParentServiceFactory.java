package com.servinglynk.hmis.warehouse.service.core;

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
import com.servinglynk.hmis.warehouse.service.SurveySectionService;
import com.servinglynk.hmis.warehouse.service.SurveyService;
import com.servinglynk.hmis.warehouse.service.SurveyServicev2;

public interface ParentServiceFactory  {
	
	SurveyService getSurveyService();
	
	PickListGroupService getPickListGroupService();

	PickListValueService getPickListValueService();

	QuestionService getQuestionService();

	QuestionGroupService getQuestionGroupService();

	ResponseService getResponseService();

	SectionQuestionMappingService getSectionQuestionMappingService();

	SurveySectionService getSurveySectionService();
	
	SectionScoreService getSectionScoreService();

	HealthService getHealthService();
	
	SurveyServicev2 getSurveyServicev2();
	
	QuestionServicev2 getQuestionServicev2();
	
	ResponseServiceV3 getResponseServiceV3();
	
	SectionScoreServiceV3 getSectionScoreServiceV3();
	
	ClientService getClientService();
	
	ClientSurveySubmissionService getClientSurveySubmissionService();
}