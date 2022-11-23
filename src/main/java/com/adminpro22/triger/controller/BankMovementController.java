package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.BankMovementDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "BankMovement API")
public interface BankMovementController {
    @ApiOperation("Add new data")
    public BankMovementDTO save(@RequestBody BankMovementDTO bankMovement);

    @ApiOperation("Find by Id")
    public BankMovementDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<BankMovementDTO> list();

    @ApiOperation("Pagination request")
    public Page<BankMovementDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public BankMovementDTO update(@RequestBody BankMovementDTO dto, @PathVariable("id") Long id);
}