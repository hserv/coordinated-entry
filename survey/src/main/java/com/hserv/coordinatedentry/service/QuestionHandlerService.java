package com.hserv.coordinatedentry.service;

import java.util.List;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.view.QuestionView;

public interface QuestionHandlerService {

	public Question createQuestion(Question question);
	
	public QuestionView getQuestionById(Integer questionId);

	public List<QuestionView> getAllQuestion();
}
