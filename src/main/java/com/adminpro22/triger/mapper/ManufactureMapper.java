package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.ManufactureDTO;
import com.adminpro22.triger.models.Manufacture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManufactureMapper extends GenericMapper<Manufacture, ManufactureDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Manufacture asEntity(ManufactureDTO dto);
}