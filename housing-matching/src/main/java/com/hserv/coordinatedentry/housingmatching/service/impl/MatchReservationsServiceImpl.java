package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.dao.MatchReservationsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.external.NotificationService;
import com.hserv.coordinatedentry.housingmatching.external.UserService;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;

@Service
public class MatchReservationsServiceImpl implements MatchReservationsService {

	@Autowired
	MatchReservationTranslator matchReservationTranslator;

	@Autowired
	MatchReservationsRepository repository;

	@Autowired
	NotificationService notificationService;
	
	@Autowired
	EligibleClientService eligibleClientService;
	
	@Autowired
	HousingUnitService housingUnitService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommunityServiceLocator communityServiceLocator;
	
	@Autowired
	EligibleClientsRepository eligibleClientsRepository;
	
	@Value("${TOP_N_CLIENTS}")
	private int numberOfClients;
	
	@Override
	public boolean createMatchReservation(MatchReservationModel matchReservationModel) {
		repository.saveAndFlush(matchReservationTranslator.translate(matchReservationModel));
		return true;
	}

	@Override
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels) {
		Set<Match> matchReservations = matchReservationTranslator
				.translates(new HashSet<MatchReservationModel>(matchReservationModels));
		for (Match matchReservation : matchReservations) {
			repository.saveAndFlush(matchReservation);
		}
		return true;
	}

	@Override
	public int updateMatchStatus(String matchStatus, String reservationId) {
		return repository.updateMatchStatus(matchStatus, UUID.fromString(reservationId));
	}

	@Override
	public int updateMatchStatusAndMAnualMatch(String matchStatus, boolean manualMatch, String reservationId) {
		return repository.updateMatchStatusAndManualMatch(matchStatus, manualMatch, UUID.fromString(reservationId));
	}

	@Override
	public boolean deleteAll() {
		repository.deleteAll();
		return true;
	}

	@Override
	public Page<Match> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public boolean deleteByClientId(UUID clientId) {
		if (repository.exists(clientId)) {
			repository.deleteByEligibleClientClientId(clientId);
			return true;
		}
		return false;
	}

	@Override
	public MatchReservationModel findByClientId(UUID clientId) {
		EligibleClient eligibleClient = eligibleClientsRepository.findOne(clientId);
		if (eligibleClient!=null) 
			return matchReservationTranslator
					.translate(repository.findByEligibleClient(eligibleClient).get(0));
		
		return null;
	}

	@Override
	public boolean updateByClientId(UUID clientId, MatchReservationModel matchReservationModel) {
	
			EligibleClient eligibleClient = eligibleClientsRepository.findOne(clientId);
			if(eligibleClient==null)
				return false;
			
		Match matchReservations = matchReservationTranslator.translate(matchReservationModel);
			matchReservations.setEligibleClient(eligibleClient);
		repository.saveAndFlush(matchReservations);

	//	sendNotification(matchReservationModel);
		return true;
	}

	private void sendNotification(MatchReservationModel matchReservationModel) {
		// if input value is approve/yes then only
		if ("approve".equalsIgnoreCase(matchReservationModel.getMatchStatus())
				|| "yes".equalsIgnoreCase(matchReservationModel.getMatchStatus())) {
			// call notification service to notify housing unit/user/client

			notificationService.notifyHousingUnit();
			notificationService.notifyUser();
			notificationService.notifyClient();
		}
	}

	@Override
	public void createMatch() {
		String[] programTypes = new String[]{"PSH", "TH","RRH"};
		
		List<EligibleClient> clients;
		List<HousingInventory>  units;
		List<Match> matches = new ArrayList<>();
		
		for(String programType : programTypes){
			//Get clients for each program type
			clients = eligibleClientService.getEligibleClients(numberOfClients , programType);
			
			//Get vacant housing units for programType
			units = housingUnitService.getHousingInventoryList("token", programType);
	        
			//Make sure clients are in desc order of priority
	        //Allocate house to first member in the list and then to second and so on.
			//Right now only criterion is zip code.
			//Run only for maximum number of units available
			//If only 3 units are avail, no point running for 10 clients
			for(int i = 0; i < units.size();i++){
				if(i==clients.size())
					break;
				EligibleClient c = clients.get(i);
	            int cc = c.getZipCode();
	            int temp = 0;
	            int count = 0;
	            int leastIndex = 0;
	            boolean first = true;
	            for(HousingInventory u : units){
	                if(!u.isVacant()){
	                    count++;
	                    continue;
	                }
	                int diff = Math.abs(u.getAddress().getZipCode()-cc);
	                if(diff==0){
	                	first = false;
	                    leastIndex = count;
	                    break;
	                }
	                if(first){
	                    first = false;
	                    temp = diff;
	                    leastIndex = count;
	                    count++;
	                    continue;
	                }
	                if(diff < temp){
	                    leastIndex = count;
	                }
	                count++;
	            }
            	Match match = new Match();
	            match.setEligibleClient(c);
                match.setHousingUnitId(UUID.fromString(units.get(leastIndex).getHousingUnitId()));
                match.setManualMatch(false);
                match.setMatchDate(new Date());
                match.setMatchStatus("Proposed");
                matches.add(match);
                units.get(leastIndex).setVacant(false);
	        }

	        clients.clear();
	        units.clear();
		}
		
		for(Match m :matches){
            repository.saveAndFlush(m);
            
            //store eligible client and set match flag to true
            EligibleClient eligibleClient = m.getEligibleClient();
            eligibleClient.setMatched(true);
            eligibleClientsRepository.saveAndFlush(eligibleClient);
        }
	}
}
