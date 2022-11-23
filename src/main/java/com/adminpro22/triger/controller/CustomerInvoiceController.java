package com.adminpro22.triger.controller;

import com.adminpro22.triger.dto.CustomerInvoiceDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "CustomerInvoice API")
public interface CustomerInvoiceController {
    @ApiOperation("Add new data")
    public CustomerInvoiceDTO save(@RequestBody CustomerInvoiceDTO customerInvoice);

    @ApiOperation("Find by Id")
    public CustomerInvoiceDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<CustomerInvoiceDTO> list();

    @ApiOperation("Pagination request")
    public Page<CustomerInvoiceDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public CustomerInvoiceDTO update(@RequestBody CustomerInvoiceDTO dto, @PathVariable("id") Long id);
}