package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.VendorDTO;
import com.adminpro22.triger.models.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendorMapper extends GenericMapper<Vendor, VendorDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Vendor asEntity(VendorDTO dto);
}