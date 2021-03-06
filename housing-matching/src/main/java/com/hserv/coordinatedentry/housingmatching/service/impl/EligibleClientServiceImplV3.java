package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;
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
		List<EligibleClient> clients  =new ArrayList<EligibleClient>(); 
		 Long count =0L;
		if(filter.equalsIgnoreCase("inactive")) {
			clients = eligibleClientsRepository.getInactiveEligibleClients(projectGroupCode,pageable.getPageSize(),pageable.getOffset());
			count = eligibleClientsRepository.getInactiveEligibleClientsCount(projectGroupCode);
		}else if(filter.equalsIgnoreCase("active")) {
			clients = eligibleClientsRepository.getActiveEligibleClients(projectGroupCode,pageable.getPageSize(),pageable.getOffset());
			count = eligibleClientsRepository.getActiveEligibleClientsCount(projectGroupCode);
		}else {
			clients = eligibleClientsRepository.getActiveEligibleClients(projectGroupCode,pageable.getPageSize(),pageable.getOffset());
			count = eligibleClientsRepository.getAllEligibleClientsCount(projectGroupCode);
		}
		 

		return new PageImpl<>(clients,pageable,count);
	}
	
	

	public EligibleClientModel getEligibleClientDetail(UUID clientDedupId,String version) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		List<EligibleClient> eligibleClient = eligibleClientsRepository.getActiveEligibleClientByDedupId( projectGroup,clientDedupId.toString());
		if(eligibleClient.isEmpty()) throw new ResourceNotFoundException("Eligible not found "+clientDedupId);
		
		return eligibleClientsTranslator.translateV2(eligibleClient.get(0));
	}



	@Override
	public boolean deleteEligibleClientByDedupId(UUID dedupClientId) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		List<EligibleClient> eligibleClient = eligibleClientsRepository.getActiveEligibleClientByDedupId( projectGroup,dedupClientId.toString());
		if(eligibleClient.isEmpty()) throw new ResourceNotFoundException("Eligible not found "+dedupClientId);
		eligibleClientsRepository.delete(eligibleClient.get(0));
		return true;
	}

}
