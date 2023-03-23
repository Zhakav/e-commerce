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
    public ProductInventory update(int quantity, long productId) {

        ProductInventory inventory=unwrap(repository.findByProductId(productId),productId);
        inventory.setQuantity(quantity);
        return repository.save(inventory);

    }

    @Override
    public ProductInventory get(long id) {
        return unwrap(repository.findById(id), id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByProduct(long productId) {
        repository.deleteByProductId(productId);
    }

    @Override
    public ProductInventory getByProduct(long productId) {
        return unwrap(repository.findByProductId(productId),productId);
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
