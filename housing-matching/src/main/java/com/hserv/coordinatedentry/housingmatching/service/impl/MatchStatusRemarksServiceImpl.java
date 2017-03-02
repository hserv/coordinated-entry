package com.hserv.coordinatedentry.housingmatching.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.MatchStatusRemarksEntity;
import com.hserv.coordinatedentry.housingmatching.service.MatchStatusRemarksService;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;

@Service
public class MatchStatusRemarksServiceImpl implements MatchStatusRemarksService {

	@Autowired
	RepositoryFactory repositoryFactory;

	public Page<MatchStatusRemarksEntity> getRemarks(Long statusCode, Pageable pageable) throws Exception {
		return repositoryFactory.getMatchStatusRemarksRepository().findByStatusCodeAndProjectGroupCodeAndDeleted(
				statusCode, SecurityContextUtil.getUserProjectGroup(), false, pageable);
	}
}
