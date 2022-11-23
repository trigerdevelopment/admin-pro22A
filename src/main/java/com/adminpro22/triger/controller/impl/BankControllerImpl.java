package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.BankController;
import com.adminpro22.triger.dto.BankDTO;
import com.adminpro22.triger.mapper.BankMapper;
import com.adminpro22.triger.models.Bank;
import com.adminpro22.triger.service.BankService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/bank")
@RestController
public class BankControllerImpl implements BankController {
    private final BankService bankService;
    private final BankMapper bankMapper;

    public BankControllerImpl(BankService bankService, BankMapper bankMapper) {
        this.bankService = bankService;
        this.bankMapper = bankMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankDTO save(@RequestBody BankDTO bankDTO) {
        Bank bank = bankMapper.asEntity(bankDTO);
        return bankMapper.asDTO(bankService.save(bank));
    }

    @Override
    @GetMapping("/{id}")
    public BankDTO findById(@PathVariable("id") Long id) {
        Bank bank = bankService.findById(id).orElse(null);
        return bankMapper.asDTO(bank);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bankService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<BankDTO> list() {
        return bankMapper.asDTOList(bankService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<BankDTO> pageQuery(Pageable pageable) {
        Page<Bank> bankPage = bankService.findAll(pageable);
        List<BankDTO> dtoList = bankPage
                .stream()
                .map(bankMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, bankPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public BankDTO update(@RequestBody BankDTO bankDTO, @PathVariable("id") Long id) {
        Bank bank = bankMapper.asEntity(bankDTO);
        return bankMapper.asDTO(bankService.update(bank, id));
    }
}