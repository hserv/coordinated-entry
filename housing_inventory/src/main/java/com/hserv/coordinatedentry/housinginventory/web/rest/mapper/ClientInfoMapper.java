package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.ClientInfoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ClientInfo and its DTO ClientInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClientInfoMapper {

    ClientInfoDTO clientInfoToClientInfoDTO(ClientInfo clientInfo);

    List<ClientInfoDTO> clientInfosToClientInfoDTOs(List<ClientInfo> clientInfos);

    ClientInfo clientInfoDTOToClientInfo(ClientInfoDTO clientInfoDTO);

    List<ClientInfo> clientInfoDTOsToClientInfos(List<ClientInfoDTO> clientInfoDTOs);
}
