package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.MatchReservationsDTO;

import org.mapstruct.*;
import java.util.List;
import java.util.UUID;

/**
 * Mapper for the entity MatchReservations and its DTO MatchReservationsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MatchReservationsMapper {

    @Mapping(source = "housingInventory.id", target = "housingInventoryId")
    @Mapping(source = "eligibleClient.id", target = "eligibleClientId")
    @Mapping(source = "note.id", target = "noteId")
    MatchReservationsDTO matchReservationsToMatchReservationsDTO(MatchReservations matchReservations);

    List<MatchReservationsDTO> matchReservationsToMatchReservationsDTOs(List<MatchReservations> matchReservations);

    @Mapping(source = "housingInventoryId", target = "housingInventory")
    @Mapping(source = "eligibleClientId", target = "eligibleClient")
    @Mapping(source = "noteId", target = "note")
    MatchReservations matchReservationsDTOToMatchReservations(MatchReservationsDTO matchReservationsDTO);

    List<MatchReservations> matchReservationsDTOsToMatchReservations(List<MatchReservationsDTO> matchReservationsDTOs);

    default HousingInventory housingInventoryFromId(UUID id) {
        if (id == null) {
            return null;
        }
        HousingInventory housingInventory = new HousingInventory();
        housingInventory.setHousingUnitId(id);
        return housingInventory;
    }

    default EligibleClient eligibleClientFromId(UUID id) {
        if (id == null) {
            return null;
        }
        EligibleClient eligibleClient = new EligibleClient();
        eligibleClient.setClientId(id);
        return eligibleClient;
    }

    default Note noteFromId(UUID id) {
        if (id == null) {
            return null;
        }
        Note note = new Note();
        note.setNoteId(id);
        return note;
    }
}
