package com.hserv.coordinatedentry.housinginventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hserv.coordinatedentry.housinginventory.domain.ClientInfo;
import com.hserv.coordinatedentry.housinginventory.service.ClientInfoService;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.HeaderUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.PaginationUtil;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.ClientInfoDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.ClientInfoMapper;
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
 * REST controller for managing ClientInfo.
 */
@RestController
@RequestMapping("/api")
public class ClientInfoResource {

    private final Logger log = LoggerFactory.getLogger(ClientInfoResource.class);
        
    @Inject
    private ClientInfoService clientInfoService;
    
    @Inject
    private ClientInfoMapper clientInfoMapper;
    
    /**
     * POST  /client-infos : Create a new clientInfo.
     *
     * @param clientInfoDTO the clientInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientInfoDTO, or with status 400 (Bad Request) if the clientInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-infos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientInfoDTO> createClientInfo(@RequestBody ClientInfoDTO clientInfoDTO) throws URISyntaxException {
        log.debug("REST request to save ClientInfo : {}", clientInfoDTO);
        if (clientInfoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("clientInfo", "idexists", "A new clientInfo cannot already have an ID")).body(null);
        }
        ClientInfoDTO result = clientInfoService.save(clientInfoDTO);
        return ResponseEntity.created(new URI("/api/client-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("clientInfo", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /client-infos : Updates an existing clientInfo.
     *
     * @param clientInfoDTO the clientInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientInfoDTO,
     * or with status 400 (Bad Request) if the clientInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the clientInfoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-infos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientInfoDTO> updateClientInfo(@RequestBody ClientInfoDTO clientInfoDTO) throws URISyntaxException {
        log.debug("REST request to update ClientInfo : {}", clientInfoDTO);
        if (clientInfoDTO.getId() == null) {
            return createClientInfo(clientInfoDTO);
        }
        ClientInfoDTO result = clientInfoService.save(clientInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("clientInfo", clientInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /client-infos : get all the clientInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of clientInfos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/client-infos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<ClientInfoDTO>> getAllClientInfos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ClientInfos");
        Page<ClientInfo> page = clientInfoService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/client-infos");
        return new ResponseEntity<>(clientInfoMapper.clientInfosToClientInfoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /client-infos/:id : get the "id" clientInfo.
     *
     * @param id the id of the clientInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clientInfoDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/client-infos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientInfoDTO> getClientInfo(@PathVariable UUID id) {
        log.debug("REST request to get ClientInfo : {}", id);
        ClientInfoDTO clientInfoDTO = clientInfoService.findOne(id);
        return Optional.ofNullable(clientInfoDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /client-infos/:id : delete the "id" clientInfo.
     *
     * @param id the id of the clientInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/client-infos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteClientInfo(@PathVariable UUID id) {
        log.debug("REST request to delete ClientInfo : {}", id);
        clientInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("clientInfo", id.toString())).build();
    }

}
