package com.hserv.coordinatedentry.bootstrap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.entity.CustomPicklist;
import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveySection;
import com.hserv.coordinatedentry.repository.CustomPickListRepository;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.repository.SurveySectionRepository;
import com.hserv.coordinatedentry.repository.SurveyRepository;

@Component
public class SampleDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionBankRepository questionBankRepository;

	@Autowired
	private SurveySectionRepository surveySectionRepository;
	
	@Autowired
	private CustomPickListRepository customPickListRepository;
	
	
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
		survey.setSectionId(1);
		
		List<SurveySection> surveySections = new ArrayList<SurveySection>();
		SurveySection surveySection = new SurveySection();
		surveySection.setDateCreated(new Date());
		surveySection.setDateUpdated(new Date());
		//surveySection.setSectionId(12);
		
		List<Question> questions = new ArrayList<>();
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
		
		surveySection.setQuestions(questions);
		surveySection.setSurvey(survey);
		surveySection.setQuestionChild("Child 1");
		surveySection.setQuestionParent("Parent 1");
		surveySection.setRequired("Yes");
		surveySection.setUserId("Admin User");
		surveySection.setSectionDetail("Section Details");
		surveySection.setSectionText("Section text goes here");
		surveySection.setSectionWeight(1);
		
		surveyRepository.save(survey);
		
		surveySection.setQuestionId(question.getQuestionId());
		surveySection.setSurveyId(survey.getSurveyId());
		surveySections.add(surveySection);
		
		surveySectionRepository.save(surveySections);
		
		question.setSurveyId(survey.getSurveyId());
		question.setSurveySection(surveySection);
		questions.add(question);
		questionBankRepository.save(questions);
		
		List <CustomPicklist> customPicklists = new ArrayList<CustomPicklist>();
		customPicklists.add(createPickList("Accept", "Accept", question));
		customPicklists.add(createPickList("Reject", "Reject", question));
		customPickListRepository.save(customPicklists);
	}

	public CustomPicklist createPickList(String key, String value, Question question) {
		CustomPicklist customPickList = new CustomPicklist();
		customPickList.setDateCreated(new Date());
		customPickList.setDateUpdated(new Date());
		customPickList.setInactive(false);
		customPickList.setPicklistKey(key);
		customPickList.setPicklistValue(value);
		customPickList.setUserId("Admin");
		customPickList.setQuestion(question);
		return customPickList ;
	}
}
