package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.Supplier;

public interface SupplierService {
    
    public Supplier save(Supplier supplier);
    public Supplier update(Supplier supplier);
    public Supplier get(long id);
    public void delete(long id);
    public List<Supplier> getAll();

}
