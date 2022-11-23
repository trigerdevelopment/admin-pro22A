package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.RawMaterialController;
import com.adminpro22.triger.dto.RawMaterialDTO;
import com.adminpro22.triger.mapper.RawMaterialMapper;
import com.adminpro22.triger.models.RawMaterial;
import com.adminpro22.triger.service.RawMaterialService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/raw-material")
@RestController
public class RawMaterialControllerImpl implements RawMaterialController {
    private final RawMaterialService rawMaterialService;
    private final RawMaterialMapper rawMaterialMapper;

    public RawMaterialControllerImpl(RawMaterialService rawMaterialService, RawMaterialMapper rawMaterialMapper) {
        this.rawMaterialService = rawMaterialService;
        this.rawMaterialMapper = rawMaterialMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RawMaterialDTO save(@RequestBody RawMaterialDTO rawMaterialDTO) {
        RawMaterial rawMaterial = rawMaterialMapper.asEntity(rawMaterialDTO);
        return rawMaterialMapper.asDTO(rawMaterialService.save(rawMaterial));
    }

    @Override
    @GetMapping("/{id}")
    public RawMaterialDTO findById(@PathVariable("id") Long id) {
        RawMaterial rawMaterial = rawMaterialService.findById(id).orElse(null);
        return rawMaterialMapper.asDTO(rawMaterial);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        rawMaterialService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<RawMaterialDTO> list() {
        return rawMaterialMapper.asDTOList(rawMaterialService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<RawMaterialDTO> pageQuery(Pageable pageable) {
        Page<RawMaterial> rawMaterialPage = rawMaterialService.findAll(pageable);
        List<RawMaterialDTO> dtoList = rawMaterialPage
                .stream()
                .map(rawMaterialMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, rawMaterialPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public RawMaterialDTO update(@RequestBody RawMaterialDTO rawMaterialDTO, @PathVariable("id") Long id) {
        RawMaterial rawMaterial = rawMaterialMapper.asEntity(rawMaterialDTO);
        return rawMaterialMapper.asDTO(rawMaterialService.update(rawMaterial, id));
    }
}