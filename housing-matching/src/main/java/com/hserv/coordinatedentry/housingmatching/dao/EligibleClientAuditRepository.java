package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClientAudit;

@Repository
public interface EligibleClientAuditRepository
		extends JpaRepository<EligibleClientAudit, Serializable>, JpaSpecificationExecutor<EligibleClientAudit> {
	List<EligibleClientAudit> findByClientIdAndProjectGroupCodeOrderByDateUpdatedDesc(UUID clientId,String projectGroupCode);
}
