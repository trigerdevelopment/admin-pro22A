package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.Vendor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends PagingAndSortingRepository<Vendor, Long> {
}