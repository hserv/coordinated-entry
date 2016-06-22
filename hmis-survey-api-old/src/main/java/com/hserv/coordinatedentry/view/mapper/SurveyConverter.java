package com.hserv.coordinatedentry.view.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveySection;
import com.hserv.coordinatedentry.view.QuestionView;
import com.hserv.coordinatedentry.view.SurveySectionView;
import com.hserv.coordinatedentry.view.SurveyView;

@Component()
public class SurveyConverter {

	public Survey convertSurveyEntityFromView(Survey survey, SurveyView surveyView) {
		
		//survey.setDateCreated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		//survey.setDateUpdated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
//		survey.setDateCreated(surveyView.getDateCreated());
//		survey.setDateUpdated(surveyView.getDateUpdated());
		
		survey.setInactive(surveyView.getInactive());
		survey.setCopySuveryId(surveyView.getCopySuveryId());
		survey.setLocked(surveyView.getLocked());
		survey.setSurveyOwner(surveyView.getSurveyOwner());
		survey.setSurveyTitle(surveyView.getSurveyTitle());
		survey.setTagValue(surveyView.getTagValue());
		survey.setUserId(surveyView.getUserId());
		survey.setSectionId(surveyView.getSectionId());
		
		return survey;
	}
	
	public List<SurveySection> populateSurveySectionEntityList(Survey survey, List<SurveySectionView> surveySectionViewList){
		
		List<SurveySection> surveySectionList = new ArrayList<>();
		
		if(surveySectionViewList!=null && !surveySectionViewList.isEmpty())
			for (Iterator iterator = surveySectionViewList.iterator(); iterator.hasNext();) {
				SurveySectionView surveySectionView = (SurveySectionView) iterator.next();
				
				SurveySection surveySection = populateSurveySectionEntity( surveySectionView, survey);
				surveySectionList.add(surveySection);
			}
		
		return surveySectionList;
	}
	
	public SurveySection populateSurveySectionEntity( SurveySectionView surveySectionView, Survey survey){
		SurveySection surveySection = new SurveySection();
		return populateSurveySectionEntity( surveySection , surveySectionView, survey);
	}
	
	
	public SurveySection populateSurveySectionEntity(SurveySection surveySection ,  SurveySectionView surveySectionView, Survey survey){
		surveySection.setDateCreated(surveySectionView.getDateCreated());
		surveySection.setDateUpdated(surveySectionView.getDateUpdated());
		surveySection.setSectionId(surveySectionView.getSectionId());
		surveySection.setSurvey(survey);
		surveySection.setQuestionChild(surveySectionView.getQuestionChild());
		surveySection.setQuestionParent(surveySectionView.getQuestionParent());
		surveySection.setRequired(surveySectionView.getRequired());
		surveySection.setUserId(surveySectionView.getUserId());
		surveySection.setQuestionId(surveySectionView.getQuestionId());
		surveySection.setSurveyId(survey.getSurveyId());
		surveySection.setSectionDetail(surveySectionView.getSectionDetail());
		surveySection.setSectionText(surveySectionView.getSectionText());
		surveySection.setSectionWeight(surveySectionView.getSectionWeight());
		//surveySection.setQuestions(populateSurveyQuestionEntityList(surveySectionView, surveySection));
		return surveySection;
}
	
