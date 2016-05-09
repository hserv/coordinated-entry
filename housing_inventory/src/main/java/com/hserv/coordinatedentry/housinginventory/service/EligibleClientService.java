package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.EligibleClient;
import com.hserv.coordinatedentry.housinginventory.repository.EligibleClientRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.EligibleClientDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.EligibleClientMapper;
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
 * Service Implementation for managing EligibleClient.
 */
@Service
@Transactional
public class EligibleClientService {

    private final Logger log = LoggerFactory.getLogger(EligibleClientService.class);
    
    @Inject
    private EligibleClientRepository eligibleClientRepository;
    
    @Inject
    private EligibleClientMapper eligibleClientMapper;
    
    /**
     * Save a eligibleClient.
     * 
     * @param eligibleClientDTO the entity to save
     * @return the persisted entity
     */
    public EligibleClientDTO save(EligibleClientDTO eligibleClientDTO) {
        log.debug("Request to save EligibleClient : {}", eligibleClientDTO);
        EligibleClient eligibleClient = eligibleClientMapper.eligibleClientDTOToEligibleClient(eligibleClientDTO);
        eligibleClient.setClientId(UUID.randomUUID());
        eligibleClient = eligibleClientRepository.save(eligibleClient);
        EligibleClientDTO result = eligibleClientMapper.eligibleClientToEligibleClientDTO(eligibleClient);
        return result;
    }

    /**
     *  Get all the eligibleClients.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<EligibleClient> findAll(Pageable pageable) {
        log.debug("Request to get all EligibleClients");
        Page<EligibleClient> result = eligibleClientRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one eligibleClient by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public EligibleClientDTO findOne(UUID id) {
        log.debug("Request to get EligibleClient : {}", id);
        EligibleClient eligibleClient = eligibleClientRepository.findOne(id);
        EligibleClientDTO eligibleClientDTO = eligibleClientMapper.eligibleClientToEligibleClientDTO(eligibleClient);
        return eligibleClientDTO;
    }

    /**
     *  Delete the  eligibleClient by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete EligibleClient : {}", id);
        eligibleClientRepository.delete(id);
    }
}
