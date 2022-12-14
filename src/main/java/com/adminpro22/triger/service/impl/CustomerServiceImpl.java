package com.adminpro22.triger.service.impl;

import com.adminpro22.triger.dao.CustomerRepository;
import com.adminpro22.triger.dto.CustomerDTO;
import com.adminpro22.triger.mapper.CustomerMapper;
import com.adminpro22.triger.models.Customer;
import com.adminpro22.triger.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer entity) {
        return repository.save(entity);
    }

    @Override
    public List<Customer> save(List<Customer> entities) {
        return (List<Customer>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) repository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        Page<Customer> entityPage = repository.findAll(pageable);
        List<Customer> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Customer update(Customer entity, Long id) {
        Optional<Customer> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}