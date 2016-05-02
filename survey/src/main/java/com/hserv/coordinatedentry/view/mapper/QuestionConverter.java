package com.hserv.coordinatedentry.view.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.SurveySection;
import com.hserv.coordinatedentry.view.QuestionView;

@Component()
public class QuestionConverter {

	public Question populateSurveyQuestionEntity(QuestionView questionView,
			Question question, SurveySection surveySection) {
		question.setDateCreated(new Date());
		question.setDateUpdated(new Date());
		question.setDisplayText(questionView.getDisplayText());
		question.setHudBoolean(questionView.getHudBoolean());
		question.setInactive(questionView.getInactive());
		question.setIsCopyQuestionId(questionView.getIsCopyQuestionId());
		question.setLabelValue(questionView.getLabelValue());
		question.setLocked(questionView.getLocked());
		question.setOptionsSingleMultipleSelect(questionView
				.getOptionsSingleMultipleSelect());
		question.setQuestionDataType(questionView.getQuestionDataType());
		question.setQuestionGroupId(questionView.getQuestionGroupId());
		question.setQuestionName(questionView.getQuestionName());
		// question.setSurveyQuestion(surveyQuestions);
		question.setUserId(questionView.getUserId());
		question.setSurveyId(questionView.getSurveyId());
		// question.setSurveySection(surveySection);
		// question.getCustomPicklist().addAll(questionView.getCustomPicklist());
		return question;
	}

	public QuestionView populateQuestionViewByQuestionEntity(Question question) {
		QuestionView questionView = new QuestionView();
		questionView.setQuestionId(question.getQuestionId());
		questionView.setDateCreated(question.getDateCreated());
		questionView.setDateUpdated(question.getDateUpdated());
		questionView.setDisplayText(question.getDisplayText());
		questionView.setHudBoolean(question.getHudBoolean());
		questionView.setInactive(question.getInactive());
		questionView.setCustomPicklist(question.getCustomPicklist());
		questionView.setIsCopyQuestionId(question.getIsCopyQuestionId());
		questionView.setLabelValue(question.getLabelValue());
		questionView.setLocked(question.getLocked());
		questionView.setIsCopyQuestionId(question.getOptionsSingleMultipleSelect());
		questionView.setQuestionDataType(question.getQuestionDataType());
		questionView.setQuestionGroupId(question.getQuestionGroupId());
		questionView.setQuestionName(question.getQuestionName());
		questionView.setSurveyId(question.getSurveyId());
		questionView.setUserId(question.getUserId());
		questionView.setSurveySection(question.getSurveySection());
		
		
		
		return questionView;
	}

	public List<QuestionView> populateQuestionViewListByQuestionEntityList(
			List<Question> question) {
		Iterator iterator = question.iterator();
		List<QuestionView> questionViewList = new ArrayList<QuestionView>();
		QuestionView questionView = null;
		while (iterator.hasNext()) {
			questionView = populateQuestionViewByQuestionEntity((Question) iterator
					.next());
			questionViewList.add(questionView);
		}
		return questionViewList;
	}

}
