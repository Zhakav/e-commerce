package com.zhakav.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {
    
    public List<OrderDetail> findByUserId(long userId);

}
