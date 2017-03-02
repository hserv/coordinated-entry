package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housingmatching.entity.MatchStatusRemarksEntity;

public interface MatchStatusRemarksRepository extends JpaRepository<MatchStatusRemarksEntity, Serializable> {

	List<MatchStatusRemarksEntity> findByProjectGroupCodeAndDeleted(String projectGroupCode,boolean deleted);
	
	Page<MatchStatusRemarksEntity> findByStatusCodeAndProjectGroupCodeAndDeleted(Long statusCode,String projectGroupCode,boolean deleted,Pageable pageable);

}