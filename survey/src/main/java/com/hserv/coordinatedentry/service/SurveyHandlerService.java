package com.hserv.coordinatedentry.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.entity.CustomPicklist;
import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveySection;
import com.hserv.coordinatedentry.repository.CustomPickListRepository;
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
	private CustomPickListRepository customPickListRepository;

	@Autowired
	public SurveyHandlerService(SurveyRepository surveyRepository,
			SurveySectionRepository surveySectionRepository,
			QuestionBankRepository questionBankRepository,
			SurveyConverter surveyConverter, 
			CustomPickListRepository customPickListRepository) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveySectionRepository = surveySectionRepository;
		this.questionBankRepository = questionBankRepository;
		this.surveyConverter = surveyConverter;
		this.customPickListRepository =  customPickListRepository;
	}

	public List<SurveyView> getSurveyList() {
		List<Survey> surveyList = surveyRepository.findAll();
		List<SurveyView> surveyViewList = new ArrayList<SurveyView>(); 
		surveyViewList = surveyConverter.convertSurveyViewListFromEntityList(surveyViewList, surveyList);
		return surveyViewList;
	}
	
	public List<Survey> getAllSurveyList() {
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
	public Survey getSurveyBySurveyIdAndUserId(Integer surveyId, String userId) {
		Survey survey = surveyRepository.findBySurveyIdAndUserId(surveyId,userId);
		SurveyView surveyView = new SurveyView(); 
		surveyConverter.convertSurveyViewFromEntity(surveyView, survey);
		return survey;
	}
		
	
	public ResponseMessage createSurvey(SurveyView surveyView) {
		try{
			Survey survey = new Survey();
			
			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.save(survey);
			
			
			for(SurveySectionView surveySectionView : surveyView.getSurveySection() ){
				SurveySection surveySection  = surveyConverter.populateSurveySectionEntity(surveySectionView, survey);
				surveySectionRepository.save(surveySection);
				
				for (QuestionView questionView : surveySectionView.getQuestions()) {
					Question question = new Question();
					surveyConverter.populateSurveyQuestionEntity(questionView, question, surveySection);
					questionBankRepository.save(question);
					
					for (CustomPicklist customPick : questionView.getCustomPicklist()) {
						customPick.setQuestion(question);
						customPickListRepository.save(customPick);
					}
				}
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
			surveyRepository.save(survey);
			for(SurveySectionView surveySectionView : surveyView.getSurveySection()){
				System.out.println("surveyQuestion.getSurveyQuestionId() :"+surveySectionView.getSectionId());
				SurveySection surveySection = surveySectionRepository.findOne(surveySectionView.getSectionId());
				
				surveyConverter.populateSurveySectionEntity(surveySection, surveySectionView, survey);
				surveySectionRepository.save(surveySection);
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
			surveyConverter.populateSurveyQuestionEntity(questionView, question, surveySection);
			questionBankRepository.save(question);
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

