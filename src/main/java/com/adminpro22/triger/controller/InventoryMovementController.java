package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.InventoryMovementDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "InventoryMovement API")
public interface InventoryMovementController {
    @ApiOperation("Add new data")
    public InventoryMovementDTO save(@RequestBody InventoryMovementDTO inventoryMovement);

    @ApiOperation("Find by Id")
    public InventoryMovementDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<InventoryMovementDTO> list();

    @ApiOperation("Pagination request")
    public Page<InventoryMovementDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public InventoryMovementDTO update(@RequestBody InventoryMovementDTO dto, @PathVariable("id") Long id);
}