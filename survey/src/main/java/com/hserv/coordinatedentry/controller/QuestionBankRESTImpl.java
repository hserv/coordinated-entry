package com.hserv.coordinatedentry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.repository.QuestionBankRepository;
import com.hserv.coordinatedentry.util.WSResponse;

public class QuestionBankRESTImpl {

	private final QuestionBankRepository questionBankRepository;

	@Autowired
	public QuestionBankRESTImpl(QuestionBankRepository questionBankRepository) {
		this.questionBankRepository = questionBankRepository;
	}

	///service/secure/question/create
	@RequestMapping(method = RequestMethod.POST, value="/service/question/create")
	public @ResponseBody WSResponse createQuestion(@RequestBody Question question){
		WSResponse wsResponseStatus = null;

		Question question1 = null;

		try{
			wsResponseStatus = new WSResponse();
			question1 = questionBankRepository.save(question);
			wsResponseStatus.setData(question1);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in createQuestion API"+e.getMessage());
		}

		return wsResponseStatus;
	}

	// /service/secure/question/update
	@RequestMapping(method = RequestMethod.PUT, value="/service/question/update")
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
	@RequestMapping(method = RequestMethod.GET, value="/service/question/delete")
	public @ResponseBody WSResponse deleteQuestion(@RequestParam (value="questionId") Integer questionId){
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

}
