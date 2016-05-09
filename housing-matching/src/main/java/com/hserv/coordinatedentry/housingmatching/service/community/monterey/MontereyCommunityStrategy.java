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
				programType = "PSH";
			}else if(spdatScore >=4 && spdatScore <=7){
				programType = "TH";
			}else if(spdatScore <4){
				programType = "RRH";
			}
		}else if(family){
			if(spdatScore>9){
				programType = "PSH";
			}else if(spdatScore >=4 && spdatScore <=8){
				programType = "TH";
			}else if(spdatScore <4){
				programType = "RRH";
			}
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
}
