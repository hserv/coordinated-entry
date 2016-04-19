package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.MatchReservations;
import com.hserv.coordinatedentry.housinginventory.service.MatchReservationsService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.MatchReservationsDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.MatchReservationsMapper;
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
 * REST controller for managing MatchReservations.
 */
@RestController
@RequestMapping("/api")
public class MatchReservationsResource {

    private final Logger log = LoggerFactory.getLogger(MatchReservationsResource.class);
        
    @Inject
    private MatchReservationsService matchReservationsService;
    
    @Inject
    private MatchReservationsMapper matchReservationsMapper;
    
    /**
     * POST  /match-reservations : Create a new matchReservations.
     *
     * @param matchReservationsDTO the matchReservationsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matchReservationsDTO, or with status 400 (Bad Request) if the matchReservations has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/match-reservations",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MatchReservationsDTO> createMatchReservations(@RequestBody MatchReservationsDTO matchReservationsDTO) throws URISyntaxException {
        log.debug("REST request to save MatchReservations : {}", matchReservationsDTO);
        if (matchReservationsDTO.getReservationId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("matchReservations", "idexists", "A new matchReservations cannot already have an ID")).body(null);
        }
        MatchReservationsDTO result = matchReservationsService.save(matchReservationsDTO);
        return ResponseEntity.created(new URI("/api/match-reservations/" + result.getReservationId()))
            .headers(HeaderUtil.createEntityCreationAlert("matchReservations", result.getReservationId().toString()))
            .body(result);
    }

    /**
     * PUT  /match-reservations : Updates an existing matchReservations.
     *
     * @param matchReservationsDTO the matchReservationsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matchReservationsDTO,
     * or with status 400 (Bad Request) if the matchReservationsDTO is not valid,
     * or with status 500 (Internal Server Error) if the matchReservationsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/match-reservations",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MatchReservationsDTO> updateMatchReservations(@RequestBody MatchReservationsDTO matchReservationsDTO) throws URISyntaxException {
        log.debug("REST request to update MatchReservations : {}", matchReservationsDTO);
        if (matchReservationsDTO.getReservationId() == null) {
            return createMatchReservations(matchReservationsDTO);
        }
        MatchReservationsDTO result = matchReservationsService.save(matchReservationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("matchReservations", matchReservationsDTO.getReservationId().toString()))
            .body(result);
    }

    /**
     * GET  /match-reservations : get all the matchReservations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of matchReservations in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/match-reservations",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<MatchReservationsDTO>> getAllMatchReservations(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MatchReservations");
        Page<MatchReservations> page = matchReservationsService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/match-reservations");
        return new ResponseEntity<>(matchReservationsMapper.matchReservationsToMatchReservationsDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /match-reservations/:id : get the "id" matchReservations.
     *
     * @param id the id of the matchReservationsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matchReservationsDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/match-reservations/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MatchReservationsDTO> getMatchReservations(@PathVariable UUID id) {
        log.debug("REST request to get MatchReservations : {}", id);
        MatchReservationsDTO matchReservationsDTO = matchReservationsService.findOne(id);
        return Optional.ofNullable(matchReservationsDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /match-reservations/:id : delete the "id" matchReservations.
     *
     * @param id the id of the matchReservationsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/match-reservations/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMatchReservations(@PathVariable UUID id) {
        log.debug("REST request to delete MatchReservations : {}", id);
        matchReservationsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("matchReservations", id.toString())).build();
    }

}
