package com.hserv.coordinatedentry.service;

import java.util.List;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.util.ResponseMessage;
import com.hserv.coordinatedentry.view.QuestionView;

public interface QuestionHandlerService {

	public ResponseMessage createQuestion(QuestionView questionView);
	
	public QuestionView getQuestionById(Integer questionId);

	public List<QuestionView> getAllQuestion();
}
