package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.InventoryMovementDTO;
import com.adminpro22.triger.models.InventoryMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper extends GenericMapper<InventoryMovement, InventoryMovementDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    InventoryMovement asEntity(InventoryMovementDTO dto);
}