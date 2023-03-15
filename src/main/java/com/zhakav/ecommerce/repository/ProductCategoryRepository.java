package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory,Long> {
    
}
