package com.hserv.coordinatedentry.bootstrap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveyQuestion;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.repository.SurveyQuestionRepository;
import com.hserv.coordinatedentry.repository.SurveyRepository;

@Component
public class SampleDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionBankRepository questionBankRepository;

	@Autowired
	private SurveyQuestionRepository surveyQuestionRepository;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Survey survey = new Survey();
		//survey.setDateCreated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		//survey.setDateUpdated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		survey.setDateCreated(new Date());
		survey.setDateUpdated(new Date());
		
		survey.setInactive(false);
		survey.setCopySuveryId(true);
		survey.setLocked(false);
		survey.setSurveyOwner("HMISlynk");
		survey.setSurveyTitle("Sample Survey2");
		survey.setTagValue("Copied from HMIS");
		survey.setUserId("Vijay");
		survey.setSection_id("Section Id");
		
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setDateCreated(new Date());
		surveyQuestion.setDateUpdated(new Date());
		surveyQuestion.setSectionId("Section Id");
		
		Question question = new Question();
		question.setDateCreated(new Date());
		question.setDateUpdated(new Date());
		question.setDisplayText("How frequent you go for vacations");
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
		
		
		questionBankRepository.save(question);
		surveyRepository.save(survey);
		
		surveyQuestion.setQuestionId(question.getQuestionId());
		surveyQuestion.setSurveyId(survey.getSurveyId());
		surveyQuestions.add(surveyQuestion);
		
		surveyQuestionRepository.save(surveyQuestions);
		
	}

}
