package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.CustomerInvoiceDTO;
import com.adminpro22.triger.models.CustomerInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerInvoiceMapper extends GenericMapper<CustomerInvoice, CustomerInvoiceDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    CustomerInvoice asEntity(CustomerInvoiceDTO dto);
}