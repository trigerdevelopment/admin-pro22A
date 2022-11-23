package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.RawMaterialRepository;
import com.adminpro22.triger.dto.RawMaterialDTO;
import com.adminpro22.triger.mapper.RawMaterialMapper;
import com.adminpro22.triger.models.RawMaterial;
import com.adminpro22.triger.service.RawMaterialService;
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
public class RawMaterialServiceImpl implements RawMaterialService {
    private final RawMaterialRepository repository;

    public RawMaterialServiceImpl(RawMaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public RawMaterial save(RawMaterial entity) {
        return repository.save(entity);
    }

    @Override
    public List<RawMaterial> save(List<RawMaterial> entities) {
        return (List<RawMaterial>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<RawMaterial> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<RawMaterial> findAll() {
        return (List<RawMaterial>) repository.findAll();
    }

    @Override
    public Page<RawMaterial> findAll(Pageable pageable) {
        Page<RawMaterial> entityPage = repository.findAll(pageable);
        List<RawMaterial> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public RawMaterial update(RawMaterial entity, Long id) {
        Optional<RawMaterial> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}