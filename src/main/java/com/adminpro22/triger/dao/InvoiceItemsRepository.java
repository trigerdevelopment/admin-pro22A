package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.InventoryMovement;
import com.adminpro22.triger.models.InvoiceItems;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemsRepository extends PagingAndSortingRepository<InvoiceItems, Long> {
}