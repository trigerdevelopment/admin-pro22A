package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.SupplierInvoiceItemsController;
import com.adminpro22.triger.dto.SupplierInvoiceItemsDTO;
import com.adminpro22.triger.mapper.SupplierInvoiceItemsMapper;
import com.adminpro22.triger.models.SupplierInvoiceItems;
import com.adminpro22.triger.service.SupplierInvoiceItemsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/supplier-invoice-items")
@RestController
public class SupplierInvoiceItemsControllerImpl implements SupplierInvoiceItemsController {
    private final SupplierInvoiceItemsService supplierInvoiceItemsService;
    private final SupplierInvoiceItemsMapper supplierInvoiceItemsMapper;

    public SupplierInvoiceItemsControllerImpl(SupplierInvoiceItemsService supplierInvoiceItemsService, SupplierInvoiceItemsMapper supplierInvoiceItemsMapper) {
        this.supplierInvoiceItemsService = supplierInvoiceItemsService;
        this.supplierInvoiceItemsMapper = supplierInvoiceItemsMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierInvoiceItemsDTO save(@RequestBody SupplierInvoiceItemsDTO supplierInvoiceItemsDTO) {
        SupplierInvoiceItems supplierInvoiceItems = supplierInvoiceItemsMapper.asEntity(supplierInvoiceItemsDTO);
        return supplierInvoiceItemsMapper.asDTO(supplierInvoiceItemsService.save(supplierInvoiceItems));
    }

    @Override
    @GetMapping("/{id}")
    public SupplierInvoiceItemsDTO findById(@PathVariable("id") Long id) {
        SupplierInvoiceItems supplierInvoiceItems = supplierInvoiceItemsService.findById(id).orElse(null);
        return supplierInvoiceItemsMapper.asDTO(supplierInvoiceItems);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        supplierInvoiceItemsService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<SupplierInvoiceItemsDTO> list() {
        return supplierInvoiceItemsMapper.asDTOList(supplierInvoiceItemsService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<SupplierInvoiceItemsDTO> pageQuery(Pageable pageable) {
        Page<SupplierInvoiceItems> supplierInvoiceItemsPage = supplierInvoiceItemsService.findAll(pageable);
        List<SupplierInvoiceItemsDTO> dtoList = supplierInvoiceItemsPage
                .stream()
                .map(supplierInvoiceItemsMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, supplierInvoiceItemsPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public SupplierInvoiceItemsDTO update(@RequestBody SupplierInvoiceItemsDTO supplierInvoiceItemsDTO, @PathVariable("id") Long id) {
        SupplierInvoiceItems supplierInvoiceItems = supplierInvoiceItemsMapper.asEntity(supplierInvoiceItemsDTO);
        return supplierInvoiceItemsMapper.asDTO(supplierInvoiceItemsService.update(supplierInvoiceItems, id));
    }
}