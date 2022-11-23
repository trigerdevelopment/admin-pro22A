package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.ManufactureController;
import com.adminpro22.triger.dto.ManufactureDTO;
import com.adminpro22.triger.mapper.ManufactureMapper;
import com.adminpro22.triger.models.Manufacture;
import com.adminpro22.triger.service.ManufactureService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/manufacture")
@RestController
public class ManufactureControllerImpl implements ManufactureController {
    private final ManufactureService manufactureService;
    private final ManufactureMapper manufactureMapper;

    public ManufactureControllerImpl(ManufactureService manufactureService, ManufactureMapper manufactureMapper) {
        this.manufactureService = manufactureService;
        this.manufactureMapper = manufactureMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufactureDTO save(@RequestBody ManufactureDTO manufactureDTO) {
        Manufacture manufacture = manufactureMapper.asEntity(manufactureDTO);
        return manufactureMapper.asDTO(manufactureService.save(manufacture));
    }

    @Override
    @GetMapping("/{id}")
    public ManufactureDTO findById(@PathVariable("id") Long id) {
        Manufacture manufacture = manufactureService.findById(id).orElse(null);
        return manufactureMapper.asDTO(manufacture);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        manufactureService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<ManufactureDTO> list() {
        return manufactureMapper.asDTOList(manufactureService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<ManufactureDTO> pageQuery(Pageable pageable) {
        Page<Manufacture> manufacturePage = manufactureService.findAll(pageable);
        List<ManufactureDTO> dtoList = manufacturePage
                .stream()
                .map(manufactureMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, manufacturePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public ManufactureDTO update(@RequestBody ManufactureDTO manufactureDTO, @PathVariable("id") Long id) {
        Manufacture manufacture = manufactureMapper.asEntity(manufactureDTO);
        return manufactureMapper.asDTO(manufactureService.update(manufacture, id));
    }
}