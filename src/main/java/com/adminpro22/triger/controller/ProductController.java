package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Product API")
public interface ProductController {
    @ApiOperation("Add new data")
    public ProductDTO save(@RequestBody ProductDTO product);

    @ApiOperation("Find by Id")
    public ProductDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<ProductDTO> list();

    @ApiOperation("Pagination request")
    public Page<ProductDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public ProductDTO update(@RequestBody ProductDTO dto, @PathVariable("id") Long id);
}