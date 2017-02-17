package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hserv.coordinatedentry.housingmatching.entity.ResponseEntity;
import com.hserv.coordinatedentry.housingmatching.entity.SectionScoreEntity;
import com.hserv.coordinatedentry.housingmatching.external.SurveyMSService;
import com.hserv.coordinatedentry.housingmatching.model.ClientSurveyScore;
import com.hserv.coordinatedentry.housingmatching.model.ClientsSurveyScores;
import com.hserv.coordinatedentry.housingmatching.model.SurveyResponseModel;
import com.hserv.coordinatedentry.housingmatching.model.SurveySectionModel;
import com.hserv.coordinatedentry.housingmatching.util.RestClient;

@Service
public class SurveyMSServiceImpl implements SurveyMSService {

	@Value(value = "${SURVEY_MS_REST_SERVICE_URL}")
	private String SURVEY_MS_REST_SERVICE_URL;

	@Autowired
	private RestClient restClient;
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<SurveySectionModel> fetchSurveyResponse(String clientId) {
		

		// 3.Obtain token and call Survey MS.
		String url = new StringBuilder(SURVEY_MS_REST_SERVICE_URL).append(clientId).toString();
		List<SurveySectionModel> surveySectionList = new ArrayList<>();
		try{
			// 4.Return the useful data.
			SurveyResponseModel surveyResponseModel = (SurveyResponseModel)restClient.get(url, SurveyResponseModel.class);
			surveySectionList = surveyResponseModel.getSurveySectionList();
			
		}catch(Exception ex){
			
		}
		return surveySectionList;
	}
	
	@Resource(name="restTemplateWithMapper")
	RestTemplate restTemplate;

	
/*	@Override
	public ClientsSurveyScores fetchSurveyResponse(Session session) {		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-HMIS-TrustedApp-Id", "MASTER_TRUSTED_APP");
		headers.add("Authorization","HMISUserAuth session_token="+session.getToken());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		
		ResponseEntity<ClientsSurveyScores> responseEntity = restTemplate.exchange(SURVEY_MS_REST_SERVICE_URL, HttpMethod.GET,entity,ClientsSurveyScores.class);
		System.out.println("survey clients "+responseEntity.getBody().getClientsSurveyScores().size());
		return responseEntity.getBody();
	}*/
	
	public ClientsSurveyScores fetchSurveyResponses(String projectGroup){
		ClientsSurveyScores scores = new ClientsSurveyScores();
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);

		DetachedCriteria criteria = DetachedCriteria.forClass(SectionScoreEntity.class);
		criteria.createAlias("surveyEntity", "surveyEntity");
		
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.sum("sectionScore"),"surveyScore");
		projectionList.add(Projections.min("createdAt"),"surveyDate");
		projectionList.add(Projections.property("surveyEntity.id"),"surveyId");
		projectionList.add(Projections.property("clientId"),"clientId");
		projectionList.add(Projections.property("surveyEntity.projectGroupCode"),"projectGroupCode");
		projectionList.add(Projections.groupProperty("surveyEntity.id"));
		projectionList.add(Projections.groupProperty("clientId"));
		projectionList.add(Projections.groupProperty("surveyEntity.tagValue"),"surveyTagValue");
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("surveyEntity.projectGroupCode",projectGroup));
		criteria.add(Restrictions.eq("deleted", false));
		criteria.setResultTransformer(Transformers.aliasToBean(ClientSurveyScore.class));
		
		Criteria eCriteria = criteria.getExecutableCriteria(session);
		List<ClientSurveyScore> enities =  eCriteria.list();
		
		System.out.println("Suvey clients "+enities.size());
		scores.addAll(enities);		
		
		return scores;
	}
	
	public LocalDateTime getSurveyDate(UUID clientId, UUID surveyId) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(ResponseEntity.class);
		criteria.createAlias("surveyEntity", "surveyEntity");
		criteria.add(Restrictions.eq("clientId", clientId));
		criteria.add(Restrictions.eq("surveyEntity.id", surveyId));
		criteria.addOrder(Order.asc("createdAt"));
		Criteria eCriteria = criteria.getExecutableCriteria(session);
		List<ResponseEntity> entities = eCriteria.list();
		if(!entities.isEmpty()) return entities.get(0).getCreatedAt();
		return null;
	}

}
