package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.MatchReservations;

@Repository
public interface MatchReservationsRepository extends JpaRepository<MatchReservations, Serializable>{

}
