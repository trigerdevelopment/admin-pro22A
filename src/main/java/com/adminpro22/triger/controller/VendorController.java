package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.VendorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Vendor API")
public interface VendorController {
    @ApiOperation("Add new data")
    public VendorDTO save(@RequestBody VendorDTO vendor);

    @ApiOperation("Find by Id")
    public VendorDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<VendorDTO> list();

    @ApiOperation("Pagination request")
    public Page<VendorDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public VendorDTO update(@RequestBody VendorDTO dto, @PathVariable("id") Long id);
}