package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.PaymentDetail;

public interface PaymentDetailRepository extends CrudRepository<PaymentDetail,Long> {
    
}
