package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;

@Repository
public interface EligibleClientsRepository extends JpaRepository<EligibleClients, Serializable> {
	
	public EligibleClients findByClientId(UUID clientID);

	@Query("select ec from EligibleClients as ec where ec.matched = false")
	public List<EligibleClients> findTopEligibleClients(Pageable pageable);

}
