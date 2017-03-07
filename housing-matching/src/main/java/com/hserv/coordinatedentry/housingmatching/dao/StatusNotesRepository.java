package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.StatusNotesEntity;

@Repository
public interface StatusNotesRepository extends JpaRepository<StatusNotesEntity, Serializable> {

	List<StatusNotesEntity> findByStatusIdAndDeletedOrderByDateCreatedDesc(UUID id, boolean b);
	
	Page<StatusNotesEntity> findByStatusIdAndDeletedOrderByDateCreatedDesc(UUID id, boolean deleted,Pageable pageable);

}
