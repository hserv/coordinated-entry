package com.hserv.coordinatedentry.housingmatching.service.community.monterey;

import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.service.MatchStrategy;

@Service
public class MontereyCommunityStrategy implements MatchStrategy{

	@Override
	public String getProgramType(int spdatScore,boolean individual,boolean family) {
		String programType = null;
		if(individual){
			if(spdatScore>8){
				programType = "10";
			}else if(spdatScore >=4 && spdatScore <=7){
				programType = "2";
			}else if(spdatScore <4){
				programType = "162";
			}
		}else if(family){
			if(spdatScore>9){
				programType = "10";
			}else if(spdatScore >=4 && spdatScore <=8){
				programType = "2";
			}else if(spdatScore <4){
				programType = "162";
			}
		}
		
		return programType;
	}
	
	public String getProgramType(int spdatScore,String spdatLabel){
		String programType = null;
		switch (spdatLabel) {
		case "SINGLE_AUDULT":
			if(spdatScore>8){
				programType = "10";
			}else if(spdatScore >=4 && spdatScore <=7){
				programType = "2";
			}else if(spdatScore <4){
				programType = "162";
			}
			break;
		case "FAMILY" :
			if(spdatScore>9){
				programType = "10";
			}else if(spdatScore >=4 && spdatScore <=8){
				programType = "2";
			}else if(spdatScore <4){
				programType = "162";
			}
		    break;
		default:
			programType=null;
			break;
		}
		return programType;
	}
	
	@Override
	public int getAdditionalScore(boolean individual, boolean family, int age, boolean familyWithChildren,
			boolean vet, boolean frailHealth) {
		int additionalScore = 0;
		
		if(individual){
			if(age>=18 && age<=24)
			additionalScore+=2;
		}
		
		if(familyWithChildren){
			additionalScore+=1;
		}
		
		if(vet){
			additionalScore+=2;
		}
		
		if(frailHealth){
			additionalScore+=3;
		}
		
		return additionalScore;
	}
	
	public int getAdditionalScore(int age,String spdatLabel){
		int additionalScore=0;
		
		switch (spdatLabel) {
		case "SINGLE_AUDULT":
			if(age>=18 && age<=24)
				additionalScore+=2;
			break;
		case "FAMILY" :
			additionalScore=0;
			break;
		default:
			additionalScore=0;
			break;
		}
		
		return additionalScore;
	}
}
