package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.SupplierInvoiceItemsRepository;
import com.adminpro22.triger.dto.SupplierInvoiceItemsDTO;
import com.adminpro22.triger.mapper.SupplierInvoiceItemsMapper;
import com.adminpro22.triger.models.SupplierInvoiceItems;
import com.adminpro22.triger.service.SupplierInvoiceItemsService;
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
public class SupplierInvoiceItemsServiceImpl implements SupplierInvoiceItemsService {
    private final SupplierInvoiceItemsRepository repository;

    public SupplierInvoiceItemsServiceImpl(SupplierInvoiceItemsRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierInvoiceItems save(SupplierInvoiceItems entity) {
        return repository.save(entity);
    }

    @Override
    public List<SupplierInvoiceItems> save(List<SupplierInvoiceItems> entities) {
        return (List<SupplierInvoiceItems>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SupplierInvoiceItems> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<SupplierInvoiceItems> findAll() {
        return (List<SupplierInvoiceItems>) repository.findAll();
    }

    @Override
    public Page<SupplierInvoiceItems> findAll(Pageable pageable) {
        Page<SupplierInvoiceItems> entityPage = repository.findAll(pageable);
        List<SupplierInvoiceItems> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public SupplierInvoiceItems update(SupplierInvoiceItems entity, Long id) {
        Optional<SupplierInvoiceItems> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}