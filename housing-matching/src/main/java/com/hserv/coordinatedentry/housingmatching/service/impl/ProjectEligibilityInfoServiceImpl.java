package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.model.ProjectEligibilityInfoModel;
import com.hserv.coordinatedentry.housingmatching.service.ProjectEligibilityInfoService;

@Service
public class ProjectEligibilityInfoServiceImpl implements ProjectEligibilityInfoService{

	@Override
	public List<ProjectEligibilityInfoModel> getEligibleProjectsInfo() {
		// TODO Approach not yet final , actual service call to get eligible projects info
		return null;
	}

}
