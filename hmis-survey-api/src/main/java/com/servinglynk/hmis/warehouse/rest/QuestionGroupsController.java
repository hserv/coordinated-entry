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
import com.servinglynk.hmis.warehouse.core.model.Question;
import com.servinglynk.hmis.warehouse.core.model.QuestionGroup;
import com.servinglynk.hmis.warehouse.core.model.QuestionGroups;
import com.servinglynk.hmis.warehouse.core.model.Questions;
import com.servinglynk.hmis.warehouse.core.model.Session;

@RestController
@RequestMapping("/questiongroups")
public class QuestionGroupsController extends BaseController { 

   @RequestMapping(method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_QUESTIONGROUP",checkTrustedApp=true,checkSessionToken=true)
   public QuestionGroup createQuestionGroup(@Valid @RequestBody QuestionGroup QuestionGroup,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
         serviceFactory.getQuestionGroupService().createQuestionGroup(QuestionGroup,session.getAccount().getUsername()); 
         QuestionGroup returnQuestionGroup = new QuestionGroup();
         returnQuestionGroup.setQuestionGroupId(QuestionGroup.getQuestionGroupId());
         return returnQuestionGroup;
   }

   @RequestMapping(value="/{questiongroupid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_QUESTIONGROUP",checkTrustedApp=true,checkSessionToken=true)
   public void updateQuestionGroup(@PathVariable( "questiongroupid" ) UUID questionGroupId,@Valid @RequestBody QuestionGroup QuestionGroup,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getQuestionGroupService().updateQuestionGroup(QuestionGroup,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{questiongroupid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_QUESTIONGROUP",checkTrustedApp=true,checkSessionToken=true)
   public void deleteQuestionGroup(@PathVariable( "questiongroupid" ) UUID questionGroupId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getQuestionGroupService().deleteQuestionGroup(questionGroupId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{questiongroupid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_QUESTIONGROUP_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public QuestionGroup getQuestionGroupById(@PathVariable( "questiongroupid" ) UUID questionGroupId,HttpServletRequest request) throws Exception{
        return serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId); 
   }

   @RequestMapping(method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_QUESTIONGROUP",checkTrustedApp=true,checkSessionToken=true)
   public QuestionGroups getAllQuestionGroups(
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
 
        return serviceFactory.getQuestionGroupService().getAllQuestionGroups(startIndex,maxItems); 
   }
   
   
   @RequestMapping(value="/{questiongroupid}/questions",method=RequestMethod.POST)
   @APIMapping(value="SURVEY_API_CREATE_QUESTION",checkTrustedApp=true,checkSessionToken=true)
   public Question createQuestion(@PathVariable( "questiongroupid" ) UUID questionGroupId,@Valid @RequestBody Question question,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        question.setQuestionGroupId(questionGroupId);
         serviceFactory.getQuestionService().createQuestion(question,session.getAccount().getUsername()); 
         Question returnQuestion = new Question();
         returnQuestion.setQuestionId(question.getQuestionId());
         return returnQuestion;
   }

   @RequestMapping(value="/{questiongroupid}/questions/{questionid}",method=RequestMethod.PUT)
   @APIMapping(value="SURVEY_API_UPDATE_QUESTION",checkTrustedApp=true,checkSessionToken=true)
   public void updateQuestion(@PathVariable( "questiongroupid" ) UUID questionGroupId,
		   @PathVariable( "questionid" ) UUID questionId,@Valid @RequestBody Question question,HttpServletRequest request) throws Exception{
        Session session = sessionHelper.getSession(request); 
        question.setQuestionId(questionId);
        question.setQuestionGroupId(questionGroupId);
        serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
        serviceFactory.getQuestionService().updateQuestion(question,session.getAccount().getUsername()); 
   }

   @RequestMapping(value="/{questiongroupid}/questions/{questionid}",method=RequestMethod.DELETE)
   @APIMapping(value="SURVEY_API_DELETE_QUESTION",checkTrustedApp=true,checkSessionToken=true)
   public void deleteQuestion(@PathVariable( "questiongroupid" ) UUID questionGroupId,
		   @PathVariable( "questionid" ) UUID questionId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Session session = sessionHelper.getSession(request); 
        serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
        serviceFactory.getQuestionService().deleteQuestion(questionId,session.getAccount().getUsername()); 
        response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
   }

   @RequestMapping(value="/{questiongroupid}/questions/{questionid}",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_QUESTION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
   public Question getQuestionById(@PathVariable( "questiongroupid" ) UUID questionGroupId,@PathVariable( "questionid" ) UUID QuestionId,HttpServletRequest request) throws Exception{
       serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
	   return serviceFactory.getQuestionService().getQuestionById(QuestionId); 
   }

   @RequestMapping(value="/{questiongroupid}/questions",method=RequestMethod.GET)
   @APIMapping(value="SURVEY_API_GET_ALL_QUESTION",checkTrustedApp=true,checkSessionToken=true)
   public Questions getAllQuestions(@PathVariable( "questiongroupid" ) UUID questionGroupId,
                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
                       @RequestParam(value="maxItems", required=false) Integer maxItems,
                       HttpServletRequest request) throws Exception {
           if (startIndex == null) startIndex =0;
           if (maxItems == null || maxItems > 30) maxItems =30;
           serviceFactory.getQuestionGroupService().getQuestionGroupById(questionGroupId);
        return serviceFactory.getQuestionService().getAllQuestions(questionGroupId,startIndex,maxItems); 
   }

}

