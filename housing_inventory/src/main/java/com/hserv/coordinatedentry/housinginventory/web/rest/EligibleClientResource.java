package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.EligibleClient;
import com.hserv.coordinatedentry.housinginventory.service.EligibleClientService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.EligibleClientDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.EligibleClientMapper;
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
 * REST controller for managing EligibleClient.
 */
@RestController
@RequestMapping("/api")
public class EligibleClientResource {

    private final Logger log = LoggerFactory.getLogger(EligibleClientResource.class);
        
    @Inject
    private EligibleClientService eligibleClientService;
    
    @Inject
    private EligibleClientMapper eligibleClientMapper;
    
    /**
     * POST  /eligible-clients : Create a new eligibleClient.
     *
     * @param eligibleClientDTO the eligibleClientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eligibleClientDTO, or with status 400 (Bad Request) if the eligibleClient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/eligible-clients",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EligibleClientDTO> createEligibleClient(@RequestBody EligibleClientDTO eligibleClientDTO) throws URISyntaxException {
        log.debug("REST request to save EligibleClient : {}", eligibleClientDTO);
        if (eligibleClientDTO.getClientId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("eligibleClient", "idexists", "A new eligibleClient cannot already have an ID")).body(null);
        }
        EligibleClientDTO result = eligibleClientService.save(eligibleClientDTO);
        return ResponseEntity.created(new URI("/api/eligible-clients/" + result.getClientId()))
            .headers(HeaderUtil.createEntityCreationAlert("eligibleClient", result.getClientId().toString()))
            .body(result);
    }

    /**
     * PUT  /eligible-clients : Updates an existing eligibleClient.
     *
     * @param eligibleClientDTO the eligibleClientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eligibleClientDTO,
     * or with status 400 (Bad Request) if the eligibleClientDTO is not valid,
     * or with status 500 (Internal Server Error) if the eligibleClientDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/eligible-clients",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EligibleClientDTO> updateEligibleClient(@RequestBody EligibleClientDTO eligibleClientDTO) throws URISyntaxException {
        log.debug("REST request to update EligibleClient : {}", eligibleClientDTO);
        if (eligibleClientDTO.getClientId() == null) {
            return createEligibleClient(eligibleClientDTO);
        }
        EligibleClientDTO result = eligibleClientService.save(eligibleClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("eligibleClient", eligibleClientDTO.getClientId().toString()))
            .body(result);
    }

    /**
     * GET  /eligible-clients : get all the eligibleClients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eligibleClients in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/eligible-clients",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<EligibleClientDTO>> getAllEligibleClients(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of EligibleClients");
        Page<EligibleClient> page = eligibleClientService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/eligible-clients");
        return new ResponseEntity<>(eligibleClientMapper.eligibleClientsToEligibleClientDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /eligible-clients/:id : get the "id" eligibleClient.
     *
     * @param id the id of the eligibleClientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eligibleClientDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/eligible-clients/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EligibleClientDTO> getEligibleClient(@PathVariable UUID id) {
        log.debug("REST request to get EligibleClient : {}", id);
        EligibleClientDTO eligibleClientDTO = eligibleClientService.findOne(id);
        return Optional.ofNullable(eligibleClientDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /eligible-clients/:id : delete the "id" eligibleClient.
     *
     * @param id the id of the eligibleClientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/eligible-clients/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEligibleClient(@PathVariable UUID id) {
        log.debug("REST request to delete EligibleClient : {}", id);
        eligibleClientService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("eligibleClient", id.toString())).build();
    }

}
