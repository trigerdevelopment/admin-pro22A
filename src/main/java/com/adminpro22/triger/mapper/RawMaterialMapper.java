package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.RawMaterialDTO;
import com.adminpro22.triger.models.RawMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper extends GenericMapper<RawMaterial, RawMaterialDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    RawMaterial asEntity(RawMaterialDTO dto);
}