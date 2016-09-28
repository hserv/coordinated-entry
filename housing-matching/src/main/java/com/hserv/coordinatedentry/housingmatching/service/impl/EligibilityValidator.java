package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibilityRequirement;
import com.hserv.coordinatedentry.housingmatching.entity.HouseholdMembership;
import com.hserv.coordinatedentry.housingmatching.model.Requirement;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

@Component
public class EligibilityValidator {
	
	@Autowired
	RepositoryFactory repositoryFactory;

	public boolean validateProjectEligibility(BaseClient client,List<com.hserv.coordinatedentry.housingmatching.entity.EligibilityRequirement> eligibilityRequirementModels){
		
			boolean result=false;
			EvaluationContext context = new StandardEvaluationContext(client);
			ExpressionParser parser = new SpelExpressionParser();
			ObjectMapper mapper = new ObjectMapper();
			
			for(EligibilityRequirement eligibilityRequirement : eligibilityRequirementModels){
		
				List<Requirement> requirements= new ArrayList<Requirement>();
				try {
					requirements = mapper.readValue(eligibilityRequirement.getEligibility(), TypeFactory.defaultInstance().constructCollectionType(List.class, Requirement.class));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				for( Requirement requirement :  requirements){
						
						String req=generateExpression(requirement.getName(), requirement.getValue()+"");
						Expression expression = parser.parseExpression(req);
						result = (boolean) expression.getValue(context);
 					}
					System.out.println("  result "+result);
			}
			return result;
	}
	
	public String generateExpression(String name,String value){
		 value = value.replaceAll("==",name +" == ");
		 value = value.replaceAll("and", name+ " and ");
		 value = value.replaceAll("&&", name+ " and ");
		 value = value.replaceAll("&", name+ " and ");
		 value = value.replaceAll("or", name+ " or ");
		 value = value.replaceAll("||", name+ " or ");
		 value = value.replaceAll("eq",name +" eq ");
		 value = value.replaceAll("!=",name +" != ");
		 value = value.replaceAll("lt",name +" lt ");
		 value = value.replaceAll("<=",name +" le ");
		 value = value.replaceAll(">=",name +" ge ");
		 value = value.replaceAll(">",name +" gt ");
		 value = value.replaceAll("<",name +" lt ");
		 

		return value;
	}
	
	
	public boolean validateCommunityPreferences(BaseClient client){
		return true;
	}
	
	public Integer validateBedsAvailability(UUID clientId,Integer bedsCount){
		Integer bedsRequired = 0;
		HouseholdMembership membership =  repositoryFactory.getHouseholdMembershipRepository().findByGlobalClientId(clientId);
		Integer members = repositoryFactory.getHouseholdMembershipRepository().countByGlobalHousehold(membership.getGlobalHousehold());
		
		if(members == bedsCount ){
			bedsRequired = members;
			return bedsRequired;
		}
		if( (members-1) == bedsCount){
			bedsRequired = bedsCount;
			return bedsRequired;
		}
		return bedsRequired;
	}
	
	
/*	public static void main(String args[]){
		EligibilityValidator validator = new EligibilityValidator();
		String name="veteranStatus";
		String value=" eq \"Client does not know\" || != \"Client refused\"";
		
		List<EligibilityRequirementModel> models = new ArrayList<EligibilityRequirementModel>();
		EligibilityRequirementModel requirement = new EligibilityRequirementModel();
		requirement.addRequirement(name, value);
		requirement.addRequirement("gender"," >10 && <25");
		models.add(requirement);
		BaseClient client = new BaseClient();
		client.setVeteranStatus("yes");
		client.setGender(0);

		validator.validateClientEligibility(client, models);
		
	}*/
	
	
	public static void main(String ags[]){
		for(int i=0; i< 10 ; i++){
			for(int j=0;j<10; j++){
				System.out.println(" i " +i);
				if(j%2==0){
					System.out.println(" breaking inner loop " +j);
					break;

				}
				
			}
		}
	}
}