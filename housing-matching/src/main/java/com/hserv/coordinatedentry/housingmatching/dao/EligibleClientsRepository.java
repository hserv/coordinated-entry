package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;

@Repository
public interface EligibleClientsRepository extends JpaRepository<EligibleClients, Serializable> {
	
	public EligibleClients findByClientId(UUID clientID);

}
