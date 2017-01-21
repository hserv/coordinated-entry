package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibilityRequirement;
import com.hserv.coordinatedentry.housingmatching.entity.HouseholdMembership;
import com.hserv.coordinatedentry.housingmatching.model.ClientDEModel;
import com.hserv.coordinatedentry.housingmatching.model.Requirement;
import com.hserv.coordinatedentry.housingmatching.util.MatchProcessLogger;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

@Component
public class EligibilityValidator {
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	@Autowired
	MatchProcessLogger logger;
	

	@Value(value = "${apply_project_eligibility}")
	boolean applyProjectEligibility;

	public boolean validateProjectEligibility(ClientDEModel client,UUID projectId, UUID housingUnitId) {
			
			if(!applyProjectEligibility)
				return true;
			
			List<EligibilityRequirement> eligibilityRequirementModels = repositoryFactory.getEligibilityRequirementRepository().findByProjectIdAndDeleted(projectId,false);
			EvaluationContext context = new StandardEvaluationContext(client);
			ExpressionParser parser = new SpelExpressionParser();
			ObjectMapper mapper = new ObjectMapper();
			
			Set<Boolean> result = new HashSet<Boolean>();
			logger.log("match.process.project.eligibility.validation", new Object[]{eligibilityRequirementModels.size()},true,housingUnitId, projectId, client.getClientId());
			for(EligibilityRequirement eligibilityRequirement : eligibilityRequirementModels){
		
				List<Requirement> requirements= new ArrayList<Requirement>();
				try {
					JsonNode node =mapper.readTree(eligibilityRequirement.getEligibility());
					
					requirements = mapper.readValue(node.get("requirements").traverse(), TypeFactory.defaultInstance().constructCollectionType(List.class, Requirement.class));
				} catch (IOException e) {
					e.printStackTrace();
				}
					int step=1;
				for( Requirement requirement :  requirements){
						
						String req=generateExpression(requirement.getName(), requirement.getValue()+"");
						try{
							Expression expression = parser.parseExpression(req);
							boolean isValid = (boolean) expression.getValue(context);
//							match.process.project.eligibility.step={"step":"{}# VALIDATE - Project eligibility criteria","statusMessage":"{} - {} validated with criteria {} ","additionalInfo":{"eligibilityRequirmentId":"{}","dataElement":"{}","criteria":"{}","clientDEValue":"{}"}}
							logger.log("match.process.project.eligibility.step",new Object[]{ step,isValid? "SUCCESS" : "FAILED",requirement.getName(), requirement.getValue().toString().replaceAll("\"","&quot;"),eligibilityRequirement.getEligibilityId(),requirement.getName(),requirement.getValue().toString().replaceAll("\"","&quot;"),BeanUtils.getProperty(client, requirement.getName())}, isValid,housingUnitId,projectId,client.getClientId());
							if(!isValid) return false;
						}catch (Exception e) {
							logger.log("match.process.project.eligibility.step.invalid",new Object[]{ step,"SUCCESS",requirement.getName(), requirement.getValue().toString().replaceAll("\"","&quot;"),eligibilityRequirement.getEligibilityId(),requirement.getName(),requirement.getValue().toString().replaceAll("\"","&quot;"),""}, true,housingUnitId,projectId,client.getClientId());
						}
						step++;
 					}
			}
			return true;
	}
	
