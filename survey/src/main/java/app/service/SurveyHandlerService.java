package app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Survey;
import app.repository.SurveyRepository;
import app.util.ResponseMessage;
import app.view.SurveyView;
import app.view.mapper.SurveyConverter;

@Transactional
@Service
public class SurveyHandlerService {

	private SurveyRepository surveyRepository;
	private SurveyConverter surveyConverter;

	@Autowired
	public SurveyHandlerService(SurveyRepository surveyRepository,
			SurveyConverter surveyConverter) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveyConverter = surveyConverter;
	}

	public List<Survey> getSurveyList() {
		List<Survey> surveyList = surveyRepository.findAll();
		List<SurveyView> surveyViewList = new ArrayList<SurveyView>(); 
		//surveyConverter.convertSurveyViewListFromEntityList(surveyViewList, surveyList);
		return surveyList;
	}
	
	public SurveyView getSurveyById(Integer surveyId) {
		Survey survey = surveyRepository.findOne(surveyId);
		SurveyView surveyView = new SurveyView(); 
		surveyConverter.convertSurveyViewFromEntity(surveyView, survey);
		return surveyView;
	}
	
	public ResponseMessage createSurvey(SurveyView surveyView) {
		try{
			Survey survey = new Survey();
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.save(survey);
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
	public ResponseMessage updateSurvey(SurveyView surveyView) {
		try{
			Survey survey = surveyRepository.findOne(surveyView.getSurveyId());
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.saveAndFlush(survey);
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
}
