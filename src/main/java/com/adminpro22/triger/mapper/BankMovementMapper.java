package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.BankMovementDTO;
import com.adminpro22.triger.models.BankMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankMovementMapper extends GenericMapper<BankMovement, BankMovementDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    BankMovement asEntity(BankMovementDTO dto);
}