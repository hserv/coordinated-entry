package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.Questionsv2;
import com.servinglynk.hmis.warehouse.core.model.Questionv2;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping("/v2/questiongroups")
public class QuestionsControllerv2 extends BaseController {
	
	
	@RequestMapping(value="/{questiongroupid}/questions",method=RequestMethod.POST)
	   @APIMapping(value="SURVEY_API_CREATE_QUESTION",checkTrustedApp=true,checkSessionToken=true)
	   public Questionv2 createQuestion(@PathVariable( "questiongroupid" ) UUID questionGroupId,@Valid @RequestBody Questionv2 question,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        question.setQuestionGroupId(questionGroupId);
	         serviceFactory.getQuestionServicev2().createQuestion(question,session.getAccount().getUsername()); 
	         Questionv2 returnQuestion = new Questionv2();
	         returnQuestion.setQuestionId(question.getQuestionId());
	         return returnQuestion;
	   }

	   @RequestMapping(value="/{questiongroupid}/questions/{questionid}",method=RequestMethod.PUT)
	   @APIMapping(value="SURVEY_API_UPDATE_QUESTION",checkTrustedApp=true,checkSessionToken=true)
	   public void updateQuestion(@PathVariable( "questiongroupid" ) UUID questionGroupId,
			   @PathVariable( "questionid" ) UUID questionId,@Valid @RequestBody Questionv2 question,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        question.setQuestionId(questionId);
	        question.setQuestionGroupId(questionGroupId);
	        serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
	        serviceFactory.getQuestionServicev2().updateQuestion(question,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{questiongroupid}/questions/{questionid}",method=RequestMethod.DELETE)
	   @APIMapping(value="SURVEY_API_DELETE_QUESTION",checkTrustedApp=true,checkSessionToken=true)
	   public void deleteQuestion(@PathVariable( "questiongroupid" ) UUID questionGroupId,
			   @PathVariable( "questionid" ) UUID questionId,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
	        serviceFactory.getQuestionServicev2().deleteQuestion(questionId,session.getAccount().getUsername()); 
	        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
	   }

	   @RequestMapping(value="/{questiongroupid}/questions/{questionid}",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_QUESTION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Questionv2 getQuestionById(@PathVariable( "questiongroupid" ) UUID questionGroupId,@PathVariable( "questionid" ) UUID QuestionId,HttpServletRequest request) throws Exception{
	       serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
		   return serviceFactory.getQuestionServicev2().getQuestionById(QuestionId); 
	   }

	   @RequestMapping(value="/{questiongroupid}/questions",method=RequestMethod.GET)
	   @APIMapping(value="SURVEY_API_GET_ALL_QUESTION",checkTrustedApp=true,checkSessionToken=true)
	   public Questionsv2 getAllQuestions(@PathVariable( "questiongroupid" ) UUID questionGroupId,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null || maxItems > 30) maxItems =30;
	           serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
	        return serviceFactory.getQuestionServicev2().getAllQuestions(startIndex,maxItems); 
	   }

}
