package com.hserv.coordinatedentry.housingmatching.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hserv.coordinatedentry.housingmatching.entity.MatchStatusRemarksEntity;

public interface MatchStatusRemarksService {
	Page<MatchStatusRemarksEntity> getRemarks(Long statusCode,Pageable pageable) throws Exception;
}