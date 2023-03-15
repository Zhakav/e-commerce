package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.Discount;

public interface DiscountRepository extends CrudRepository<Discount,Long> {
    
}
