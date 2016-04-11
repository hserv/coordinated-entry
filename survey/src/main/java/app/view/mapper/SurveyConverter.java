package app.view.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.entity.Survey;
import app.entity.SurveyQuestion;
import app.view.SurveyQuestionView;
import app.view.SurveyView;

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
		
		return survey;
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
			surveyQuestionViewList.add(surveyQuestionView);
		}
	}
}
