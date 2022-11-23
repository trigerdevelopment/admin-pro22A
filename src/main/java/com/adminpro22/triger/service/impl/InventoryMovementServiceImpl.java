package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.InventoryMovementRepository;
import com.adminpro22.triger.dto.InventoryMovementDTO;
import com.adminpro22.triger.mapper.InventoryMovementMapper;
import com.adminpro22.triger.models.InventoryMovement;
import com.adminpro22.triger.service.InventoryMovementService;
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
public class InventoryMovementServiceImpl implements InventoryMovementService {
    private final InventoryMovementRepository repository;

    public InventoryMovementServiceImpl(InventoryMovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryMovement save(InventoryMovement entity) {
        return repository.save(entity);
    }

    @Override
    public List<InventoryMovement> save(List<InventoryMovement> entities) {
        return (List<InventoryMovement>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<InventoryMovement> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<InventoryMovement> findAll() {
        return (List<InventoryMovement>) repository.findAll();
    }

    @Override
    public Page<InventoryMovement> findAll(Pageable pageable) {
        Page<InventoryMovement> entityPage = repository.findAll(pageable);
        List<InventoryMovement> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public InventoryMovement update(InventoryMovement entity, Long id) {
        Optional<InventoryMovement> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}