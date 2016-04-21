package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;
import com.hserv.coordinatedentry.housinginventory.service.HousingUnitAssignmentService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingUnitAssignmentDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.HousingUnitAssignmentMapper;

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
 * REST controller for managing HousingUnitAssignment.
 */
@RestController
@RequestMapping("/api")
public class HousingUnitAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(HousingUnitAssignmentResource.class);
        
    @Inject
    private HousingUnitAssignmentService housingUnitAssignmentService;
    
    @Inject
    private HousingUnitAssignmentMapper housingUnitAssignmentMapper;
    
    /**
     * POST  /housing-unit-assignments : Create a new housingUnitAssignment.
     *
     * @param housingUnitAssignmentDTO the housingUnitAssignmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new housingUnitAssignmentDTO, or with status 400 (Bad Request) if the housingUnitAssignment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/housing-unit-assignments",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingUnitAssignmentDTO> createHousingUnitAssignment(@RequestBody HousingUnitAssignmentDTO housingUnitAssignmentDTO) throws URISyntaxException {
        log.debug("REST request to save HousingUnitAssignment : {}", housingUnitAssignmentDTO);
        if (housingUnitAssignmentDTO.getAssignmentId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("housingUnitAssignment", "idexists", "A new housingUnitAssignment cannot already have an ID")).body(null);
        }
        HousingUnitAssignmentDTO result = housingUnitAssignmentService.save(housingUnitAssignmentDTO);
        return ResponseEntity.created(new URI("/api/housing-unit-assignments/" + result.getAssignmentId()))
            .headers(HeaderUtil.createEntityCreationAlert("housingUnitAssignment", result.getAssignmentId().toString()))
            .body(result);
    }

    /**
     * PUT  /housing-unit-assignments : Updates an existing housingUnitAssignment.
     *
     * @param housingUnitAssignmentDTO the housingUnitAssignmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated housingUnitAssignmentDTO,
     * or with status 400 (Bad Request) if the housingUnitAssignmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the housingUnitAssignmentDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/housing-unit-assignments",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingUnitAssignmentDTO> updateHousingUnitAssignment(@RequestBody HousingUnitAssignmentDTO housingUnitAssignmentDTO) throws URISyntaxException {
        log.debug("REST request to update HousingUnitAssignment : {}", housingUnitAssignmentDTO);
        if (housingUnitAssignmentDTO.getAssignmentId() == null) {
            return createHousingUnitAssignment(housingUnitAssignmentDTO);
        }
        HousingUnitAssignmentDTO result = housingUnitAssignmentService.save(housingUnitAssignmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("housingUnitAssignment", housingUnitAssignmentDTO.getAssignmentId().toString()))
            .body(result);
    }

    /**
     * GET  /housing-unit-assignments : get all the housingUnitAssignments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of housingUnitAssignments in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/housing-unit-assignments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<HousingUnitAssignmentDTO>> getAllHousingUnitAssignments(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of HousingUnitAssignments");
        Page<HousingUnitAssignment> page = housingUnitAssignmentService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/housing-unit-assignments");
        return new ResponseEntity<>(housingUnitAssignmentMapper.housingUnitAssignmentsToHousingUnitAssignmentDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /housing-unit-assignments/:id : get the "id" housingUnitAssignment.
     *
     * @param id the id of the housingUnitAssignmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the housingUnitAssignmentDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/housing-unit-assignments/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HousingUnitAssignmentDTO> getHousingUnitAssignment(@PathVariable UUID id) {
        log.debug("REST request to get HousingUnitAssignment : {}", id);
        HousingUnitAssignmentDTO housingUnitAssignmentDTO = housingUnitAssignmentService.findOne(id);
        return Optional.ofNullable(housingUnitAssignmentDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /housing-unit-assignments/:id : delete the "id" housingUnitAssignment.
     *
     * @param id the id of the housingUnitAssignmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/housing-unit-assignments/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteHousingUnitAssignment(@PathVariable UUID id) {
        log.debug("REST request to delete HousingUnitAssignment : {}", id);
        housingUnitAssignmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("housingUnitAssignment", id.toString())).build();
    }

}
