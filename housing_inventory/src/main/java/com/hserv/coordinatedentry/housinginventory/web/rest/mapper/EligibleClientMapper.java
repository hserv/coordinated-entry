package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.EligibleClientDTO;

import org.mapstruct.*;
import java.util.List;
import java.util.UUID;

/**
 * Mapper for the entity EligibleClient and its DTO EligibleClientDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EligibleClientMapper {

    @Mapping(source = "clientInfo.id", target = "clientInfoId")
    EligibleClientDTO eligibleClientToEligibleClientDTO(EligibleClient eligibleClient);

    List<EligibleClientDTO> eligibleClientsToEligibleClientDTOs(List<EligibleClient> eligibleClients);

    @Mapping(source = "clientInfoId", target = "clientInfo")
    EligibleClient eligibleClientDTOToEligibleClient(EligibleClientDTO eligibleClientDTO);

    List<EligibleClient> eligibleClientDTOsToEligibleClients(List<EligibleClientDTO> eligibleClientDTOs);

    default ClientInfo clientInfoFromId(UUID id) {
        if (id == null) {
            return null;
        }
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setId(id);
        return clientInfo;
    }
}
