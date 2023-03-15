package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.ShoppingSession;

public interface ShoppingSessionRepository extends CrudRepository<ShoppingSession,Long> {
    
}
