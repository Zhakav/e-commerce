package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.AdminType;

public interface AdminTypeRepository extends CrudRepository<AdminType,Long> {
    
}
