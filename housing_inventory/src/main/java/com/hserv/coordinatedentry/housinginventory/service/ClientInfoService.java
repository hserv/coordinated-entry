package com.hserv.coordinatedentry.housinginventory.service;

import com.hserv.coordinatedentry.housinginventory.domain.ClientInfo;
import com.hserv.coordinatedentry.housinginventory.repository.ClientInfoRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.ClientInfoDTO;
import com.hserv.coordinatedentry.housinginventory.web.rest.mapper.ClientInfoMapper;
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
 * Service Implementation for managing ClientInfo.
 */
@Service
@Transactional
public class ClientInfoService {

    private final Logger log = LoggerFactory.getLogger(ClientInfoService.class);
    
    @Inject
    private ClientInfoRepository clientInfoRepository;
    
    @Inject
    private ClientInfoMapper clientInfoMapper;
    
    /**
     * Save a clientInfo.
     * 
     * @param clientInfoDTO the entity to save
     * @return the persisted entity
     */
    public ClientInfoDTO save(ClientInfoDTO clientInfoDTO) {
        log.debug("Request to save ClientInfo : {}", clientInfoDTO);
        ClientInfo clientInfo = clientInfoMapper.clientInfoDTOToClientInfo(clientInfoDTO);
        clientInfo.setId(UUID.randomUUID());
        clientInfo = clientInfoRepository.save(clientInfo);
        ClientInfoDTO result = clientInfoMapper.clientInfoToClientInfoDTO(clientInfo);
        return result;
    }

    /**
     *  Get all the clientInfos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ClientInfo> findAll(Pageable pageable) {
        log.debug("Request to get all ClientInfos");
        Page<ClientInfo> result = clientInfoRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one clientInfo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ClientInfoDTO findOne(UUID id) {
        log.debug("Request to get ClientInfo : {}", id);
        ClientInfo clientInfo = clientInfoRepository.findOne(id);
        ClientInfoDTO clientInfoDTO = clientInfoMapper.clientInfoToClientInfoDTO(clientInfo);
        return clientInfoDTO;
    }

    /**
     *  Delete the  clientInfo by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(UUID id) {
        log.debug("Request to delete ClientInfo : {}", id);
        clientInfoRepository.delete(id);
    }
}
