package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the HousingUnitAssignment entity.
 */
public interface HousingUnitAssignmentRepository extends JpaRepository<HousingUnitAssignment,UUID> {

}
