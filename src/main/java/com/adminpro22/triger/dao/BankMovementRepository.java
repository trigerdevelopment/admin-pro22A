package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.BankMovement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankMovementRepository extends PagingAndSortingRepository<BankMovement, Long> {
}