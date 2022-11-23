package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.CustomerDTO;
import com.adminpro22.triger.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Customer asEntity(CustomerDTO dto);
}