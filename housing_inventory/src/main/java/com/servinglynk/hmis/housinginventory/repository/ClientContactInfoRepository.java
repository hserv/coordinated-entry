package com.servinglynk.hmis.housinginventory.repository;

import org.springframework.data.jpa.repository.*;

import com.servinglynk.hmis.housinginventory.domain.ClientContactInfo;

import java.util.List;

/**
 * Spring Data JPA repository for the ClientContactInfo entity.
 */
public interface ClientContactInfoRepository extends JpaRepository<ClientContactInfo,Long> {

}
