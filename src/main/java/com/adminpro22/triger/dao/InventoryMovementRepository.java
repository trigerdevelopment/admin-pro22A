package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.InventoryMovement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementRepository extends PagingAndSortingRepository<InventoryMovement, Long> {
}