package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;
import java.util.UUID;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.HouseholdMembership;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.util.DateUtil;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Parameter;
import com.servinglynk.hmis.warehouse.core.model.Parameters;

public class ClientDEModel {
	
	String momAndNumberOfKids ="( ?[ relationshipToHeadOfHousehold!=null && relationshipToHeadOfHousehold.equals('2')]).size() ";
	
	private Integer veteranStatus;
	private Integer gender;
	private Integer age;
	private UUID clientId;
	private Integer race;
	private boolean servesSingles;
	private Integer targetPopulation;
	private boolean servesHouseholdWithChildren;
	
	private EligibleClientService eligibleClientService;
	
	public Integer getVeteranStatus() {
		return veteranStatus;
	}
	public void setVeteranStatus(Integer veteranStatus) {
		this.veteranStatus = veteranStatus;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getRace() {
		return race;
	}
	public void setRace(Integer race) {
		this.race = race;
	}
	public boolean isServesSingles() {
		return servesSingles;
	}
	public void setServesSingles(boolean servesSingles) {
		this.servesSingles = servesSingles;
	}
		
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public Integer getTargetPopulation() {
		return targetPopulation;
	}
	public void setTargetPopulation(Integer targetPopulation) {
		this.targetPopulation = targetPopulation;
	}
	
	
	public EligibleClientService getEligibleClientService() {
		return eligibleClientService;
	}
	public void setEligibleClientService(EligibleClientService eligibleClientService) {
		this.eligibleClientService = eligibleClientService;
	}
	public boolean isServesHouseholdWithChildren() {
		return servesHouseholdWithChildren;
	}
	public void setServesHouseholdWithChildren(boolean servesHouseholdWithChildren) {
		this.servesHouseholdWithChildren = servesHouseholdWithChildren;
	}
	public void populateValues(BaseClient client,Parameters parameters){
		if(client.getVeteranStatus()!=null)
			parameters.addParameter(new Parameter("veteranStatus", Integer.parseInt(client.getVeteranStatus())));
	//		this.setVeteranStatus(Integer.parseInt(client.getVeteranStatus()));
		if(client.getGender()!=null)
			parameters.addParameter(new Parameter("gender",client.getGender()));
	//		this.setGender(client.getGender());
		if(client.getDob()!=null)
			parameters.addParameter(new Parameter("age", DateUtil.calculateAge(client.getDob())));
	//		this.setAge(DateUtil.calculateAge(client.getDob()));
		if(client.getRace()!=null)
			parameters.addParameter(new Parameter("race", client.getRace()));
	//		this.setRace(client.getRace());
	}
	
	public void populateValues(Project project,Parameters parameters){
		if(project.getTargetPopulation()!=null)
			this.setTargetPopulation(project.getTargetPopulation()); 
	}
	
	public void populateValues(EligibleClient client,Parameters parameters){
		this.setClientId(client.getClientId());
		if(client.getSpdatLabel()!=null) {
			if(client.getSpdatLabel().equalsIgnoreCase("TAY") || client.getSpdatLabel().equalsIgnoreCase("SINGLE_AUDLT")){
				parameters.addParameter(new Parameter("servesSingles", true));
	//			this.setServesSingles(true);
			}else{
				parameters.addParameter(new Parameter("servesSingles", false));
//				this.setServesSingles(false);
			}
		}
	}
	
	public void populateValues(List<HouseholdMembership> members,String trustedAppId,String sessionToken,Parameters parameters,BaseClient baseClient){
	
		Integer childCount =0 ;
		for(HouseholdMembership membership : members){
			BaseClient client =	eligibleClientService.getClientInfo(membership.getGlobalClientId(), trustedAppId, sessionToken);
				if(client!=null && client.getDob()!=null) 
					 if(DateUtil.calculateAge(client.getDob())  < 18){
						 	childCount++;
					 }
		}
		if(childCount>0){
			parameters.addParameter(new Parameter("servesHouseholdWithChildren", true));
		}
			
		if(baseClient.getGender()==0){
			if(childCount!=0 && ((members.size()-1) == childCount)){
				parameters.addParameter(new Parameter("momAndNumberOfKids",childCount));
			}else{
				parameters.addParameter(new Parameter("momAndNumberOfKids", null));
			}

		}else{
			parameters.addParameter(new Parameter("momAndNumberOfKids", null));
		}				
		
	}
	
	
	public Object evaluvateExpression( Object dataObject, String expr){
		try{
			EvaluationContext context = new StandardEvaluationContext(dataObject);
			ExpressionParser parser = new SpelExpressionParser();
	
			Expression expression = parser.parseExpression(expr);
			return expression.getValue(context);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}