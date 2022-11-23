package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.InvoiceItemsDTO;
import com.adminpro22.triger.models.InvoiceItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceItemsMapper extends GenericMapper<InvoiceItems, InvoiceItemsDTO> {
    @Override
    InvoiceItems asEntity(InvoiceItemsDTO dto);
}