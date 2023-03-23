package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.ProductInventory;

public interface ProductInventoryService {

    public ProductInventory update(int quantity, long productId);
    public ProductInventory get(long id);
    public void delete(long id);
    public void deleteByProduct(long productId);
    public ProductInventory getByProduct(long productId);
    public List<ProductInventory> getAll();
    
}