	public boolean validateProjectEligibility(Map<String, Object> client,UUID clientId,UUID projectId, UUID housingUnitId) {
		
		if(!applyProjectEligibility)
			return true;
		
		List<EligibilityRequirement> eligibilityRequirementModels = repositoryFactory.getEligibilityRequirementRepository().findByProjectIdAndDeleted(projectId,false);
		EvaluationContext context = new StandardEvaluationContext(client);
		ExpressionParser parser = new SpelExpressionParser();
		ObjectMapper mapper = new ObjectMapper();
		
		Set<Boolean> result = new HashSet<Boolean>();
		logger.log("match.process.project.eligibility.validation", new Object[]{eligibilityRequirementModels.size()},true,housingUnitId, projectId, clientId);
		for(EligibilityRequirement eligibilityRequirement : eligibilityRequirementModels){
	
			List<Requirement> requirements= new ArrayList<Requirement>();
			try {
				JsonNode node =mapper.readTree(eligibilityRequirement.getEligibility());
				
				requirements = mapper.readValue(node.get("requirements").traverse(), TypeFactory.defaultInstance().constructCollectionType(List.class, Requirement.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
				int step=1;
			for( Requirement requirement :  requirements){
					
					String req=generateExpression(requirement.getName(), requirement.getValue()+"");
					try{
						Expression expression = parser.parseExpression(req);
						if(client.get(requirement.getName().toLowerCase())!=null) {
							boolean isValid = (boolean) expression.getValue(context);
//							match.process.project.eligibility.step={"step":"{}# VALIDATE - Project eligibility criteria","statusMessage":"{} - {} validated with criteria {} ","additionalInfo":{"eligibilityRequirmentId":"{}","dataElement":"{}","criteria":"{}","clientDEValue":"{}"}}
							logger.log("match.process.project.eligibility.step",new Object[]{ step,isValid? "SUCCESS" : "FAILED",requirement.getName(), requirement.getValue().toString().replaceAll("\"","&quot;"),eligibilityRequirement.getEligibilityId(),requirement.getName(),requirement.getValue().toString().replaceAll("\"","&quot;"),client.get(requirement.getName().toLowerCase())}, isValid,housingUnitId,projectId,clientId);
							System.out.println("SPEL output "+isValid);
							if(!isValid) return false;
						}
						else{
							logger.log("match.process.project.eligibility.step.invalid",new Object[]{ step,"SUCCESS",requirement.getName(), requirement.getValue().toString().replaceAll("\"","&quot;"),eligibilityRequirement.getEligibilityId(),requirement.getName(),requirement.getValue().toString().replaceAll("\"","&quot;"),""}, true,housingUnitId,projectId,clientId);
						}
					}catch (Exception e) {
						System.out.println(" exception in SPEL"+e.getMessage());
						logger.log("match.process.project.eligibility.step.invalid",new Object[]{ step,"SUCCESS",requirement.getName(), requirement.getValue().toString().replaceAll("\"","&quot;"),eligibilityRequirement.getEligibilityId(),requirement.getName(),requirement.getValue().toString().replaceAll("\"","&quot;"),""}, true,housingUnitId,projectId,clientId);
					}
					step++;
					}
		}
		return true;
}
	
	public String generateExpression(String name,String value){
		 value = value.replaceAll("==","["+name+"] " +" eq ");
		 value = value.replaceAll("!=","["+name+"] " +" ne ");
		 value = value.replaceAll("=","["+name+"] " +" eq ");
		 value = value.replaceAll("<=","["+name+"] " +" le ");
		 value = value.replaceAll(">=","["+name+"] " +" ge ");
		 value = value.replaceAll(">","["+name+"] " +" gt ");
		 value = value.replaceAll("<","["+name+"] " +" lt ");		 
		 value = "( " + value +" ) ? true : false";
		 
		 System.out.println("SPEL expression "+value.toLowerCase());
		return value.toLowerCase();
	}
	
	
	public boolean validateCommunityPreferences(BaseClient client){
		return true;
	}
	
	public Integer validateBedsAvailability(UUID clientId,Integer bedsCount, UUID housingUnitId, UUID projectId){
		Integer bedsRequired = 0;
		Integer members =0;
		List<HouseholdMembership> membership =  repositoryFactory.getHouseholdMembershipRepository().findByGlobalClientIdAndDeleted(clientId,false);
			
		if(!membership.isEmpty()){
			 members = repositoryFactory.getHouseholdMembershipRepository().countByGlobalHouseholdAndDeleted(membership.get(0).getGlobalHousehold(),false);		
				logger.log("match.process.household.memberRegistration",new Object[]{"SUCCESS","Client registered in global house hold"},true,housingUnitId,projectId,clientId);
		}else{
			bedsRequired = 0;
			logger.log("match.process.household.memberRegistration",new Object[]{"FAILED","Client not registered in global house hold"},false,housingUnitId,projectId,clientId);	
			return bedsRequired;
		}
		if(members == bedsCount ){
			bedsRequired = members;
		}
		if( (members-1) == bedsCount){
			bedsRequired = bedsCount;
		}
			logger.log("match.process.household.memberValidationWithBeds",new Object[]{bedsRequired>0 ? "SUCCESS" : "FAILED",members,members,bedsCount},bedsRequired==0 ? false : true ,housingUnitId,projectId,clientId);
		return bedsRequired;
	}
	
/*		public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("test", true);
		System.out.println(map.get("test1"));
		
		Requirement requirement = new Requirement();
		
		EvaluationContext context = new StandardEvaluationContext(map);
		ExpressionParser parser = new SpelExpressionParser();
		String req = "[ test  ]   eq    true";
		Expression expression = parser.parseExpression(req);
		boolean isValid = (boolean) expression.getValue(context);
		
		System.out.println(isValid); 

	}  */
}