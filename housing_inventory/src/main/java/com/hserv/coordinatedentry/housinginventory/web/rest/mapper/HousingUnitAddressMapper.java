package com.hserv.coordinatedentry.housinginventory.web.rest.mapper;

import com.hserv.coordinatedentry.housinginventory.domain.*;
import com.hserv.coordinatedentry.housinginventory.web.rest.dto.HousingUnitAddressDTO;

import org.mapstruct.*;
import java.util.List;
import java.util.UUID;

/**
 * Mapper for the entity HousingUnitAddress and its DTO HousingUnitAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HousingUnitAddressMapper {

    @Mapping(source = "housingInventory.id", target = "housingInventoryId")
    HousingUnitAddressDTO housingUnitAddressToHousingUnitAddressDTO(HousingUnitAddress housingUnitAddress);

    List<HousingUnitAddressDTO> housingUnitAddressesToHousingUnitAddressDTOs(List<HousingUnitAddress> housingUnitAddresses);

    @Mapping(source = "housingInventoryId", target = "housingInventory")
    HousingUnitAddress housingUnitAddressDTOToHousingUnitAddress(HousingUnitAddressDTO housingUnitAddressDTO);

    List<HousingUnitAddress> housingUnitAddressDTOsToHousingUnitAddresses(List<HousingUnitAddressDTO> housingUnitAddressDTOs);

    default HousingInventory housingInventoryFromId(UUID housingUnitId) {
        if (housingUnitId == null) {
            return null;
        }
        HousingInventory housingInventory = new HousingInventory();
        housingInventory.setHousingUnitId(housingUnitId);
        return housingInventory;
    }
}
