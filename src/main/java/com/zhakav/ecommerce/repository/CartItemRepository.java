package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {
    
}
