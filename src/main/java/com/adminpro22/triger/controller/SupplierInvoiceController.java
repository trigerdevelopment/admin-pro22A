package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.SupplierInvoiceDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "SupplierInvoice API")
public interface SupplierInvoiceController {
    @ApiOperation("Add new data")
    public SupplierInvoiceDTO save(@RequestBody SupplierInvoiceDTO supplierInvoice);

    @ApiOperation("Find by Id")
    public SupplierInvoiceDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<SupplierInvoiceDTO> list();

    @ApiOperation("Pagination request")
    public Page<SupplierInvoiceDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public SupplierInvoiceDTO update(@RequestBody SupplierInvoiceDTO dto, @PathVariable("id") Long id);
}