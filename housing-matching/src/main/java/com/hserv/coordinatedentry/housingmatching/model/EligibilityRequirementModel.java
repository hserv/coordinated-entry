package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class EligibilityRequirementModel  {
	
	private UUID eligibilityRequirementId;
	private UUID projectId;
	
	private String type;
	private Integer gender;
	private boolean servesSingles;
	private boolean servesHouseholdWithChildren;
	private Integer minAge;
	private Integer maxAge;
	private boolean incomeRequirement;
	private Long minimumIncome;
	private Long maximumIncome;
	private String zipCode;
	private Integer disability;
	private boolean alcoholAbuse;
	private boolean bothAlcoholAndDrugAbuse;
	private boolean chronicHealthCondition;
	private boolean developmental; 
	private boolean drugAbuse;
	private boolean mentalHealthProblem;
	private Integer chronicallyHomeless;
	private Integer race;
	private Integer ethnicity;
	private Integer veteranStatus;
	private Integer housingStatus;
	private Integer monthsHomelessPastThreeYears;
	private Integer residencePrior;
		
	public UUID getEligibilityRequirementId() {
		return eligibilityRequirementId;
	}

	public void setEligibilityRequirementId(UUID eligibilityRequirementId) {
		this.eligibilityRequirementId = eligibilityRequirementId;
	}

	public UUID getProjectId() {
		return projectId;
	}

	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public boolean isServesSingles() {
		return servesSingles;
	}

	public void setServesSingles(boolean servesSingles) {
		this.servesSingles = servesSingles;
	}

	public boolean isServesHouseholdWithChildren() {
		return servesHouseholdWithChildren;
	}

	public void setServesHouseholdWithChildren(boolean servesHouseholdWithChildren) {
		this.servesHouseholdWithChildren = servesHouseholdWithChildren;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public boolean isIncomeRequirement() {
		return incomeRequirement;
	}

	public void setIncomeRequirement(boolean incomeRequirement) {
		this.incomeRequirement = incomeRequirement;
	}

	public Long getMinimumIncome() {
		return minimumIncome;
	}

	public void setMinimumIncome(Long minimumIncome) {
		this.minimumIncome = minimumIncome;
	}

	public Long getMaximumIncome() {
		return maximumIncome;
	}

	public void setMaximumIncome(Long maximumIncome) {
		this.maximumIncome = maximumIncome;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getDisability() {
		return disability;
	}

	public void setDisability(Integer disability) {
		this.disability = disability;
	}

	public boolean isAlcoholAbuse() {
		return alcoholAbuse;
	}

	public void setAlcoholAbuse(boolean alcoholAbuse) {
		this.alcoholAbuse = alcoholAbuse;
	}

	public boolean isBothAlcoholAndDrugAbuse() {
		return bothAlcoholAndDrugAbuse;
	}

	public void setBothAlcoholAndDrugAbuse(boolean bothAlcoholAndDrugAbuse) {
		this.bothAlcoholAndDrugAbuse = bothAlcoholAndDrugAbuse;
	}

	public boolean isChronicHealthCondition() {
		return chronicHealthCondition;
	}

	public void setChronicHealthCondition(boolean chronicHealthCondition) {
		this.chronicHealthCondition = chronicHealthCondition;
	}

	public boolean isDevelopmental() {
		return developmental;
	}

	public void setDevelopmental(boolean developmental) {
		this.developmental = developmental;
	}

	public boolean isDrugAbuse() {
		return drugAbuse;
	}

	public void setDrugAbuse(boolean drugAbuse) {
		this.drugAbuse = drugAbuse;
	}

	public boolean isMentalHealthProblem() {
		return mentalHealthProblem;
	}

	public void setMentalHealthProblem(boolean mentalHealthProblem) {
		this.mentalHealthProblem = mentalHealthProblem;
	}

	public Integer getChronicallyHomeless() {
		return chronicallyHomeless;
	}

	public void setChronicallyHomeless(Integer chronicallyHomeless) {
		this.chronicallyHomeless = chronicallyHomeless;
	}

	public Integer getRace() {
		return race;
	}

	public void setRace(Integer race) {
		this.race = race;
	}

	public Integer getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Integer ethnicity) {
		this.ethnicity = ethnicity;
	}

	public Integer getVeteranStatus() {
		return veteranStatus;
	}

	public void setVeteranStatus(Integer veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	public Integer getHousingStatus() {
		return housingStatus;
	}

	public void setHousingStatus(Integer housingStatus) {
		this.housingStatus = housingStatus;
	}

	public Integer getMonthsHomelessPastThreeYears() {
		return monthsHomelessPastThreeYears;
	}

	public void setMonthsHomelessPastThreeYears(Integer monthsHomelessPastThreeYears) {
		this.monthsHomelessPastThreeYears = monthsHomelessPastThreeYears;
	}

	public Integer getResidencePrior() {
		return residencePrior;
	}

	public void setResidencePrior(Integer residencePrior) {
		this.residencePrior = residencePrior;
	}
	
	public int getAge(Date dateOfBirth) {
		Calendar dob = Calendar.getInstance();
		dob.setTime(dateOfBirth);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
		age--;
		return age;
	}

}