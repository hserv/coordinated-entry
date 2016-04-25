package com.hserv.coordinatedentry.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveySection;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.repository.SurveyRepository;
import com.hserv.coordinatedentry.repository.SurveySectionRepository;
import com.hserv.coordinatedentry.util.ResponseMessage;
import com.hserv.coordinatedentry.view.QuestionView;
import com.hserv.coordinatedentry.view.SurveySectionView;
import com.hserv.coordinatedentry.view.SurveyView;
import com.hserv.coordinatedentry.view.mapper.SurveyConverter;

@Transactional
@Service
public class SurveyHandlerService {

	private SurveyRepository surveyRepository;
	private SurveySectionRepository surveySectionRepository;
	private QuestionBankRepository questionBankRepository;
	private SurveyConverter surveyConverter;

	@Autowired
	public SurveyHandlerService(SurveyRepository surveyRepository,
			SurveySectionRepository surveySectionRepository,
			QuestionBankRepository questionBankRepository,
			SurveyConverter surveyConverter) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveySectionRepository = surveySectionRepository;
		this.questionBankRepository = questionBankRepository;
		this.surveyConverter = surveyConverter;
	}

	public List<Survey> getSurveyList() {
		List<Survey> surveyList = surveyRepository.findAll();
		//List<SurveyView> surveyViewList = new ArrayList<SurveyView>(); 
		//surveyConverter.convertSurveyViewListFromEntityList(surveyViewList, surveyList);
		return surveyList;
	}
	
	public Survey getSurveyById(Integer surveyId) {
		Survey survey = surveyRepository.findOne(surveyId);
		SurveyView surveyView = new SurveyView(); 
		surveyConverter.convertSurveyViewFromEntity(surveyView, survey);
		return survey;
	}
	
	public ResponseMessage createSurvey(SurveyView surveyView) {
		try{
			Survey survey = new Survey();
			
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.save(survey);
			
			List<SurveySection> surveyQuestionsList = surveyConverter.populateSurveySectionEntityList(survey, surveyView.getSurveySection());
			
			surveySectionRepository.save(surveyQuestionsList);
			for (Iterator iterator = surveyQuestionsList.iterator(); iterator
					.hasNext();) {
				SurveySection surveySection = (SurveySection) iterator.next();
				questionBankRepository.save(surveySection.getQuestions());
			}
			
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
	public ResponseMessage updateSurvey(SurveyView surveyView) {
		try{
			Survey survey = surveyRepository.findOne(surveyView.getSurveyId());
			//surveySectionRepository.findBySurveyId(surveyView.getSurveyId());
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.saveAndFlush(survey);
			for(SurveySectionView surveySectionView : surveyView.getSurveySection()){
				System.out.println("surveyQuestion.getSurveyQuestionId() :"+surveySectionView.getSectionId());
				SurveySection surveySection = surveySectionRepository.findOne(surveySectionView.getSectionId());
				
				surveyConverter.populateSurveySectionEntity(surveySection, surveySectionView, survey);
				surveySectionRepository.saveAndFlush(surveySection);
				updateQuestions(surveySectionView, surveySection);
			}
			
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
	public void updateQuestions(SurveySectionView surveySectionView, SurveySection surveySection) {
		for(QuestionView questionView : surveySectionView.getQuestions()){
			Question question = questionBankRepository.findOne(questionView.getQuestionId());
			surveyConverter.populateSurveyQuestionEntity(surveySection, question);
			questionBankRepository.saveAndFlush(question);
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

