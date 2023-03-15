package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.ProductInventory;

public interface ProductInventoryService {

    public ProductInventory save(ProductInventory inventory);
    public ProductInventory update(ProductInventory inventory);
    public ProductInventory get(long id);
    public void delete(long id);
    public List<ProductInventory> getAll();
    
}
