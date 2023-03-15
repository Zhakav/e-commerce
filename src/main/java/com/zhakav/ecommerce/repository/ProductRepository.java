package com.zhakav.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {
    
    public List<Product> findBySupplierId(long supplierId);
    public List<Product> findByCategoryId(long productCategoryId);
    public List<Product> findByDiscountId(long discountId);
    public Optional<Product> findByInventoryId(long productInventoryId);



}
