package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.VendorController;
import com.adminpro22.triger.dto.VendorDTO;
import com.adminpro22.triger.mapper.VendorMapper;
import com.adminpro22.triger.models.Vendor;
import com.adminpro22.triger.service.VendorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/vendor")
@RestController
public class VendorControllerImpl implements VendorController {
    private final VendorService vendorService;
    private final VendorMapper vendorMapper;

    public VendorControllerImpl(VendorService vendorService, VendorMapper vendorMapper) {
        this.vendorService = vendorService;
        this.vendorMapper = vendorMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO save(@RequestBody VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.asEntity(vendorDTO);
        return vendorMapper.asDTO(vendorService.save(vendor));
    }

    @Override
    @GetMapping("/{id}")
    public VendorDTO findById(@PathVariable("id") Long id) {
        Vendor vendor = vendorService.findById(id).orElse(null);
        return vendorMapper.asDTO(vendor);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        vendorService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<VendorDTO> list() {
        return vendorMapper.asDTOList(vendorService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<VendorDTO> pageQuery(Pageable pageable) {
        Page<Vendor> vendorPage = vendorService.findAll(pageable);
        List<VendorDTO> dtoList = vendorPage
                .stream()
                .map(vendorMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, vendorPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public VendorDTO update(@RequestBody VendorDTO vendorDTO, @PathVariable("id") Long id) {
        Vendor vendor = vendorMapper.asEntity(vendorDTO);
        return vendorMapper.asDTO(vendorService.update(vendor, id));
    }
}