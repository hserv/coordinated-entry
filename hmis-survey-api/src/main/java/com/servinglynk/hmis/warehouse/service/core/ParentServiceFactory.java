package com.servinglynk.hmis.warehouse.service.core;

import com.servinglynk.hmis.warehouse.service.PickListGroupService;
import com.servinglynk.hmis.warehouse.service.PickListValueService;
import com.servinglynk.hmis.warehouse.service.QuestionGroupService;
import com.servinglynk.hmis.warehouse.service.QuestionService;
import com.servinglynk.hmis.warehouse.service.ResponseService;
import com.servinglynk.hmis.warehouse.service.SectionQuestionMappingService;
import com.servinglynk.hmis.warehouse.service.SectionScoreService;
import com.servinglynk.hmis.warehouse.service.SurveySectionService;
import com.servinglynk.hmis.warehouse.service.SurveyService;

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

}

