package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingInventoryDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.HousingInventoryMapper;
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
 * Service Implementation for managing HousingInventory.
 */
@Service
@Transactional
public class HousingInventoryService {

    private final Logger log = LoggerFactory.getLogger(HousingInventoryService.class);
    
    @Inject
    private HousingInventoryRepository housingInventoryRepository;
    
    @Inject
    private HousingInventoryMapper housingInventoryMapper;
    
    /**
     * Save a housingInventory.
     * 
     * @param housingInventoryDTO the entity to save
     * @return the persisted entity
     */
    public HousingInventoryDTO save(HousingInventoryDTO housingInventoryDTO) {
        log.debug("Request to save HousingInventory : {}", housingInventoryDTO);
        HousingInventory housingInventory = housingInventoryMapper.housingInventoryDTOToHousingInventory(housingInventoryDTO);
        housingInventory.setHousingUnitId(UUID.randomUUID());
        housingInventory = housingInventoryRepository.save(housingInventory);
        HousingInventoryDTO result = housingInventoryMapper.housingInventoryToHousingInventoryDTO(housingInventory);
        return result;
    }

    /**
     *  Get all the housingInventories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<HousingInventory> findAll(Pageable pageable) {
        log.debug("Request to get all HousingInventories");
        Page<HousingInventory> result = housingInventoryRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one housingInventory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public HousingInventoryDTO findOne(UUID id) {
        log.debug("Request to get HousingInventory : {}", id);
        HousingInventory housingInventory = housingInventoryRepository.findOne(id);
        HousingInventoryDTO housingInventoryDTO = housingInventoryMapper.housingInventoryToHousingInventoryDTO(housingInventory);
        return housingInventoryDTO;
    }

    /**
     *  Delete the  housingInventory by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete HousingInventory : {}", id);
        housingInventoryRepository.delete(id);
    }
}
