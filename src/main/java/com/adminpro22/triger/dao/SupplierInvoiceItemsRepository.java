package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.SupplierInvoiceItems;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierInvoiceItemsRepository extends PagingAndSortingRepository<SupplierInvoiceItems, Long> {
}