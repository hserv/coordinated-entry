package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;

public class MatchAlgorithmModel {

	//private List<ProjectEligibilityInfoModel> projectEligibilityInfoModels;
	private List<CommunityPreferenceInfoModel> communityPreferenceModels;
	private List<HousingInventoryModel> housingInventoryModels;
	private List<EligibleClientModel> eligibleClientModels;

	/*public List<ProjectEligibilityInfoModel> getProjectEligibilityInfoModels() {
		return projectEligibilityInfoModels;
	}

	public void setProjectEliginilityInfoModels(List<ProjectEligibilityInfoModel> projectEligibilityInfoModels) {
		this.projectEligibilityInfoModels = projectEligibilityInfoModels;
	}*/

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

}
