package com.hserv.coordinatedentry.housinginventory.model;

import java.util.UUID;

public class EligibilityModel {
	
	private UUID eligibiityId;
	private String type;
	private String gender;
	private String servesSingles;
	private String servesHouseholdWithChildren;;
	private String minAge;
	private String maxAge;
	private String incomeRequirementsForYourProject;
	private String minimumIncome;
	private String miximumIncome;

	private String zipCode;
	private String disability;
	private String alcoholAbuse;
	private String bothAlcoholAndDrugAbuse;
	private String chronicHealthCondition;
	private String developmental; 
	private String drugAbuse;
	private String mentalHealthProblem;
	private String chronicallyHomeless;
	
	public UUID getEligibiityId() {
		return eligibiityId;
	}
	public void setEligibiityId(UUID eligibiityId) {
		this.eligibiityId = eligibiityId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getServesSingles() {
		return servesSingles;
	}
	public void setServesSingles(String servesSingles) {
		this.servesSingles = servesSingles;
	}
	public String getServesHouseholdWithChildren() {
		return servesHouseholdWithChildren;
	}
	public void setServesHouseholdWithChildren(String servesHouseholdWithChildren) {
		this.servesHouseholdWithChildren = servesHouseholdWithChildren;
	}
	public String getMinAge() {
		return minAge;
	}
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	public String getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
	public String getIncomeRequirementsForYourProject() {
		return incomeRequirementsForYourProject;
	}
	public void setIncomeRequirementsForYourProject(String incomeRequirementsForYourProject) {
		this.incomeRequirementsForYourProject = incomeRequirementsForYourProject;
	}
	public String getMinimumIncome() {
		return minimumIncome;
	}
	public void setMinimumIncome(String minimumIncome) {
		this.minimumIncome = minimumIncome;
	}
	public String getMiximumIncome() {
		return miximumIncome;
	}
	public void setMiximumIncome(String miximumIncome) {
		this.miximumIncome = miximumIncome;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getDisability() {
		return disability;
	}
	public void setDisability(String disability) {
		this.disability = disability;
	}
	public String getAlcoholAbuse() {
		return alcoholAbuse;
	}
	public void setAlcoholAbuse(String alcoholAbuse) {
		this.alcoholAbuse = alcoholAbuse;
	}
	public String getBothAlcoholAndDrugAbuse() {
		return bothAlcoholAndDrugAbuse;
	}
	public void setBothAlcoholAndDrugAbuse(String bothAlcoholAndDrugAbuse) {
		this.bothAlcoholAndDrugAbuse = bothAlcoholAndDrugAbuse;
	}
	public String getChronicHealthCondition() {
		return chronicHealthCondition;
	}
	public void setChronicHealthCondition(String chronicHealthCondition) {
		this.chronicHealthCondition = chronicHealthCondition;
	}
	public String getDevelopmental() {
		return developmental;
	}
	public void setDevelopmental(String developmental) {
		this.developmental = developmental;
	}
	public String getDrugAbuse() {
		return drugAbuse;
	}
	public void setDrugAbuse(String drugAbuse) {
		this.drugAbuse = drugAbuse;
	}
	public String getMentalHealthProblem() {
		return mentalHealthProblem;
	}
	public void setMentalHealthProblem(String mentalHealthProblem) {
		this.mentalHealthProblem = mentalHealthProblem;
	}
	public String getChronicallyHomeless() {
		return chronicallyHomeless;
	}
	public void setChronicallyHomeless(String chronicallyHomeless) {
		this.chronicallyHomeless = chronicallyHomeless;
	}
}