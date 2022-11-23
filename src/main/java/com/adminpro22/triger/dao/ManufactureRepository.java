package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.Manufacture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends PagingAndSortingRepository<Manufacture, Long> {
}