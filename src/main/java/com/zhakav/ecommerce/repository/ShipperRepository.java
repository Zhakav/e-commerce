package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.Shipper;

public interface ShipperRepository extends CrudRepository<Shipper,Long> {
    
}
