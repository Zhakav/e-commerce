package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {
    
}
