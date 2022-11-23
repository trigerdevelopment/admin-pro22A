package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.VendorRepository;
import com.adminpro22.triger.dto.VendorDTO;
import com.adminpro22.triger.mapper.VendorMapper;
import com.adminpro22.triger.models.Vendor;
import com.adminpro22.triger.service.VendorService;
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
public class VendorServiceImpl implements VendorService {
    private final VendorRepository repository;

    public VendorServiceImpl(VendorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vendor save(Vendor entity) {
        return repository.save(entity);
    }

    @Override
    public List<Vendor> save(List<Vendor> entities) {
        return (List<Vendor>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Vendor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Vendor> findAll() {
        return (List<Vendor>) repository.findAll();
    }

    @Override
    public Page<Vendor> findAll(Pageable pageable) {
        Page<Vendor> entityPage = repository.findAll(pageable);
        List<Vendor> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Vendor update(Vendor entity, Long id) {
        Optional<Vendor> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}