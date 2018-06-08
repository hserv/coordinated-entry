package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsDaoV3;
import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientServiceV3;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;

@Service
public class EligibleClientServiceImplV3 implements EligibleClientServiceV3 {
	
	
	@Autowired
	EligibleClientsDaoV3 eligibleClientsDao;
	
	@Autowired
	EligibleClientsRepository eligibleClientsRepository;
	
	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;
	
	public Page<EligibleClient> getEligibleClients(String projectGroupCode, Pageable pageable, String filter) {
		
		Page<EligibleClient> clients  = new PageImpl<EligibleClient>(new ArrayList<EligibleClient>());
		if(filter.equalsIgnoreCase("inactive")) {
			clients =eligibleClientsRepository.findByProjectGroupCodeAndDeletedAndIgnoreMatchProcessOrderBySurveyDateDesc(projectGroupCode,false,true, pageable);
		}else if(filter.equalsIgnoreCase("active")) {
			clients =eligibleClientsRepository.findByProjectGroupCodeAndDeletedAndIgnoreMatchProcessOrderBySurveyDateDesc(projectGroupCode,false, false, pageable);
		}else {
			clients =eligibleClientsRepository.findByProjectGroupCodeAndDeletedOrderBySurveyDateDesc(projectGroupCode,false , pageable);			
		}

		return clients;
	}
	
	

	public EligibleClientModel getEligibleClientDetail(UUID clientDedupId,String version) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		EligibleClient eligibleClient = eligibleClientsDao.getEligibleClients(clientDedupId, projectGroup);
		if(eligibleClient==null) throw new ResourceNotFoundException("Eligible not found "+clientDedupId);
		
		return eligibleClientsTranslator.translateV2(eligibleClient);
	}

}
