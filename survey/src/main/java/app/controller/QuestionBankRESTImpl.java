package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.entity.Question;
import app.repository.QuestionBankRepository;
import app.util.WSResponseStatus;

public class QuestionBankRESTImpl {

	private final QuestionBankRepository questionBankRepository;

	@Autowired
	public QuestionBankRESTImpl(QuestionBankRepository questionBankRepository) {
		this.questionBankRepository = questionBankRepository;
	}

	///service/secure/question/create
	@RequestMapping(method = RequestMethod.POST, value="/service/question/create")
	public @ResponseBody WSResponseStatus createQuestion(@RequestBody Question question){
		WSResponseStatus wsResponseStatus = null;

		Question question1 = null;

		try{
			wsResponseStatus = new WSResponseStatus();
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
	public @ResponseBody WSResponseStatus updateQuestion(@RequestBody Question question){
		WSResponseStatus wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponseStatus();
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
	public @ResponseBody WSResponseStatus deleteQuestion(@RequestParam (value="questionId") Integer questionId){
		WSResponseStatus wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponseStatus();
			questionBankRepository.delete(questionId);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in deleteQuestionAPI"+e.getMessage());
		}

		return wsResponseStatus;
	}

}
