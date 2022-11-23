package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.SupplierInvoice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierInvoiceRepository extends PagingAndSortingRepository<SupplierInvoice, Long> {
}