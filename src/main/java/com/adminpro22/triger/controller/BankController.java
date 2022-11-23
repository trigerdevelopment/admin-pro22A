package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.BankDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Bank API")
public interface BankController {
    @ApiOperation("Add new data")
    public BankDTO save(@RequestBody BankDTO bank);

    @ApiOperation("Find by Id")
    public BankDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<BankDTO> list();

    @ApiOperation("Pagination request")
    public Page<BankDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public BankDTO update(@RequestBody BankDTO dto, @PathVariable("id") Long id);
}