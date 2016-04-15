package com.hserv.coordinatedentry.housingmatching.translator;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;

@Component
public class EligibleClientsTranslator {

	public EligibleClientModel translate(EligibleClients eligibleClient) {
		EligibleClientModel eligibleClientModel= new EligibleClientModel();
		return eligibleClientModel;
	}

}
