package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.ProductCategory;

public interface ProductCategoryService {
    
    public ProductCategory save(ProductCategory category);
    public ProductCategory update(ProductCategory category);
    public ProductCategory get(long id);
    public void delete(long id);
    public List<ProductCategory> getAll();

}
