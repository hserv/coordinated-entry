package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Serializable> {
	
	List<Client> findByDedupClientIdAndProjectGroupCodeOrderBySchemaYearDesc(UUID dedupClientId,String projectGroupCode);
	
	Client findById(UUID clientId);

}
