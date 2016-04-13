package com.servinglynk.hmis.housinginventory.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.servinglynk.hmis.housinginventory.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
