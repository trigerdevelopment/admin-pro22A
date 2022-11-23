package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.SupplierInvoiceRepository;
import com.adminpro22.triger.dto.SupplierInvoiceDTO;
import com.adminpro22.triger.mapper.SupplierInvoiceMapper;
import com.adminpro22.triger.models.SupplierInvoice;
import com.adminpro22.triger.service.SupplierInvoiceService;
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
public class SupplierInvoiceServiceImpl implements SupplierInvoiceService {
    private final SupplierInvoiceRepository repository;

    public SupplierInvoiceServiceImpl(SupplierInvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierInvoice save(SupplierInvoice entity) {
        return repository.save(entity);
    }

    @Override
    public List<SupplierInvoice> save(List<SupplierInvoice> entities) {
        return (List<SupplierInvoice>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SupplierInvoice> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<SupplierInvoice> findAll() {
        return (List<SupplierInvoice>) repository.findAll();
    }

    @Override
    public Page<SupplierInvoice> findAll(Pageable pageable) {
        Page<SupplierInvoice> entityPage = repository.findAll(pageable);
        List<SupplierInvoice> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public SupplierInvoice update(SupplierInvoice entity, Long id) {
        Optional<SupplierInvoice> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}