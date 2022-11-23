package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.BankDTO;
import com.adminpro22.triger.models.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankMapper extends GenericMapper<Bank, BankDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Bank asEntity(BankDTO dto);
}