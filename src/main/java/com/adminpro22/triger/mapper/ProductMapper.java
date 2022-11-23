package com.adminpro22.triger.mapper;

import com.adminpro22.triger.dto.ProductDTO;
import com.adminpro22.triger.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Product asEntity(ProductDTO dto);
}