package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.ClientInfo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ClientInfo entity.
 */
public interface ClientInfoRepository extends JpaRepository<ClientInfo,Long> {

}
