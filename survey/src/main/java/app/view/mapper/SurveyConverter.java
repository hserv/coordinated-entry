package app.view.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.entity.Question;
import app.entity.Survey;
import app.entity.SurveyQuestion;


public class SurveyConverter {

	public Survey convertSurveyEntityFromView(app.view.Survey surveyView) {
		
		Survey survey = new Survey();
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
		
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setDateCreated(new Date());
		surveyQuestion.setDateUpdated(new Date());
		surveyQuestion.setSectionId("Section Id");
		
		Question question = new Question();
		question.setDateCreated(new Date());
		question.setDateUpdated(new Date());
		question.setDisplayText("How frequest you go for vacations");
		question.setHudBoolean(true);
		question.setInactive(true);
		question.setIsCopyQuestionId(false);
		question.setLabelValue("HMIS Question");
		question.setLocked(false);
		question.setOptionsSingleMultipleSelect(true);
		question.setQuestionDataType("Text");
		question.setQuestionGroupId("Group1");
		question.setQuestionName("Sample Question 1");
		//question.setSurveyQuestion(surveyQuestions);
		question.setUserId("Admin User");
		//
		
		//survey.setSurveyQuestion(surveyQuestions );
		
		surveyQuestion.setQuestion(question );
		surveyQuestion.setSurvey(survey);
		surveyQuestion.setQuestionChild("Child 1");
		surveyQuestion.setQuestionParent("Parent 1");
		surveyQuestion.setRequired("Yes");
		surveyQuestion.setUserId("Admin User");
		
		
		
		surveyQuestion.setQuestionId(question.getQuestionId());
		surveyQuestion.setSurveyId(survey.getSurveyId());
		surveyQuestions.add(surveyQuestion);
		
		
		return null;
		
	}
}
