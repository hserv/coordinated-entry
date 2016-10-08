package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.housingmatching.entity.MatchStatusLevels;

public interface MatchStatuLevelsRepository extends JpaRepository<MatchStatusLevels, Serializable>{

	List<MatchStatusLevels> findByProjectGroupCodeAndStatusCode(String projectGroupCode,String currentStatus);
}
