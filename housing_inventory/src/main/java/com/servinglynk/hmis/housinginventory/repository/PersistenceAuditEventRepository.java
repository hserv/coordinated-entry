package com.servinglynk.hmis.housinginventory.repository;


import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.servinglynk.hmis.housinginventory.domain.PersistentAuditEvent;

import java.util.List;

/**
 * Spring Data JPA repository for the PersistentAuditEvent entity.
 */
public interface PersistenceAuditEventRepository extends JpaRepository<PersistentAuditEvent, Long> {

    List<PersistentAuditEvent> findByPrincipal(String principal);

    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal, LocalDateTime after);

    List<PersistentAuditEvent> findAllByAuditEventDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
