package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.BankRepository;
import com.adminpro22.triger.dto.BankDTO;
import com.adminpro22.triger.mapper.BankMapper;
import com.adminpro22.triger.models.Bank;
import com.adminpro22.triger.service.BankService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankServiceImpl implements BankService {
    private final BankRepository repository;

    public BankServiceImpl(BankRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bank save(Bank entity) {
        return repository.save(entity);
    }

    @Override
    public List<Bank> save(List<Bank> entities) {
        return (List<Bank>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Bank> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Bank> findAll() {
        return (List<Bank>) repository.findAll();
    }

    @Override
    public Page<Bank> findAll(Pageable pageable) {
        Page<Bank> entityPage = repository.findAll(pageable);
        List<Bank> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Bank update(Bank entity, Long id) {
        Optional<Bank> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}