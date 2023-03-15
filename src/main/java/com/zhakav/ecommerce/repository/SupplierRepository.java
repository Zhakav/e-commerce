package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {
    
}
