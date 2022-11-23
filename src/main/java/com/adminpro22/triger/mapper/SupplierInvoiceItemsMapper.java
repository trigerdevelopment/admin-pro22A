package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.SupplierInvoiceItemsDTO;
import com.adminpro22.triger.models.SupplierInvoiceItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierInvoiceItemsMapper extends GenericMapper<SupplierInvoiceItems, SupplierInvoiceItemsDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    SupplierInvoiceItems asEntity(SupplierInvoiceItemsDTO dto);
}