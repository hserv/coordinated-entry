package app.bootstrap;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import app.model.Survey;
import app.repository.SurveyRepository;

@Component
public class SampleDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private SurveyRepository surveyRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Survey survey = new Survey();
		survey.setDateCreated(new Date());
		survey.setDateUpdated(new Date());
		survey.setInactive("Active");
		survey.setCopySuveryId(1);
		survey.setLocked(false);
		survey.setSurveyOwner("HMISlync");
		survey.setSurveyQuestionSet(null);
		survey.setSurveyTitle("Sample Survey");
		survey.setTagValuestring("Copied from HMIS");
		survey.setUserId("Vijay");
		
		surveyRepository.save(survey);	
		
	}

}
