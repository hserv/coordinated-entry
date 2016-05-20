package com.hserv.coordinatedentry.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hserv.coordinatedentry.entity.CustomPicklist;
import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.SectionQuestionMapping;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.entity.SurveySection;
import com.hserv.coordinatedentry.repository.CustomPickListRepository;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.repository.SectionQuestionMappingRepository;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyHandlerService.class);

	private SurveyRepository surveyRepository;
	private SurveySectionRepository surveySectionRepository;
	private QuestionBankRepository questionBankRepository;
	private SurveyConverter surveyConverter;
	private CustomPickListRepository customPickListRepository;
	private SectionQuestionMappingRepository sectionQuestionMappingRepository;

	@Autowired
	public SurveyHandlerService(SurveyRepository surveyRepository,
			SurveySectionRepository surveySectionRepository,
			QuestionBankRepository questionBankRepository,
			SurveyConverter surveyConverter, 
			CustomPickListRepository customPickListRepository,
			SectionQuestionMappingRepository sectionQuestionMappingRepository) {
		super();
		this.surveyRepository = surveyRepository;
		this.surveySectionRepository = surveySectionRepository;
		this.questionBankRepository = questionBankRepository;
		this.surveyConverter = surveyConverter;
		this.customPickListRepository =  customPickListRepository;
		this.sectionQuestionMappingRepository = sectionQuestionMappingRepository;
	}

	public List<SurveyView> getSurveyList() {
		List<SurveyView> surveyViewList = null;
		try {
			List<Survey> surveyList = surveyRepository.findAll();
			surveyViewList = surveyConverter.convertSurveyViewListFromEntityList(new ArrayList<SurveyView>(), surveyList);			
		} catch (Exception e) {
			LOGGER.error("Backend server error while fetching surveys");
			return null;
		}
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
			
			
			/*for(SurveySectionView surveySectionView : surveyView.getSurveySection() ){
				SurveySection surveySection  = surveyConverter.populateSurveySectionEntity(surveySectionView, survey);
				surveySectionRepository.save(surveySection);
				
				for (QuestionView questionView : surveySectionView.getQuestions()) {
					Question question = new Question();
					surveyConverter.populateSurveyQuestionEntity(questionView, question, surveySection);
					questionBankRepository.save(question);
					
					
					SectionQuestionMapping sectionQuestionMapping = new SectionQuestionMapping();
					sectionQuestionMapping.setQuestion(question);
					sectionQuestionMapping.setSurveySection(surveySection);
					
					sectionQuestionMappingRepository.save(sectionQuestionMapping);
					
					for (CustomPicklist customPick : questionView.getCustomPicklist()) {
						customPick.setQuestion(question);
						customPickListRepository.save(customPick);
					}
				}
			}*/
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
				SurveySection surveySection = new SurveySection();
				if(surveySectionView!=null && surveySectionView.getSectionId()!=null){
					surveySection = surveySectionRepository.findOne(surveySectionView.getSectionId());
				}
				
				surveyConverter.populateSurveySectionEntity(surveySection, surveySectionView, survey);
				surveySectionRepository.save(surveySection);
				updateQuestions(surveySectionView, surveySection);
			}
			
			return ResponseMessage.SUCCESS;
		}catch(RuntimeException exception){
			return ResponseMessage.FAILURE;
		}
	}
	
	/*public ResponseMessage updateSurvey(SurveyView surveyView) {
		try {
			

			Survey survey = surveyRepository.findOne(surveyView.getSurveyId());

			if (survey == null) {
				throw new SurveyServiceException("Survey not exist");
			}

			surveyConverter.convertSurveyEntityFromView(survey, surveyView);
			surveyRepository.save(survey);

			for (SurveySectionView surveySectionView : surveyView .getSurveySection()) {
				SurveySection surveySection = surveySectionRepository.findOne(surveySectionView.getSectionId());
				surveyConverter.populateSurveySectionEntity(surveySection, surveySectionView, survey);
				surveySectionRepository.save(surveySection);
				updateQuestions(surveySectionView, surveySection);
			}
		} catch (Exception exception) {
			return ResponseMessage.FAILURE;			
		}
		return ResponseMessage.SUCCESS;
	}*/
	
	public void updateQuestions(SurveySectionView surveySectionView, SurveySection surveySection) {
		if (!CollectionUtils.isEmpty(surveySectionView.getQuestions())) {
			for(QuestionView questionView : surveySectionView.getQuestions()){
				Question question = new Question();
				
				boolean isAddQuestionMapping = true;
				if(questionView!=null && questionView.getQuestionId()!=null){
					question = questionBankRepository.findOne(questionView.getQuestionId());
					isAddQuestionMapping = false;
				}
				surveyConverter.populateSurveyQuestionEntity(questionView, question, surveySection);
				questionBankRepository.save(question);
				
				if (isAddQuestionMapping) {
					SectionQuestionMapping sectionQuestionMapping = new SectionQuestionMapping();
					sectionQuestionMapping.setQuestion(question);
					sectionQuestionMapping.setSurveySection(surveySection);	
					sectionQuestionMappingRepository.save(sectionQuestionMapping);
				}
				
				
				
				updatePickList(questionView, question);
			}
		}
	}
	
	
	public void updatePickList(QuestionView questionView, Question question){
		
		for (CustomPicklist customPick : questionView.getCustomPicklist()) {
			CustomPicklist customPicklist = new CustomPicklist();
			if(customPick!=null && customPick.getPicklistId()!=null)
				customPicklist = customPickListRepository.findOne(customPick.getPicklistId());
			
			BeanUtils.copyProperties(customPick, customPicklist);
			customPicklist.setQuestion(question);
			customPickListRepository.save(customPicklist);
		}
	}
	
	public ResponseMessage deleteSurvey(Integer surveyId){
		try{
			Survey survey = surveyRepository.getOne(surveyId);
			
			if (survey == null) {
				return ResponseMessage.FAILURE;
			}
			
			//first delete mapping between survey section & question
			List<SurveySection> surveySections = survey.getSurveySection();			
			if (!CollectionUtils.isEmpty(surveySections)) {
				sectionQuestionMappingRepository.deleteBySurveySectionIn(surveySections);
			}
			
			//surveyRepository.delete(surveyId);
			surveyRepository.delete(survey);
			
		}catch(DataAccessException exception){
			return ResponseMessage.FAILURE;
		}
		return ResponseMessage.SUCCESS;
	}
	
}

