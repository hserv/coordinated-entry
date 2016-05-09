package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.MatchReservations;
import com.hserv.coordinatedentry.housinginventory.repository.MatchReservationsRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.MatchReservationsDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.MatchReservationsMapper;
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
 * Service Implementation for managing MatchReservations.
 */
@Service
@Transactional
public class MatchReservationsService {

    private final Logger log = LoggerFactory.getLogger(MatchReservationsService.class);
    
    @Inject
    private MatchReservationsRepository matchReservationsRepository;
    
    @Inject
    private MatchReservationsMapper matchReservationsMapper;
    
    /**
     * Save a matchReservations.
     * 
     * @param matchReservationsDTO the entity to save
     * @return the persisted entity
     */
    public MatchReservationsDTO save(MatchReservationsDTO matchReservationsDTO) {
        log.debug("Request to save MatchReservations : {}", matchReservationsDTO);
        MatchReservations matchReservations = matchReservationsMapper.matchReservationsDTOToMatchReservations(matchReservationsDTO);
        matchReservations.setReservationId(UUID.randomUUID());
        matchReservations = matchReservationsRepository.save(matchReservations);
        MatchReservationsDTO result = matchReservationsMapper.matchReservationsToMatchReservationsDTO(matchReservations);
        return result;
    }

    /**
     *  Get all the matchReservations.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<MatchReservations> findAll(Pageable pageable) {
        log.debug("Request to get all MatchReservations");
        Page<MatchReservations> result = matchReservationsRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one matchReservations by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MatchReservationsDTO findOne(UUID id) {
        log.debug("Request to get MatchReservations : {}", id);
        MatchReservations matchReservations = matchReservationsRepository.findOne(id);
        MatchReservationsDTO matchReservationsDTO = matchReservationsMapper.matchReservationsToMatchReservationsDTO(matchReservations);
        return matchReservationsDTO;
    }

    /**
     *  Delete the  matchReservations by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete MatchReservations : {}", id);
        matchReservationsRepository.delete(id);
    }
}
