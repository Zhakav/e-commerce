package com.zhakav.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment,Long>{

    public List<UserPayment> findByUserId(long userId);
    
}
