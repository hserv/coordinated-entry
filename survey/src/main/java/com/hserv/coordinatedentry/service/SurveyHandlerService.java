package com.hserv.coordinatedentry.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveyQuestion;
import com.hserv.coordinatedentry.repository.SurveyQuestionRepository;
import com.hserv.coordinatedentry.repository.SurveyRepository;
import com.hserv.coordinatedentry.util.ResponseMessage;
import com.hserv.coordinatedentry.view.SurveyQuestionView;
import com.hserv.coordinatedentry.view.SurveyView;
import com.hserv.coordinatedentry.view.mapper.SurveyConverter;

@Transactional
@Service
public class SurveyHandlerService {

	private SurveyRepository surveyRepository;
	private SurveyQuestionRepository surveyQuestionRepository;
	private SurveyConverter surveyConverter;

	@Autowired
	public SurveyHandlerService(SurveyRepository surveyRepository,
			SurveyQuestionRepository surveyQuestionRepository,
			SurveyConverter surveyConverter) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveyQuestionRepository = surveyQuestionRepository;
		this.surveyConverter = surveyConverter;
	}

	public List<Survey> getSurveyList() {
		List<Survey> surveyList = surveyRepository.findAll();
		//List<SurveyView> surveyViewList = new ArrayList<SurveyView>(); 
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
			surveyRepository.save(survey);
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyQuestionRepository.save(survey.getSurveyQuestion());
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
	public ResponseMessage updateSurvey(SurveyView surveyView) {
		try{
			Survey survey = surveyRepository.findOne(surveyView.getSurveyId());
			//surveyQuestionRepository.findBySurveyId(surveyView.getSurveyId());
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.saveAndFlush(survey);
			for(SurveyQuestionView surveyQuestionView : surveyView.getSurveyQuestion()){
				System.out.println("surveyQuestion.getSurveyQuestionId() :"+surveyQuestionView.getSurveyQuestionId());
				SurveyQuestion surveyQuestion = surveyQuestionRepository.findOne(surveyQuestionView.getSurveyQuestionId());
				surveyConverter.populateSurveyQuestionMapping(surveyQuestionView, surveyQuestion);
				surveyQuestionRepository.saveAndFlush(surveyQuestion);
			}
			
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
	public ResponseMessage deleteSurvey(Integer surveyId){
		try{
			surveyRepository.delete(surveyId);
		}catch(DataAccessException exception){
			return ResponseMessage.FAILURE;
		}
		return ResponseMessage.SUCCESS;
	}
	
}

