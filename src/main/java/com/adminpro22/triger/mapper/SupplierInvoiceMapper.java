package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.SupplierInvoiceDTO;
import com.adminpro22.triger.models.SupplierInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierInvoiceMapper extends GenericMapper<SupplierInvoice, SupplierInvoiceDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    SupplierInvoice asEntity(SupplierInvoiceDTO dto);
}