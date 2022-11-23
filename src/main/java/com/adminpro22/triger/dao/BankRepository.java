package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.Bank;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends PagingAndSortingRepository<Bank, Long> {
}