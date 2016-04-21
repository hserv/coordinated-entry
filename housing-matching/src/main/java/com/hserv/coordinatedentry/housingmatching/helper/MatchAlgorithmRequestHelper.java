package com.hserv.coordinatedentry.housingmatching.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.model.CommunityPreferenceInfoModel;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchAlgorithmModel;
import com.hserv.coordinatedentry.housingmatching.model.ProjectEligibilityInfoModel;
import com.hserv.coordinatedentry.housingmatching.service.CommunityPreferenceService;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.service.ProjectEligibilityInfoService;

@Component
public class MatchAlgorithmRequestHelper {

	@Autowired
	private ProjectEligibilityInfoService projectEligibilityInfoService;

	@Autowired
	private CommunityPreferenceService communityPreferenceService;

	@Autowired
	private HousingUnitService housingUnitService;

	@Autowired
	private EligibleClientService eligibleClientService;

	public MatchAlgorithmModel getMatchAlgorithmRequest(String userId) {

		MatchAlgorithmModel matchAlgoModel = new MatchAlgorithmModel();
		// 1. get project eligibility
		List<ProjectEligibilityInfoModel> eligibleProjectsInfo = projectEligibilityInfoService
				.getEligibleProjectsInfo();
		matchAlgoModel.setProjectEliginilityInfoModels(eligibleProjectsInfo);

		// 2. get community preferences
		List<CommunityPreferenceInfoModel> communityPreferenceInfo = communityPreferenceService.getCommunityPrerences();
		matchAlgoModel.setCommunityPreferenceModels(communityPreferenceInfo);

		// 3. get available housing units
		List<HousingInventoryModel> housingInventoryModels = housingUnitService.getHousingInventoryList(userId);
		matchAlgoModel.setHousingInventoryModels(housingInventoryModels);

		// 4. Get top n eligible clients
		// 5. criteria -> 1) ,2),3)
		int count = 10;
		List<EligibleClientModel> eligibleClientModels = eligibleClientService.getEligibleClients(10);
		matchAlgoModel.setEligibleClientModels(eligibleClientModels);
		return matchAlgoModel;
	}

}
