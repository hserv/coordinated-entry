package com.hserv.coordinatedentry.housingmatching.dao;

public interface RepositoryFactory {

	EligibilityRequirementRepository getEligibilityRequirementRepository();
	EligibleClientsRepository getEligibleClientsRepository();
	HousingUnitsRepository getHousingUnitsRepository();
	MatchReservationsRepository getMatchReservationsRepository();
	MatchStatusRepository getMatchStatusRepository();
	HouseholdMembershipRepository getHouseholdMembershipRepository();
	GlobalHouseholdRepository getGlobalHouseholdRepository();
	MatchStatuLevelsRepository getMatchStatuLevelsRepository();
	BatchProcessRepository getBatchProcessRepository();
	MatchStatusRemarksRepository getMatchStatusRemarksRepository();
	StatusNotesRepository getStatusNotesRepository();
}