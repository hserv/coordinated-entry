package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.ProjectEligibilityInfoModel;
public interface ProjectEligibilityInfoService {
	
	public List<ProjectEligibilityInfoModel> getEligibleProjectsInfo();

}
