package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.InventoryMovementController;
import com.adminpro22.triger.dto.InventoryMovementDTO;
import com.adminpro22.triger.mapper.InventoryMovementMapper;
import com.adminpro22.triger.models.InventoryMovement;
import com.adminpro22.triger.service.InventoryMovementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/inventory-movement")
@RestController
public class InventoryMovementControllerImpl implements InventoryMovementController {
    private final InventoryMovementService inventoryMovementService;
    private final InventoryMovementMapper inventoryMovementMapper;

    public InventoryMovementControllerImpl(InventoryMovementService inventoryMovementService, InventoryMovementMapper inventoryMovementMapper) {
        this.inventoryMovementService = inventoryMovementService;
        this.inventoryMovementMapper = inventoryMovementMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryMovementDTO save(@RequestBody InventoryMovementDTO inventoryMovementDTO) {
        InventoryMovement inventoryMovement = inventoryMovementMapper.asEntity(inventoryMovementDTO);
        return inventoryMovementMapper.asDTO(inventoryMovementService.save(inventoryMovement));
    }

    @Override
    @GetMapping("/{id}")
    public InventoryMovementDTO findById(@PathVariable("id") Long id) {
        InventoryMovement inventoryMovement = inventoryMovementService.findById(id).orElse(null);
        return inventoryMovementMapper.asDTO(inventoryMovement);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        inventoryMovementService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<InventoryMovementDTO> list() {
        return inventoryMovementMapper.asDTOList(inventoryMovementService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<InventoryMovementDTO> pageQuery(Pageable pageable) {
        Page<InventoryMovement> inventoryMovementPage = inventoryMovementService.findAll(pageable);
        List<InventoryMovementDTO> dtoList = inventoryMovementPage
                .stream()
                .map(inventoryMovementMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, inventoryMovementPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public InventoryMovementDTO update(@RequestBody InventoryMovementDTO inventoryMovementDTO, @PathVariable("id") Long id) {
        InventoryMovement inventoryMovement = inventoryMovementMapper.asEntity(inventoryMovementDTO);
        return inventoryMovementMapper.asDTO(inventoryMovementService.update(inventoryMovement, id));
    }
}