package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.CustomerInvoice;
import com.adminpro22.triger.models.InvoiceItems;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerInvoiceRepository extends PagingAndSortingRepository<CustomerInvoice, Long> {
}