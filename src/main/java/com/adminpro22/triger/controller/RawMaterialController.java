package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.RawMaterialDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "RawMaterial API")
public interface RawMaterialController {
    @ApiOperation("Add new data")
    public RawMaterialDTO save(@RequestBody RawMaterialDTO rawMaterial);

    @ApiOperation("Find by Id")
    public RawMaterialDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<RawMaterialDTO> list();

    @ApiOperation("Pagination request")
    public Page<RawMaterialDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public RawMaterialDTO update(@RequestBody RawMaterialDTO dto, @PathVariable("id") Long id);
}