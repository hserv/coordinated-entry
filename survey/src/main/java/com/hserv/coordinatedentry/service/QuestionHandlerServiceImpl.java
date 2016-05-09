package com.hserv.coordinatedentry.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.util.ResponseMessage;
import com.hserv.coordinatedentry.view.QuestionView;
import com.hserv.coordinatedentry.view.mapper.QuestionConverter;
import com.hserv.coordinatedentry.view.mapper.SurveyConverter;


@Transactional
@Service
public class QuestionHandlerServiceImpl implements QuestionHandlerService {
	
	private QuestionBankRepository questionBankRepository;
	private SurveyConverter surveyConverter;
	@Autowired
	QuestionConverter questionConverter;
	@Autowired
	public QuestionHandlerServiceImpl(
			QuestionBankRepository questionBankRepository,
			SurveyConverter surveyConverter) {
		super();
		this.questionBankRepository = questionBankRepository;
		this.surveyConverter = surveyConverter;
	}
	
	@Override
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


	@Override
	public QuestionView getQuestionById(Integer questionId) {
		
		Question question = questionBankRepository.findOne(questionId);
		return questionConverter.populateQuestionViewByQuestionEntity(question);
	}

	@Override
	public List<QuestionView> getAllQuestion() {
		List<Question> questionList = questionBankRepository.findAll();
		return questionConverter.populateQuestionViewListByQuestionEntityList(questionList);
	}

}
