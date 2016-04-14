package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingInventoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity HousingInventory and its DTO HousingInventoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HousingInventoryMapper {

    HousingInventoryDTO housingInventoryToHousingInventoryDTO(HousingInventory housingInventory);

    List<HousingInventoryDTO> housingInventoriesToHousingInventoryDTOs(List<HousingInventory> housingInventories);

    @Mapping(target = "housingUnitAddresss", ignore = true)
    @Mapping(target = "housingUnitAssignments", ignore = true)
    @Mapping(target = "matchReservationss", ignore = true)
    HousingInventory housingInventoryDTOToHousingInventory(HousingInventoryDTO housingInventoryDTO);

    List<HousingInventory> housingInventoryDTOsToHousingInventories(List<HousingInventoryDTO> housingInventoryDTOs);
}
