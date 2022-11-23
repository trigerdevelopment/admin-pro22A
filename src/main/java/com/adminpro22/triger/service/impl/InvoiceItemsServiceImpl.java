package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.InvoiceItemsRepository;
import com.adminpro22.triger.dto.InvoiceItemsDTO;
import com.adminpro22.triger.mapper.InvoiceItemsMapper;
import com.adminpro22.triger.models.InvoiceItems;
import com.adminpro22.triger.service.InvoiceItemsService;
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
public class InvoiceItemsServiceImpl implements InvoiceItemsService {
    private final InvoiceItemsRepository repository;

    public InvoiceItemsServiceImpl(InvoiceItemsRepository repository) {
        this.repository = repository;
    }

    @Override
    public InvoiceItems save(InvoiceItems entity) {
        return repository.save(entity);
    }

    @Override
    public List<InvoiceItems> save(List<InvoiceItems> entities) {
        return (List<InvoiceItems>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<InvoiceItems> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<InvoiceItems> findAll() {
        return (List<InvoiceItems>) repository.findAll();
    }

    @Override
    public Page<InvoiceItems> findAll(Pageable pageable) {
        Page<InvoiceItems> entityPage = repository.findAll(pageable);
        List<InvoiceItems> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public InvoiceItems update(InvoiceItems entity, Long id) {
        Optional<InvoiceItems> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}