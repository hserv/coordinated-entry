package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the HousingUnitAssignment entity.
 */
public interface HousingUnitAssignmentRepository extends JpaRepository<HousingUnitAssignment,Long> {

}
