package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.Product;

public interface ProductService {
    
    public Product save(Product product, long categoryId, long supplierId, int quantity);
    public Product update(Product product, long categoryId, long supplierId, int quantity);
    public Product setProductDiscount(long productId, long discountId);
    public Product get(long id);
    public void delete(long id);
    public void deleteAll();
    public void setDiscount(long productId, long discountId);
    public Product getByInventory(long inventoryId);
    public List<Product> getByCategory(long categoryId);
    public List<Product> getByDiscount(long discountId);
    public List<Product> getBySupplier(long supplierId);
    public List<Product> getAll();


}
