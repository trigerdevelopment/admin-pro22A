package com.adminpro22.triger.dao;

import com.adminpro22.triger.models.RawMaterial;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialRepository extends PagingAndSortingRepository<RawMaterial, Long> {
}