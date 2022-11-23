package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.InvoiceItemsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "InvoiceItems API")
public interface InvoiceItemsController {
    @ApiOperation("Add new data")
    public InvoiceItemsDTO save(@RequestBody InvoiceItemsDTO invoiceItems);

    @ApiOperation("Find by Id")
    public InvoiceItemsDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<InvoiceItemsDTO> list();

    @ApiOperation("Pagination request")
    public Page<InvoiceItemsDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public InvoiceItemsDTO update(@RequestBody InvoiceItemsDTO dto, @PathVariable("id") Long id);
}