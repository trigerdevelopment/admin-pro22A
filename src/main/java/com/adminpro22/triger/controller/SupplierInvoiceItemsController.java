package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.SupplierInvoiceItemsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "SupplierInvoiceItems API")
public interface SupplierInvoiceItemsController {
    @ApiOperation("Add new data")
    public SupplierInvoiceItemsDTO save(@RequestBody SupplierInvoiceItemsDTO supplierInvoiceItems);

    @ApiOperation("Find by Id")
    public SupplierInvoiceItemsDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<SupplierInvoiceItemsDTO> list();

    @ApiOperation("Pagination request")
    public Page<SupplierInvoiceItemsDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public SupplierInvoiceItemsDTO update(@RequestBody SupplierInvoiceItemsDTO dto, @PathVariable("id") Long id);
}