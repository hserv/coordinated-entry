package com.servinglynk.hmis.housinginventory.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.servinglynk.hmis.housinginventory.domain.PersistentToken;
import com.servinglynk.hmis.housinginventory.domain.User;

import java.util.List;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(User user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
