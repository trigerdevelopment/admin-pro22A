package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.ManufactureRepository;
import com.adminpro22.triger.dto.ManufactureDTO;
import com.adminpro22.triger.mapper.ManufactureMapper;
import com.adminpro22.triger.models.Manufacture;
import com.adminpro22.triger.service.ManufactureService;
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
public class ManufactureServiceImpl implements ManufactureService {
    private final ManufactureRepository repository;

    public ManufactureServiceImpl(ManufactureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Manufacture save(Manufacture entity) {
        return repository.save(entity);
    }

    @Override
    public List<Manufacture> save(List<Manufacture> entities) {
        return (List<Manufacture>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Manufacture> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Manufacture> findAll() {
        return (List<Manufacture>) repository.findAll();
    }

    @Override
    public Page<Manufacture> findAll(Pageable pageable) {
        Page<Manufacture> entityPage = repository.findAll(pageable);
        List<Manufacture> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Manufacture update(Manufacture entity, Long id) {
        Optional<Manufacture> optional = findById(id) ;
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}