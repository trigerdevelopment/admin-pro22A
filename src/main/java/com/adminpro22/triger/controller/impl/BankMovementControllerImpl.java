package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.BankMovementController;
import com.adminpro22.triger.dto.BankMovementDTO;
import com.adminpro22.triger.mapper.BankMovementMapper;
import com.adminpro22.triger.models.BankMovement;
import com.adminpro22.triger.service.BankMovementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/bank-movement")
@RestController
public class BankMovementControllerImpl implements BankMovementController {
    private final BankMovementService bankMovementService;
    private final BankMovementMapper bankMovementMapper;

    public BankMovementControllerImpl(BankMovementService bankMovementService, BankMovementMapper bankMovementMapper) {
        this.bankMovementService = bankMovementService;
        this.bankMovementMapper = bankMovementMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankMovementDTO save(@RequestBody BankMovementDTO bankMovementDTO) {
        BankMovement bankMovement = bankMovementMapper.asEntity(bankMovementDTO);
        return bankMovementMapper.asDTO(bankMovementService.save(bankMovement));
    }

    @Override
    @GetMapping("/{id}")
    public BankMovementDTO findById(@PathVariable("id") Long id) {
        BankMovement bankMovement = bankMovementService.findById(id).orElse(null);
        return bankMovementMapper.asDTO(bankMovement);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bankMovementService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<BankMovementDTO> list() {
        return bankMovementMapper.asDTOList(bankMovementService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<BankMovementDTO> pageQuery(Pageable pageable) {
        Page<BankMovement> bankMovementPage = bankMovementService.findAll(pageable);
        List<BankMovementDTO> dtoList = bankMovementPage
                .stream()
                .map(bankMovementMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, bankMovementPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public BankMovementDTO update(@RequestBody BankMovementDTO bankMovementDTO, @PathVariable("id") Long id) {
        BankMovement bankMovement = bankMovementMapper.asEntity(bankMovementDTO);
        return bankMovementMapper.asDTO(bankMovementService.update(bankMovement, id));
    }
}