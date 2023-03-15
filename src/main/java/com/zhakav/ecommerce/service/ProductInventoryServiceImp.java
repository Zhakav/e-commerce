package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.ProductInventory;
import com.zhakav.ecommerce.repository.ProductInventoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductInventoryServiceImp implements ProductInventoryService {

    ProductInventoryRepository repository;

    @Override
    public ProductInventory save(ProductInventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public ProductInventory update(ProductInventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public ProductInventory get(long id) {
        return unwrap(repository.findById(null), id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductInventory> getAll() {
        return (List<ProductInventory>) repository.findAll();
    }

    public static ProductInventory unwrap(Optional<ProductInventory> inventory , long id){
    
        if(inventory.isPresent())
            return inventory.get();
        else
            throw new RuntimeException("Cannot find product inventory with id : " + id);

    }
    
}
