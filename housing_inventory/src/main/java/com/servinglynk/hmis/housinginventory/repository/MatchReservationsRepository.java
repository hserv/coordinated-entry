package com.servinglynk.hmis.housinginventory.repository;


import org.springframework.data.jpa.repository.*;

import com.servinglynk.hmis.housinginventory.domain.MatchReservations;

import java.util.List;

/**
 * Spring Data JPA repository for the MatchReservations entity.
 */
public interface MatchReservationsRepository extends JpaRepository<MatchReservations,Long> {

}
