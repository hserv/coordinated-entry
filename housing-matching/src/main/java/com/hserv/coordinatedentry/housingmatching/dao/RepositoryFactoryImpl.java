package com.hserv.coordinatedentry.housingmatching.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("repositoryFactory")
public class RepositoryFactoryImpl implements RepositoryFactory {

	
	@Autowired private EligibilityRequirementRepository eligibilityRequirementRepository;
	@Autowired private EligibleClientsRepository eligibleClientsRepository;
	@Autowired private HousingUnitsRepository housingUnitsRepository;
	@Autowired private MatchReservationsRepository matchReservationsRepository;
	@Autowired private MatchStatusRepository matchStatusRepository;
	@Autowired private GlobalHouseholdRepository globalHouseholdRepository;
	@Autowired private HouseholdMembershipRepository householdMembershipRepository;
	@Autowired private MatchStatuLevelsRepository matchStatuLevelsRepository;
	@Autowired private BatchProcessRepository batchProcessRepository;
	@Autowired private MatchStatusRemarksRepository matchStatusRemarksRepository;
	@Autowired private StatusNotesRepository statusNotesRepository;
	
	
	public EligibilityRequirementRepository getEligibilityRequirementRepository() {
		return eligibilityRequirementRepository;
	}
	public void setEligibilityRequirementRepository(EligibilityRequirementRepository eligibilityRequirementRepository) {
		this.eligibilityRequirementRepository = eligibilityRequirementRepository;
	}
	public EligibleClientsRepository getEligibleClientsRepository() {
		return eligibleClientsRepository;
	}
	public void setEligibleClientsRepository(EligibleClientsRepository eligibleClientsRepository) {
		this.eligibleClientsRepository = eligibleClientsRepository;
	}
	public HousingUnitsRepository getHousingUnitsRepository() {
		return housingUnitsRepository;
	}
	public void setHousingUnitsRepository(HousingUnitsRepository housingUnitsRepository) {
		this.housingUnitsRepository = housingUnitsRepository;
	}
	public MatchReservationsRepository getMatchReservationsRepository() {
		return matchReservationsRepository;
	}
	public void setMatchReservationsRepository(MatchReservationsRepository matchReservationsRepository) {
		this.matchReservationsRepository = matchReservationsRepository;
	}
	public MatchStatusRepository getMatchStatusRepository() {
		return matchStatusRepository;
	}
	public void setMatchStatusRepository(MatchStatusRepository matchStatusRepository) {
		this.matchStatusRepository = matchStatusRepository;
	}
	public GlobalHouseholdRepository getGlobalHouseholdRepository() {
		return globalHouseholdRepository;
	}
	public void setGlobalHouseholdRepository(GlobalHouseholdRepository globalHouseholdRepository) {
		this.globalHouseholdRepository = globalHouseholdRepository;
	}
	public HouseholdMembershipRepository getHouseholdMembershipRepository() {
		return householdMembershipRepository;
	}
	public void setHouseholdMembershipRepository(HouseholdMembershipRepository householdMembershipRepository) {
		this.householdMembershipRepository = householdMembershipRepository;
	}
	public MatchStatuLevelsRepository getMatchStatuLevelsRepository() {
		return matchStatuLevelsRepository;
	}
	public void setMatchStatuLevelsRepository(MatchStatuLevelsRepository matchStatuLevelsRepository) {
		this.matchStatuLevelsRepository = matchStatuLevelsRepository;
	}
	public BatchProcessRepository getBatchProcessRepository() {
		return batchProcessRepository;
	}
	public void setBatchProcessRepository(BatchProcessRepository batchProcessRepository) {
		this.batchProcessRepository = batchProcessRepository;
	}
	public MatchStatusRemarksRepository getMatchStatusRemarksRepository() {
		return matchStatusRemarksRepository;
	}
	public void setMatchStatusRemarksRepository(MatchStatusRemarksRepository matchStatusRemarksRepository) {
		this.matchStatusRemarksRepository = matchStatusRemarksRepository;
	}
	public StatusNotesRepository getStatusNotesRepository() {
		return statusNotesRepository;
	}
	public void setStatusNotesRepository(StatusNotesRepository statusNotesRepository) {
		this.statusNotesRepository = statusNotesRepository;
	}
}