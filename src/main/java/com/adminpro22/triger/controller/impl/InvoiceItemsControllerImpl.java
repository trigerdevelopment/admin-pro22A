package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.InvoiceItemsController;
import com.adminpro22.triger.dto.InvoiceItemsDTO;
import com.adminpro22.triger.mapper.InvoiceItemsMapper;
import com.adminpro22.triger.models.InvoiceItems;
import com.adminpro22.triger.service.InvoiceItemsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/invoice-items")
@RestController
public class InvoiceItemsControllerImpl implements InvoiceItemsController {
    private final InvoiceItemsService invoiceItemsService;
    private final InvoiceItemsMapper invoiceItemsMapper;

    public InvoiceItemsControllerImpl(InvoiceItemsService invoiceItemsService, InvoiceItemsMapper invoiceItemsMapper) {
        this.invoiceItemsService = invoiceItemsService;
        this.invoiceItemsMapper = invoiceItemsMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItemsDTO save(@RequestBody InvoiceItemsDTO invoiceItemsDTO) {
        InvoiceItems invoiceItems = invoiceItemsMapper.asEntity(invoiceItemsDTO);
        return invoiceItemsMapper.asDTO(invoiceItemsService.save(invoiceItems));
    }

    @Override
    @GetMapping("/{id}")
    public InvoiceItemsDTO findById(@PathVariable("id") Long id) {
        InvoiceItems invoiceItems = invoiceItemsService.findById(id).orElse(null);
        return invoiceItemsMapper.asDTO(invoiceItems);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        invoiceItemsService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<InvoiceItemsDTO> list() {
        return invoiceItemsMapper.asDTOList(invoiceItemsService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<InvoiceItemsDTO> pageQuery(Pageable pageable) {
        Page<InvoiceItems> invoiceItemsPage = invoiceItemsService.findAll(pageable);
        List<InvoiceItemsDTO> dtoList = invoiceItemsPage
                .stream()
                .map(invoiceItemsMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, invoiceItemsPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public InvoiceItemsDTO update(@RequestBody InvoiceItemsDTO invoiceItemsDTO, @PathVariable("id") Long id) {
        InvoiceItems invoiceItems = invoiceItemsMapper.asEntity(invoiceItemsDTO);
        return invoiceItemsMapper.asDTO(invoiceItemsService.update(invoiceItems, id));
    }
}