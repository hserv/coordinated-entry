package com.hserv.coordinatedentry.view.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveyQuestion;
import com.hserv.coordinatedentry.view.SurveyQuestionView;
import com.hserv.coordinatedentry.view.SurveyView;

@Component()
public class SurveyConverter {

	public Survey convertSurveyEntityFromView(Survey survey, SurveyView surveyView) {
		
		//survey.setDateCreated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		//survey.setDateUpdated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		survey.setDateCreated(surveyView.getDateCreated());
		survey.setDateUpdated(surveyView.getDateUpdated());
		
		survey.setInactive(surveyView.getInactive());
		survey.setCopySuveryId(surveyView.getCopySuveryId());
		survey.setLocked(surveyView.getLocked());
		survey.setSurveyOwner(surveyView.getSurveyOwner());
		survey.setSurveyTitle(surveyView.getSurveyTitle());
		survey.setTagValue(surveyView.getTagValue());
		survey.setUserId(surveyView.getUserId());
		survey.setSection_id(surveyView.getSection_id());
		
		List<SurveyQuestionView> surveyQuestionViewList = surveyView.getSurveyQuestion();
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		
		if(surveyQuestionViewList!=null && !surveyQuestionViewList.isEmpty())
			for (Iterator iterator = surveyQuestionViewList.iterator(); iterator.hasNext();) {
				SurveyQuestionView surveyQuestionView = (SurveyQuestionView) iterator.next();
				
				SurveyQuestion surveyQuestion = new SurveyQuestion();
				populateSurveyQuestionEntity(surveyQuestion, surveyQuestionView);
				surveyQuestions.add(surveyQuestion);
			}
		
		survey.setSurveyQuestion(surveyQuestions);
	
		return survey;
	}
	
	public void populateSurveyQuestionEntity(SurveyQuestion surveyQuestion, SurveyQuestionView surveyQuestionView){
		surveyQuestion.setDateCreated(surveyQuestionView.getDateCreated());
		surveyQuestion.setDateUpdated(surveyQuestionView.getDateUpdated());
		surveyQuestion.setSectionId(surveyQuestionView.getSectionId());
		//surveyQuestion.setSurvey(survey);
		surveyQuestion.setQuestionChild(surveyQuestionView.getQuestionChild());
		surveyQuestion.setQuestionParent(surveyQuestionView.getQuestionParent());
		surveyQuestion.setRequired(surveyQuestionView.getRequired());
		surveyQuestion.setUserId(surveyQuestionView.getUserId());
		surveyQuestion.setQuestionId(surveyQuestionView.getQuestionId());
		surveyQuestion.setSurveyId(surveyQuestionView.getSurveyId());
		surveyQuestion.setSurveyQuestionId(surveyQuestionView.getSurveyQuestionId());
	}
	/*public List<SurveyView> convertSurveyViewListFromEntityList(List<SurveyView> surveyViewList, List<Survey> surveyList){
		
		if(surveyList==null || surveyList.isEmpty()) return surveyViewList;
		for(Survey survey : surveyList){
			SurveyView surveyView = new SurveyView();
			surveyViewList.add(convertSurveyViewFromEntity(surveyView, survey));
		}
		return surveyViewList;
		
	}*/
	
	public SurveyView convertSurveyViewFromEntity(SurveyView surveyView, Survey survey){
		surveyView.setSurveyId(survey.getSurveyId());
		surveyView.setDateCreated(survey.getDateCreated());
		surveyView.setDateUpdated(survey.getDateUpdated());
		
		surveyView.setInactive(survey.getInactive());
		surveyView.setCopySuveryId(survey.getCopySuveryId());
		surveyView.setLocked(survey.getLocked());
		surveyView.setSurveyOwner(survey.getSurveyOwner());
		surveyView.setSurveyTitle(survey.getSurveyTitle());
		surveyView.setTagValue(survey.getTagValue());
		surveyView.setUserId(survey.getUserId());
		surveyView.setSection_id(survey.getSection_id());
		
		List<SurveyQuestionView> surveyQuestionViewList = new ArrayList<>();
		populateSurveyQuestionMappingList(surveyQuestionViewList, survey.getSurveyQuestion());
		surveyView.setSurveyQuestion(surveyQuestionViewList);
		return surveyView;
	}
	
	private void populateSurveyQuestionMappingList(List<SurveyQuestionView> surveyQuestionViewList, List<SurveyQuestion> surveyQuestionList){
		//
		if(surveyQuestionList==null || surveyQuestionList.isEmpty()) return ;
		for(SurveyQuestion surveyQuestion : surveyQuestionList){
			SurveyQuestionView surveyQuestionView = new SurveyQuestionView();
			populateSurveyQuestionMapping(surveyQuestionView, surveyQuestion);
			surveyQuestionViewList.add(surveyQuestionView);
		}
	}
	
	public void populateSurveyQuestionMapping(SurveyQuestionView surveyQuestionView, SurveyQuestion surveyQuestion){
		surveyQuestionView.setSurveyQuestionId(surveyQuestion.getSurveyQuestionId());
		surveyQuestionView.setDateCreated(surveyQuestion.getDateCreated());
		surveyQuestionView.setDateUpdated(surveyQuestion.getDateUpdated());
		surveyQuestionView.setSectionId(surveyQuestion.getSectionId());
		
		//surveyQuestionView.setQuestion(surveyQuestion.getQuestion());
		//surveyQuestionView.setSurvey(survey);
		surveyQuestionView.setQuestionChild(surveyQuestion.getQuestionChild());
		surveyQuestionView.setQuestionParent(surveyQuestion.getQuestionParent());
		surveyQuestionView.setRequired(surveyQuestion.getRequired());
		surveyQuestionView.setUserId(surveyQuestion.getUserId());
		
		surveyQuestionView.setQuestionId(surveyQuestion.getQuestionId());
		surveyQuestionView.setSurveyId(surveyQuestion.getSurveyId());
	}
}
