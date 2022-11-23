package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.ManufactureDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Manufacture API")
public interface ManufactureController {
    @ApiOperation("Add new data")
    public ManufactureDTO save(@RequestBody ManufactureDTO manufacture);

    @ApiOperation("Find by Id")
    public ManufactureDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<ManufactureDTO> list();

    @ApiOperation("Pagination request")
    public Page<ManufactureDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public ManufactureDTO update(@RequestBody ManufactureDTO dto, @PathVariable("id") Long id);
}