package com.servinglynk.hmis.warehouse.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.Questionsv2;

@RestController
@RequestMapping("/questions")
public class QuestionsSearchController extends BaseController {

	@RequestMapping(method=RequestMethod.GET)
	@APIMapping(value="SURVEY_API_GET_ALL_QUESTION",checkTrustedApp=true,checkSessionToken=true)
	public Questionsv2 searchQuestions(
			@RequestParam(value="text",required=false) String displayText,
			@RequestParam(value="name",required=false) String description,
			@RequestParam(value = "startIndex", required = false) Integer startIndex,
			@RequestParam(value = "maxItems", required = false) Integer maxItems, HttpServletRequest request)
			throws Exception {
		if (startIndex == null)
			startIndex = 0;
		if (maxItems == null || maxItems > 30)
			maxItems = 30;

		return serviceFactory.getQuestionServicev2().filterQuestions(displayText, description, startIndex, maxItems);
	}
}
