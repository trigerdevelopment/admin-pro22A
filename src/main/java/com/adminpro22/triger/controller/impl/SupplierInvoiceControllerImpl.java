package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.SupplierInvoiceController;
import com.adminpro22.triger.dto.SupplierInvoiceDTO;
import com.adminpro22.triger.mapper.SupplierInvoiceMapper;
import com.adminpro22.triger.models.SupplierInvoice;
import com.adminpro22.triger.service.SupplierInvoiceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/supplier-invoice")
@RestController
public class SupplierInvoiceControllerImpl implements SupplierInvoiceController {
    private final SupplierInvoiceService supplierInvoiceService;
    private final SupplierInvoiceMapper supplierInvoiceMapper;

    public SupplierInvoiceControllerImpl(SupplierInvoiceService supplierInvoiceService, SupplierInvoiceMapper supplierInvoiceMapper) {
        this.supplierInvoiceService = supplierInvoiceService;
        this.supplierInvoiceMapper = supplierInvoiceMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierInvoiceDTO save(@RequestBody SupplierInvoiceDTO supplierInvoiceDTO) {
        SupplierInvoice supplierInvoice = supplierInvoiceMapper.asEntity(supplierInvoiceDTO);
        return supplierInvoiceMapper.asDTO(supplierInvoiceService.save(supplierInvoice));
    }

    @Override
    @GetMapping("/{id}")
    public SupplierInvoiceDTO findById(@PathVariable("id") Long id) {
        SupplierInvoice supplierInvoice = supplierInvoiceService.findById(id).orElse(null);
        return supplierInvoiceMapper.asDTO(supplierInvoice);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        supplierInvoiceService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<SupplierInvoiceDTO> list() {
        return supplierInvoiceMapper.asDTOList(supplierInvoiceService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<SupplierInvoiceDTO> pageQuery(Pageable pageable) {
        Page<SupplierInvoice> supplierInvoicePage = supplierInvoiceService.findAll(pageable);
        List<SupplierInvoiceDTO> dtoList = supplierInvoicePage
                .stream()
                .map(supplierInvoiceMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, supplierInvoicePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public SupplierInvoiceDTO update(@RequestBody SupplierInvoiceDTO supplierInvoiceDTO, @PathVariable("id") Long id) {
        SupplierInvoice supplierInvoice = supplierInvoiceMapper.asEntity(supplierInvoiceDTO);
        return supplierInvoiceMapper.asDTO(supplierInvoiceService.update(supplierInvoice, id));
    }
}