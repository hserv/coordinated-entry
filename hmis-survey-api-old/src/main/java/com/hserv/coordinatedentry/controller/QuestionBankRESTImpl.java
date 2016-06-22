package com.hserv.coordinatedentry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.annotation.APIMapping;
import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.exception.InvalidArgumentException;
import com.hserv.coordinatedentry.exception.SurveyControllerException;
import com.hserv.coordinatedentry.service.QuestionHandlerService;
import com.hserv.coordinatedentry.util.WSResponse;
import com.hserv.coordinatedentry.view.QuestionView;

@RestController
@RequestMapping("/questions")
public class QuestionBankRESTImpl {

	@Autowired
	private QuestionHandlerService questionHandlerService;

	//@Autowired
	public QuestionBankRESTImpl() {
		/*this.questionBankRepository = questionBankRepository;
		this.sectionQuestionMappingRepository = sectionQuestionMappingRepository;*/
	}

	///service/secure/question/create
	@RequestMapping(method = RequestMethod.POST, value="/")
	@APIMapping(value="SURVEY_API_CREATE_QUESTION")
	public @ResponseBody WSResponse createQuestion(@RequestBody @Validated Question question){
		WSResponse wsResponseStatus = null;

		Question questionBean = null;

		try{
			wsResponseStatus = new WSResponse();
			questionBean = questionHandlerService.createQuestion(question);
			wsResponseStatus.setData(questionBean.getQuestionId());
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in createQuestion API"+e.getMessage());
		}

		return wsResponseStatus;
	}


	// /service/secure/question/update
	@RequestMapping(method = RequestMethod.PUT, value="/{question_id}")
	@APIMapping(value="SURVEY_API_UPDATE_QUESTION")
	public @ResponseBody WSResponse updateQuestion(@PathVariable("question_id") Integer questionId, @RequestBody  @Validated Question question){
		try{
			
			if (questionId == null || questionId < 1) {
				throw new InvalidArgumentException ("Invalid question id");
			}
			
			question.setQuestionId(questionId);
			
			boolean response = questionHandlerService.updateQuestion(question);
			
			if(!response) {
				throw new SurveyControllerException("Backend server error while updating question, please try again!");
			} 
			
			return new WSResponse();
			
		}catch(InvalidArgumentException e){
			throw e;
		}catch(SurveyControllerException e){
			throw e;
		}catch(Exception e){
			throw new SurveyControllerException("Backend server error, please try again!");
		}
	}

	// /service/secure/question/delete/{questionId}
	@RequestMapping(method = RequestMethod.DELETE, value="/{question_id}")
	@APIMapping(value="SURVEY_API_DELETE_QUESTION")
	public @ResponseBody WSResponse deleteQuestion(@PathVariable("question_id") Integer questionId){

		try{
			
			if (questionId == null || questionId < 1) {
				throw new InvalidArgumentException ("Invalid question id");
			}
			
			boolean response = questionHandlerService.deleteQuestion(questionId);
			
			if(!response) {
				throw new SurveyControllerException("Backend server error while deleting question, please try again!");
			} 
			
			return new WSResponse();
			
		}catch(InvalidArgumentException e){
			throw e;
		}catch(SurveyControllerException e){
			throw e;
		}catch(Exception e){
			throw new SurveyControllerException("Backend server error, please try again!");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{question_id}")
	@APIMapping(value="SURVEY_API_GET_QUESTION_BY_ID")
	public @ResponseBody WSResponse getQuestionById(@PathVariable("question_id") Integer questionId){
		WSResponse wsResponseStatus = null;
		
		QuestionView questionView = null;

		try{
			wsResponseStatus = new WSResponse();
			questionView = questionHandlerService.getQuestionById(questionId);
			wsResponseStatus.setData(questionView);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in createQuestion API"+e.getMessage());
		}

		return wsResponseStatus;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	@APIMapping(value="SURVEY_API_GET_ALL_QUESTION")
	public @ResponseBody WSResponse getAllQuestion(){
		WSResponse wsResponseStatus = null;
		
		List<QuestionView> questionViewList = null;

		try{
			wsResponseStatus = new WSResponse();
			questionViewList = questionHandlerService.getAllQuestion();
			wsResponseStatus.setData(questionViewList);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in createQuestion API"+e.getMessage());
		}

		return wsResponseStatus;
	}

}
