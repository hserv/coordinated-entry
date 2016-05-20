
package com.hserv.coordinatedentry.housingmatching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.hserv.coordinatedentry.housingmatching.interceptor.APIMapping;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;

@RestController
@ResponseBody
@RequestMapping(value = "/scores", produces = "application/json")
public class ScoreController {

	@Autowired
	SurveyScoreService surveyScoreService;
	
	/**
	 * Trigger score totaling via POST. An Empty body request would suffice.
	 * 
	 * 
	 */
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@APIMapping(value="trigger-score-calculation")
	public DeferredResult<String> calculateScore() {
		DeferredResult<String> deferredResult = new DeferredResult<>();
		try {
			surveyScoreService.calculateScore();
			deferredResult.setResult("triggered: 'ok'");
		} catch (Exception ex) {
			deferredResult.setResult(new String("{\"failed\": \"true\"}\""));
			ex.printStackTrace();
		}
		return deferredResult;
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	@APIMapping(value="create-score-by-clientId")
	public DeferredResult<String> insertScoreForAClient(@PathVariable String id) {
		DeferredResult<String> deferredResult = new DeferredResult<>();
		try {
			//
		} catch (Exception ex) {
			deferredResult.setResult(new String("{\"failed\": \"true\"}\""));
		}
		return deferredResult;
	}

	/**
	 * Get the list of clients and their survey scores.
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@APIMapping(value="get-scores")
	public List<EligibleClientModel> getScores() {
		return surveyScoreService.getScores();
	}

	/**
	 * Clear all the survey scores.
	 * 
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@APIMapping(value="delete-scores")
	public ResponseEntity<String> deleteScores() {
		ResponseEntity<String> responseEntity = null;
		try {
			surveyScoreService.deleteScores();
			responseEntity = ResponseEntity.ok("success");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Get the survey score for a client_id.
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
	@APIMapping(value="get-score-by-clientId")
	public ResponseEntity<String> getScoreByClientId(@PathVariable String id) {
		ResponseEntity<String> responseEntity = null;
		try {
			int score = surveyScoreService.getScoreByClientId(id);
			responseEntity = ResponseEntity.ok("score :" + String.valueOf(score));
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Clears off the survey score for a particular client.
	 * 
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
	@APIMapping(value="delete-score-by-clientId")
	public ResponseEntity<String> deleteScoreByClientId(@PathVariable String id) {
		ResponseEntity<String> responseEntity = null;
		try {
			surveyScoreService.deleteScoreByClientId(id);
			responseEntity = ResponseEntity.ok("Score Deleted");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

	/**
	 * Used for updating survey score for a client id.
	 * 
	 * 
	 */
	@RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
	@APIMapping(value="update-score-by-clientId")
	public ResponseEntity<String> updateScoreByClientId(@PathVariable String id, @RequestBody int score) {
		ResponseEntity<String> responseEntity = null;
		try {
			surveyScoreService.updateScoreByClientId(score, id);
			responseEntity = ResponseEntity.ok("Score Updated");
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<String>("Fail", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}

}
