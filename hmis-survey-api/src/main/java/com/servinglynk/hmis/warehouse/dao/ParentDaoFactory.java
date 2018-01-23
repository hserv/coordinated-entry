package com.servinglynk.hmis.warehouse.dao;

public interface ParentDaoFactory  {
	
	 SurveyDao getSurveyDao();

	 PickListGroupEntityDao getPickListGroupEntityDao();

	 PickListValueEntityDao getPickListValueEntityDao();

	 QuestionEntityDao getQuestionEntityDao();

	 QuestionGroupEntityDao getQuestionGroupEntityDao();

	 ResponseEntityDao getResponseEntityDao();

	 SectionQuestionMappingEntityDao getSectionQuestionMappingEntityDao();


	 SurveyEntityDao getSurveyEntityDao();

	 SurveySectionEntityDao getSurveySectionEntityDao();
	 
	 SectionScoreDao getSectionScoreDao();
	 
	 HealthDao getHealthDao();
	 
	 ClientDao getClientDao();
	 
	 ClientSurveySubmissionEntityDao getClientSubmissionEntityDao(); 

}