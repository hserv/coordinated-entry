package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.service.HousingInventoryService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingInventoryDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.HousingInventoryMapper;
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
 * REST controller for managing HousingInventory.
 */
@RestController
@RequestMapping("/api")
public class HousingInventoryResource {

    private final Logger log = LoggerFactory.getLogger(HousingInventoryResource.class);
        
    @Inject
    private HousingInventoryService housingInventoryService;
    
    @Inject
    private HousingInventoryMapper housingInventoryMapper;
    
    /**
     * POST  /housing-inventories : Create a new housingInventory.
     *
     * @param housingInventoryDTO the housingInventoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new housingInventoryDTO, or with status 400 (Bad Request) if the housingInventory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/housing-inventories",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingInventoryDTO> createHousingInventory(@RequestBody HousingInventoryDTO housingInventoryDTO) throws URISyntaxException {
        log.debug("REST request to save HousingInventory : {}", housingInventoryDTO);
        if (housingInventoryDTO.getHousingUnitId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("housingInventory", "idexists", "A new housingInventory cannot already have an ID")).body(null);
        }
        HousingInventoryDTO result = housingInventoryService.save(housingInventoryDTO);
        return ResponseEntity.created(new URI("/api/housing-inventories/" + result.getHousingUnitId()))
            .headers(HeaderUtil.createEntityCreationAlert("housingInventory", result.getHousingUnitId().toString()))
            .body(result);
    }

    /**
     * PUT  /housing-inventories : Updates an existing housingInventory.
     *
     * @param housingInventoryDTO the housingInventoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated housingInventoryDTO,
     * or with status 400 (Bad Request) if the housingInventoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the housingInventoryDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/housing-inventories",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingInventoryDTO> updateHousingInventory(@RequestBody HousingInventoryDTO housingInventoryDTO) throws URISyntaxException {
        log.debug("REST request to update HousingInventory : {}", housingInventoryDTO);
        if (housingInventoryDTO.getHousingUnitId() == null) {
            return createHousingInventory(housingInventoryDTO);
        }
        HousingInventoryDTO result = housingInventoryService.save(housingInventoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("housingInventory", housingInventoryDTO.getHousingUnitId().toString()))
            .body(result);
    }

    /**
     * GET  /housing-inventories : get all the housingInventories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of housingInventories in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/housing-inventories",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<HousingInventoryDTO>> getAllHousingInventories(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of HousingInventories");
        Page<HousingInventory> page = housingInventoryService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/housing-inventories");
        return new ResponseEntity<>(housingInventoryMapper.housingInventoriesToHousingInventoryDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /housing-inventories/:id : get the "id" housingInventory.
     *
     * @param id the id of the housingInventoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the housingInventoryDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/housing-inventories/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingInventoryDTO> getHousingInventory(@PathVariable UUID id) {
        log.debug("REST request to get HousingInventory : {}", id);
        HousingInventoryDTO housingInventoryDTO = housingInventoryService.findOne(id);
        return Optional.ofNullable(housingInventoryDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /housing-inventories/:id : delete the "id" housingInventory.
     *
     * @param id the id of the housingInventoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/housing-inventories/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteHousingInventory(@PathVariable UUID id) {
        log.debug("REST request to delete HousingInventory : {}", id);
        housingInventoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingInventory", id.toString())).build();
    }

}
