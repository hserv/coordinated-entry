package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.service.HousingUnitAddressService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingUnitAddressDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.HousingUnitAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing HousingUnitAddress.
 */
@RestController
@RequestMapping("/api")
public class HousingUnitAddressResource {

    private final Logger log = LoggerFactory.getLogger(HousingUnitAddressResource.class);
        
    @Inject
    private HousingUnitAddressService housingUnitAddressService;
    
    @Inject
    private HousingUnitAddressMapper housingUnitAddressMapper;
    
    /**
     * POST  /housing-unit-addresses : Create a new housingUnitAddress.
     *
     * @param housingUnitAddressDTO the housingUnitAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new housingUnitAddressDTO, or with status 400 (Bad Request) if the housingUnitAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/housing-unit-addresses",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingUnitAddressDTO> createHousingUnitAddress(@RequestBody HousingUnitAddressDTO housingUnitAddressDTO) throws URISyntaxException {
        log.debug("REST request to save HousingUnitAddress : {}", housingUnitAddressDTO);
        if (housingUnitAddressDTO.getHousingInventoryId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("housingUnitAddress", "idexists", "A new housingUnitAddress cannot already have an ID")).body(null);
        }
        HousingUnitAddressDTO result = housingUnitAddressService.save(housingUnitAddressDTO);
        return ResponseEntity.created(new URI("/api/housing-unit-addresses/" + result.getHousingInventoryId()))
            .headers(HeaderUtil.createEntityCreationAlert("housingUnitAddress", result.getHousingInventoryId().toString()))
            .body(result);
    }

    /**
     * PUT  /housing-unit-addresses : Updates an existing housingUnitAddress.
     *
     * @param housingUnitAddressDTO the housingUnitAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated housingUnitAddressDTO,
     * or with status 400 (Bad Request) if the housingUnitAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the housingUnitAddressDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/housing-unit-addresses",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingUnitAddressDTO> updateHousingUnitAddress(@RequestBody HousingUnitAddressDTO housingUnitAddressDTO) throws URISyntaxException {
        log.debug("REST request to update HousingUnitAddress : {}", housingUnitAddressDTO);
        if (housingUnitAddressDTO.getHousingInventoryId() == null) {
            return createHousingUnitAddress(housingUnitAddressDTO);
        }
        HousingUnitAddressDTO result = housingUnitAddressService.save(housingUnitAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAddress", housingUnitAddressDTO.getHousingInventoryId().toString()))
            .body(result);
    }

    /**
     * GET  /housing-unit-addresses : get all the housingUnitAddresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of housingUnitAddresses in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/housing-unit-addresses",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<HousingUnitAddressDTO>> getAllHousingUnitAddresses(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of HousingUnitAddresses");
        Page<HousingUnitAddress> page = housingUnitAddressService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/housing-unit-addresses");
        return new ResponseEntity<>(housingUnitAddressMapper.housingUnitAddressesToHousingUnitAddressDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /housing-unit-addresses/:id : get the "id" housingUnitAddress.
     *
     * @param id the id of the housingUnitAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the housingUnitAddressDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/housing-unit-addresses/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingUnitAddressDTO> getHousingUnitAddress(@PathVariable UUID id) {
        log.debug("REST request to get HousingUnitAddress : {}", id);
        HousingUnitAddressDTO housingUnitAddressDTO = housingUnitAddressService.findOne(id);
        return Optional.ofNullable(housingUnitAddressDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /housing-unit-addresses/:id : delete the "id" housingUnitAddress.
     *
     * @param id the id of the housingUnitAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/housing-unit-addresses/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteHousingUnitAddress(@PathVariable UUID id) {
        log.debug("REST request to delete HousingUnitAddress : {}", id);
        housingUnitAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingUnitAddress", id.toString())).build();
    }

}
