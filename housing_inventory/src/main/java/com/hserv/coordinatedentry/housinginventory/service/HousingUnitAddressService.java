package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAddressRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingUnitAddressDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.HousingUnitAddressMapper;
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
 * Service Implementation for managing HousingUnitAddress.
 */
@Service
@Transactional
public class HousingUnitAddressService {

    private final Logger log = LoggerFactory.getLogger(HousingUnitAddressService.class);
    
    @Inject
    private HousingUnitAddressRepository housingUnitAddressRepository;
    
    @Inject
    private HousingUnitAddressMapper housingUnitAddressMapper;
    
    /**
     * Save a housingUnitAddress.
     * 
     * @param housingUnitAddressDTO the entity to save
     * @return the persisted entity
     */
    public HousingUnitAddressDTO save(HousingUnitAddressDTO housingUnitAddressDTO) {
        log.debug("Request to save HousingUnitAddress : {}", housingUnitAddressDTO);
        HousingUnitAddress housingUnitAddress = housingUnitAddressMapper.housingUnitAddressDTOToHousingUnitAddress(housingUnitAddressDTO);
        housingUnitAddress.setAddressId(UUID.randomUUID());
        housingUnitAddress = housingUnitAddressRepository.save(housingUnitAddress);
        HousingUnitAddressDTO result = housingUnitAddressMapper.housingUnitAddressToHousingUnitAddressDTO(housingUnitAddress);
        return result;
    }

    /**
     *  Get all the housingUnitAddresses.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<HousingUnitAddress> findAll(Pageable pageable) {
        log.debug("Request to get all HousingUnitAddresses");
        Page<HousingUnitAddress> result = housingUnitAddressRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one housingUnitAddress by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public HousingUnitAddressDTO findOne(UUID id) {
        log.debug("Request to get HousingUnitAddress : {}", id);
        HousingUnitAddress housingUnitAddress = housingUnitAddressRepository.findOne(id);
        HousingUnitAddressDTO housingUnitAddressDTO = housingUnitAddressMapper.housingUnitAddressToHousingUnitAddressDTO(housingUnitAddress);
        return housingUnitAddressDTO;
    }

    /**
     *  Delete the  housingUnitAddress by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete HousingUnitAddress : {}", id);
        housingUnitAddressRepository.delete(id);
    }
}
