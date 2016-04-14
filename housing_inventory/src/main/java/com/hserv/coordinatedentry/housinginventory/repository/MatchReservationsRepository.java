package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.MatchReservations;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MatchReservations entity.
 */
public interface MatchReservationsRepository extends JpaRepository<MatchReservations,Long> {

}