	public List<Question> populateSurveyQuestionEntityList(SurveySectionView surveySectionView, SurveySection surveySection){
		List<Question> questions = new ArrayList<>();
		for (QuestionView questionView : surveySectionView.getQuestions()) {
			Question question = new Question();
			populateSurveyQuestionEntity(questionView, question, surveySection);
			questions.add(question);
		}
		return questions;
	}
	public Question populateSurveyQuestionEntity(QuestionView questionView, Question question, SurveySection surveySection){
		question.setDateCreated(new Date());
		question.setDateUpdated(new Date());
		question.setDisplayText(questionView.getDisplayText());
		question.setHudBoolean(questionView.getHudBoolean());
		question.setInactive(questionView.getInactive());
		question.setIsCopyQuestionId(questionView.getIsCopyQuestionId());
		question.setLabelValue(questionView.getLabelValue());
		question.setLocked(questionView.getLocked());
		question.setOptionsSingleMultipleSelect(questionView.getOptionsSingleMultipleSelect());
		question.setQuestionDataType(questionView.getQuestionDataType());
		question.setQuestionGroupId(questionView.getQuestionGroupId());
		question.setQuestionName(questionView.getQuestionName());
		//question.setSurveyQuestion(surveyQuestions);
		question.setUserId(questionView.getUserId());
		
		/*List<SurveySection> surveySections = new ArrayList<SurveySection>();
		surveySections.add(surveySection);
		question.setSurveySection(surveySections);*/
		
 		question.setSurveySection(surveySection);
		
		//question.getCustomPicklist().addAll(questionView.getCustomPicklist());
		return question;
	}
	
	
	public List<SurveyView> convertSurveyViewListFromEntityList(List<SurveyView> surveyViewList, List<Survey> surveyList){		
		if(CollectionUtils.isEmpty(surveyList)) {
			return surveyViewList;
		}
		
		for(Survey survey : surveyList){
			surveyViewList.add(populateSurveyDescriptionListFromEntity(new SurveyView(), survey));
		}
		return surveyViewList;
		
	}
	
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
		surveyView.setSectionId(survey.getSectionId());
		
		List<SurveySectionView> surveyQuestionViewList = new ArrayList<>();
		populateSurveySectionMappingList(surveyQuestionViewList, survey.getSurveySection());
		surveyView.setSurveySection(surveyQuestionViewList);
		return surveyView;
	}
	
	public SurveyView populateSurveyDescriptionListFromEntity(SurveyView surveyView, Survey survey){
		surveyView.setSurveyId(survey.getSurveyId());
		surveyView.setSurveyTitle(survey.getSurveyTitle());
		surveyView.setDateCreated(survey.getDateCreated());
		surveyView.setDateUpdated(survey.getDateUpdated());
		surveyView.setInactive(survey.getInactive());
		surveyView.setCopySuveryId(survey.getCopySuveryId());		
		return surveyView;
	}
	
	private void populateSurveySectionMappingList(List<SurveySectionView> surveyQuestionViewList, List<SurveySection> surveyQuestionList){
		//
		if(surveyQuestionList==null || surveyQuestionList.isEmpty()) return ;
		for(SurveySection surveyQuestion : surveyQuestionList){
			SurveySectionView surveySectionView = new SurveySectionView();
			populateSurveySectionMapping(surveySectionView, surveyQuestion);
			surveyQuestionViewList.add(surveySectionView);
		}
	}
	
	
	public void populateSurveySectionMapping(SurveySectionView surveySectionView, SurveySection surveySection){
		surveySectionView.setDateCreated(surveySection.getDateCreated());
		surveySectionView.setDateUpdated(surveySection.getDateUpdated());
		surveySectionView.setSectionId(surveySection.getSectionId());
		
		//surveyQuestionView.setQuestion(surveyQuestion.getQuestion());
		//surveyQuestionView.setSurvey(survey);
		surveySectionView.setQuestionChild(surveySection.getQuestionChild());
		surveySectionView.setQuestionParent(surveySection.getQuestionParent());
		surveySectionView.setRequired(surveySection.getRequired());
		surveySectionView.setUserId(surveySection.getUserId());
		
		surveySectionView.setQuestionId(surveySection.getQuestionId());
		surveySectionView.setSurveyId(surveySection.getSurveyId());
		surveySectionView.setSectionDetail(surveySection.getSectionDetail());
		surveySectionView.setSectionText(surveySection.getSectionText());
		surveySectionView.setSectionWeight(surveySection.getSectionWeight());
	}
	
	//public void populateQuestionViewFromRepo(Question)
}
