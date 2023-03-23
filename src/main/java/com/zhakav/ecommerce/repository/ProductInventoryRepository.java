package com.zhakav.ecommerce.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.ProductInventory;

import java.util.Optional;

public interface ProductInventoryRepository extends CrudRepository<ProductInventory,Long> {
    public Optional<ProductInventory> findByProductId(long productId);

    @Transactional
    public void deleteByProductId(long productId);
}
