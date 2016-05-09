package com.hserv.coordinatedentry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.service.QuestionHandlerService;
import com.hserv.coordinatedentry.util.WSResponse;
import com.hserv.coordinatedentry.view.QuestionView;

@RestController
public class QuestionBankRESTImpl {

	private final QuestionBankRepository questionBankRepository;
	
	@Autowired
	private QuestionHandlerService questionHandlerService;

	@Autowired
	public QuestionBankRESTImpl(QuestionBankRepository questionBankRepository) {
		this.questionBankRepository = questionBankRepository;
	}

	///service/secure/question/create
	@RequestMapping(method = RequestMethod.POST, value="/question/create")
	public @ResponseBody WSResponse createQuestion(@RequestBody Question question){
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
	@RequestMapping(method = RequestMethod.PUT, value="/question/update")
	public @ResponseBody WSResponse updateQuestion(@RequestBody Question question){
		WSResponse wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponse();
			questionBankRepository.save(question);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatusMessage("success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in UpdateQuestion API"+e.getMessage());
		}

		return wsResponseStatus;
	}

	// /service/secure/question/delete/{questionId}
	@RequestMapping(method = RequestMethod.DELETE, value="/question/delete/id/{questionId}")
	public @ResponseBody WSResponse deleteQuestion(@PathVariable("questionId") Integer questionId){
		WSResponse wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponse();
			questionBankRepository.delete(questionId);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in deleteQuestionAPI"+e.getMessage());
		}

		return wsResponseStatus;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/question/id/{questionId}")
	public @ResponseBody WSResponse getQuestionById(@PathVariable("questionId") Integer questionId){
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
	
	
	@RequestMapping(method = RequestMethod.GET, value="/question/list")
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
