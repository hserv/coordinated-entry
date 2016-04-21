package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAssignmentRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingUnitAssignmentDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.HousingUnitAssignmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing HousingUnitAssignment.
 */
@Service
@Transactional
public class HousingUnitAssignmentService {

    private final Logger log = LoggerFactory.getLogger(HousingUnitAssignmentService.class);
    
    @Inject
    private HousingUnitAssignmentRepository housingUnitAssignmentRepository;
    
    @Inject
    private HousingUnitAssignmentMapper housingUnitAssignmentMapper;
    
    /**
     * Save a housingUnitAssignment.
     * 
     * @param housingUnitAssignmentDTO the entity to save
     * @return the persisted entity
     */
    public HousingUnitAssignmentDTO save(HousingUnitAssignmentDTO housingUnitAssignmentDTO) {
        log.debug("Request to save HousingUnitAssignment : {}", housingUnitAssignmentDTO);
        HousingUnitAssignment housingUnitAssignment = housingUnitAssignmentMapper.housingUnitAssignmentDTOToHousingUnitAssignment(housingUnitAssignmentDTO);
        housingUnitAssignment.setAssignmentId(UUID.randomUUID());
        housingUnitAssignment = housingUnitAssignmentRepository.save(housingUnitAssignment);
        HousingUnitAssignmentDTO result = housingUnitAssignmentMapper.housingUnitAssignmentToHousingUnitAssignmentDTO(housingUnitAssignment);
        return result;
    }

    /**
     *  Get all the housingUnitAssignments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<HousingUnitAssignment> findAll(Pageable pageable) {
        log.debug("Request to get all HousingUnitAssignments");
        Page<HousingUnitAssignment> result = housingUnitAssignmentRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one housingUnitAssignment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public HousingUnitAssignmentDTO findOne(UUID id) {
        log.debug("Request to get HousingUnitAssignment : {}", id);
        HousingUnitAssignment housingUnitAssignment = housingUnitAssignmentRepository.findOne(id);
        HousingUnitAssignmentDTO housingUnitAssignmentDTO = housingUnitAssignmentMapper.housingUnitAssignmentToHousingUnitAssignmentDTO(housingUnitAssignment);
        return housingUnitAssignmentDTO;
    }

    /**
     *  Delete the  housingUnitAssignment by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete HousingUnitAssignment : {}", id);
        housingUnitAssignmentRepository.delete(id);
    }
}
