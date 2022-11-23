package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.BankMovementRepository;
import com.adminpro22.triger.dto.BankMovementDTO;
import com.adminpro22.triger.mapper.BankMovementMapper;
import com.adminpro22.triger.models.BankMovement;
import com.adminpro22.triger.service.BankMovementService;
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
public class BankMovementServiceImpl implements BankMovementService {
    private final BankMovementRepository repository;

    public BankMovementServiceImpl(BankMovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public BankMovement save(BankMovement entity) {
        return repository.save(entity);
    }

    @Override
    public List<BankMovement> save(List<BankMovement> entities) {
        return (List<BankMovement>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<BankMovement> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<BankMovement> findAll() {
        return (List<BankMovement>) repository.findAll();
    }

    @Override
    public Page<BankMovement> findAll(Pageable pageable) {
        Page<BankMovement> entityPage = repository.findAll(pageable);
        List<BankMovement> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public BankMovement update(BankMovement entity, Long id) {
        Optional<BankMovement> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}