package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.CustomerInvoiceRepository;
import com.adminpro22.triger.dto.CustomerInvoiceDTO;
import com.adminpro22.triger.mapper.CustomerInvoiceMapper;
import com.adminpro22.triger.models.CustomerInvoice;
import com.adminpro22.triger.service.CustomerInvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {
    private final CustomerInvoiceRepository repository;

    public CustomerInvoiceServiceImpl(CustomerInvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerInvoice save(CustomerInvoice entity) {
        return repository.save(entity);
    }

    @Override
    public List<CustomerInvoice> save(List<CustomerInvoice> entities) {
        return (List<CustomerInvoice>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<CustomerInvoice> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CustomerInvoice> findAll() {
        return (List<CustomerInvoice>) repository.findAll();
    }

    @Override
    public Page<CustomerInvoice> findAll(Pageable pageable) {
        Page<CustomerInvoice> entityPage = repository.findAll(pageable);
        List<CustomerInvoice> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public CustomerInvoice update(CustomerInvoice entity, Long id) {
        Optional<CustomerInvoice> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}