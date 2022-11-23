package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.CustomerInvoiceController;
import com.adminpro22.triger.dto.CustomerInvoiceDTO;
import com.adminpro22.triger.mapper.CustomerInvoiceMapper;
import com.adminpro22.triger.models.CustomerInvoice;
import com.adminpro22.triger.service.CustomerInvoiceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/customer-invoice")
@RestController
public class CustomerInvoiceControllerImpl implements CustomerInvoiceController {
    private final CustomerInvoiceService customerInvoiceService;
    private final CustomerInvoiceMapper customerInvoiceMapper;

    public CustomerInvoiceControllerImpl(CustomerInvoiceService customerInvoiceService, CustomerInvoiceMapper customerInvoiceMapper) {
        this.customerInvoiceService = customerInvoiceService;
        this.customerInvoiceMapper = customerInvoiceMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerInvoiceDTO save(@RequestBody CustomerInvoiceDTO customerInvoiceDTO) {
        CustomerInvoice customerInvoice = customerInvoiceMapper.asEntity(customerInvoiceDTO);
        return customerInvoiceMapper.asDTO(customerInvoiceService.save(customerInvoice));
    }

    @Override
    @GetMapping("/{id}")
    public CustomerInvoiceDTO findById(@PathVariable("id") Long id) {
        CustomerInvoice customerInvoice = customerInvoiceService.findById(id).orElse(null);
        return customerInvoiceMapper.asDTO(customerInvoice);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerInvoiceService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<CustomerInvoiceDTO> list() {
        return customerInvoiceMapper.asDTOList(customerInvoiceService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<CustomerInvoiceDTO> pageQuery(Pageable pageable) {
        Page<CustomerInvoice> customerInvoicePage = customerInvoiceService.findAll(pageable);
        List<CustomerInvoiceDTO> dtoList = customerInvoicePage
                .stream()
                .map(customerInvoiceMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, customerInvoicePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public CustomerInvoiceDTO update(@RequestBody CustomerInvoiceDTO customerInvoiceDTO, @PathVariable("id") Long id) {
        CustomerInvoice customerInvoice = customerInvoiceMapper.asEntity(customerInvoiceDTO);
        return customerInvoiceMapper.asDTO(customerInvoiceService.update(customerInvoice, id));
    }
}