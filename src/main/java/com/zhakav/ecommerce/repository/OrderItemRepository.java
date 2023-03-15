package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
    
}
