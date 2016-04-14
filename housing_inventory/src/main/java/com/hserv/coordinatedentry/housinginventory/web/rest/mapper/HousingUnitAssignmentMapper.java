package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingUnitAssignmentDTO;

import org.mapstruct.*;
import java.util.List;
import java.util.UUID;

/**
 * Mapper for the entity HousingUnitAssignment and its DTO HousingUnitAssignmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HousingUnitAssignmentMapper {

    @Mapping(source = "housingInventory.id", target = "housingInventoryId")
    HousingUnitAssignmentDTO housingUnitAssignmentToHousingUnitAssignmentDTO(HousingUnitAssignment housingUnitAssignment);

    List<HousingUnitAssignmentDTO> housingUnitAssignmentsToHousingUnitAssignmentDTOs(List<HousingUnitAssignment> housingUnitAssignments);

    @Mapping(source = "housingInventoryId", target = "housingInventory")
    HousingUnitAssignment housingUnitAssignmentDTOToHousingUnitAssignment(HousingUnitAssignmentDTO housingUnitAssignmentDTO);

    List<HousingUnitAssignment> housingUnitAssignmentDTOsToHousingUnitAssignments(List<HousingUnitAssignmentDTO> housingUnitAssignmentDTOs);

    default HousingInventory housingInventoryFromId(UUID housingUnitId) {
        if (housingUnitId == null) {
            return null;
        }
        HousingInventory housingInventory = new HousingInventory();
        housingInventory.setHousingUnitId(housingUnitId);
        return housingInventory;
    }
}
