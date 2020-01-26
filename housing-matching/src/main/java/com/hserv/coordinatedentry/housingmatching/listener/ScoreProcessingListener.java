package com.hserv.coordinatedentry.housingmatching.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.service.SurveyScoreService;
import com.servinglynk.hmis.warehouse.model.AMQEvent;
import com.servinglynk.hmis.warehouse.model.JSONObjectMapper;

@Component
public class ScoreProcessingListener {
	
	@Autowired
	SurveyScoreService surveyScoreService;

	
	@JmsListener(destination="survey.scores.submissions")
	public void listeneQueue(String eventString) {

		System.out.println("inside client.delete listener");
		JSONObjectMapper mapper = new JSONObjectMapper();
		try {
			AMQEvent event = mapper.readValue(eventString, AMQEvent.class);
				surveyScoreService.processClientScore(event);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
}
