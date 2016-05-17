package com.hserv.coordinatedentry.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.SectionQuestionMapping;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.repository.SectionQuestionMappingRepository;
import com.hserv.coordinatedentry.view.QuestionView;
import com.hserv.coordinatedentry.view.mapper.QuestionConverter;


@Transactional
@Service
public class QuestionHandlerService {
	
	private QuestionBankRepository questionBankRepository;
	
	private  SectionQuestionMappingRepository sectionQuestionMappingRepository;
	
	@Autowired
	QuestionConverter questionConverter;
	@Autowired
	public QuestionHandlerService(
			QuestionBankRepository questionBankRepository,
			SectionQuestionMappingRepository sectionQuestionMappingRepository) {
		super();
		this.questionBankRepository = questionBankRepository;
		this.sectionQuestionMappingRepository = sectionQuestionMappingRepository;
	}
	
//	@Override
	public Question createQuestion(Question question) {
		try {

			//Question question = new Question();
			Question result = questionConverter.setCustomPickListData(question);
			Question questionBean = questionBankRepository.save(result);
			return questionBean;
		} catch (RuntimeException exception) {
			return new Question();
		}
	}


	public boolean updateQuestion(Question question){
		try{
			questionBankRepository.save(question);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
//	@Override
	public QuestionView getQuestionById(Integer questionId) {
		
		Question question = questionBankRepository.findOne(questionId);
		return questionConverter.populateQuestionViewByQuestionEntity(question);
	}

//	@Override
	public List<QuestionView> getAllQuestion() {
		List<Question> questionList = questionBankRepository.findAll();
		return questionConverter.populateQuestionViewListByQuestionEntityList(questionList);
	}

//	@Override
	public boolean deleteQuestion(Integer questionId) {
		try {
			List<SectionQuestionMapping> sectionQuestionMappings = sectionQuestionMappingRepository.getAllSectionQuestionMapping(questionId);
			if (!CollectionUtils.isEmpty(sectionQuestionMappings)){
				sectionQuestionMappingRepository.delete(sectionQuestionMappings);
			}
			questionBankRepository.delete(questionId);			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
