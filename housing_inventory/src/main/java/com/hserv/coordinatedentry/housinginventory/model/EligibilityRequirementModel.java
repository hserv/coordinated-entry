package com.hserv.coordinatedentry.housinginventory.model;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.hserv.coordinatedentry.housinginventory.enums.DisabilitiesEnum;
import com.hserv.coordinatedentry.housinginventory.enums.EthnicityEnum;
import com.hserv.coordinatedentry.housinginventory.enums.GenderEnum;
import com.hserv.coordinatedentry.housinginventory.enums.HousingstatusEnum;
import com.hserv.coordinatedentry.housinginventory.enums.MonthshomelesspastthreeyearsEnum;
import com.hserv.coordinatedentry.housinginventory.enums.RaceEnum;
import com.hserv.coordinatedentry.housinginventory.enums.ResidencepriorEnum;
import com.hserv.coordinatedentry.housinginventory.enums.VeteranStatusEnum;

public class EligibilityRequirementModel {
	
	private UUID eligibilityRequirementId;
	private UUID projectId;
	
	private String type;
	private GenderEnum gender;
	private boolean servesSingles;
	private boolean servesHouseholdWithChildren;
	
	@Min(value=1,message="Minimum Age must be greater than 0")
	private Integer minAge;
	
	@Min(value=1,message="Maximum Age must be greater than 0")
	private Integer maxAge;
	private boolean incomeRequirement;
	
	@Min(value=0,message="Minimum Income must be greater than or equal to 0")
	private Long minimumIncome;
	
	@Min(value=0,message="Maximum Income must be greater than or equal to 0")
	private Long maximumIncome;

	@Length(min=5,max=5,message="Invalid zip code")
	private String zipCode;
	private VeteranStatusEnum disability;
	private boolean alcoholAbuse;
	private boolean bothAlcoholAndDrugAbuse;
	private boolean chronicHealthCondition;
	private boolean developmental; 
	private boolean drugAbuse;
	private boolean mentalHealthProblem;
	
	private VeteranStatusEnum chronicallyHomeless;
	
	private RaceEnum race;
	private EthnicityEnum ethnicity;
	private VeteranStatusEnum veteranStatus;
	private HousingstatusEnum housingStatus;
	private MonthshomelesspastthreeyearsEnum monthsHomelessPastThreeYears;
	private ResidencepriorEnum residencePrior;
	
	public UUID getEligibilityRequirementId() {
		return eligibilityRequirementId;
	}
	public void setEligibilityRequirementId(UUID eligibilityRequirementId) {
		this.eligibilityRequirementId = eligibilityRequirementId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
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
	public VeteranStatusEnum getDisability() {
		return disability;
	}
	public void setDisability(VeteranStatusEnum disability) {
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
	public VeteranStatusEnum getChronicallyHomeless() {
		return chronicallyHomeless;
	}
	public void setChronicallyHomeless(VeteranStatusEnum chronicallyHomeless) {
		this.chronicallyHomeless = chronicallyHomeless;
	}
	public RaceEnum getRace() {
		return race;
	}
	public void setRace(RaceEnum race) {
		this.race = race;
	}
	public EthnicityEnum getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(EthnicityEnum ethnicity) {
		this.ethnicity = ethnicity;
	}
	public VeteranStatusEnum getVeteranStatus() {
		return veteranStatus;
	}
	public void setVeteranStatus(VeteranStatusEnum veteranStatus) {
		this.veteranStatus = veteranStatus;
	}
	public HousingstatusEnum getHousingStatus() {
		return housingStatus;
	}
	public void setHousingStatus(HousingstatusEnum housingStatus) {
		this.housingStatus = housingStatus;
	}
	public MonthshomelesspastthreeyearsEnum getMonthsHomelessPastThreeYears() {
		return monthsHomelessPastThreeYears;
	}
	public void setMonthsHomelessPastThreeYears(MonthshomelesspastthreeyearsEnum monthsHomelessPastThreeYears) {
		this.monthsHomelessPastThreeYears = monthsHomelessPastThreeYears;
	}
	public ResidencepriorEnum getResidencePrior() {
		return residencePrior;
	}
	public void setResidencePrior(ResidencepriorEnum residencePrior) {
		this.residencePrior = residencePrior;
	}
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
}