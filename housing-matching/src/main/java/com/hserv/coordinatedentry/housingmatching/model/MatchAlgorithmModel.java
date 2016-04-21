package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;

public class MatchAlgorithmModel {

	private List<ProjectEligibilityInfoModel> projectEliginilityInfoModels;
	private List<CommunityPreferenceInfoModel> communityPreferenceModels;
	private List<HousingInventoryModel> housingInventoryModels;
	private List<EligibleClientModel> eligibleClientModels;
	private List<RuleModel> ruleModels;

	public List<ProjectEligibilityInfoModel> getProjectEliginilityInfoModels() {
		return projectEliginilityInfoModels;
	}

	public void setProjectEliginilityInfoModels(List<ProjectEligibilityInfoModel> projectEliginilityInfoModels) {
		this.projectEliginilityInfoModels = projectEliginilityInfoModels;
	}

	public List<CommunityPreferenceInfoModel> getCommunityPreferenceModels() {
		return communityPreferenceModels;
	}

	public void setCommunityPreferenceModels(List<CommunityPreferenceInfoModel> communityPreferenceModels) {
		this.communityPreferenceModels = communityPreferenceModels;
	}

	public List<HousingInventoryModel> getHousingInventoryModels() {
		return housingInventoryModels;
	}

	public void setHousingInventoryModels(List<HousingInventoryModel> housingInventoryModels) {
		this.housingInventoryModels = housingInventoryModels;
	}

	public List<EligibleClientModel> getEligibleClientModels() {
		return eligibleClientModels;
	}

	public void setEligibleClientModels(List<EligibleClientModel> eligibleClientModels) {
		this.eligibleClientModels = eligibleClientModels;
	}

	public List<RuleModel> getRuleModels() {
		return ruleModels;
	}

	public void setRuleModels(List<RuleModel> ruleModels) {
		this.ruleModels = ruleModels;
	}

}
