package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.zhakav.ecommerce.entity.ProductCategory;
import com.zhakav.ecommerce.repository.ProductCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImp implements ProductCategoryService {

    ProductCategoryRepository repository;

    @Override
    public ProductCategory save(ProductCategory category) {
        return repository.save(category);
    }

    @Override
    public ProductCategory update(ProductCategory category) {
        return repository.save(category);
    }

    @Override
    public ProductCategory get(long id) {
        return unwrap(repository.findById(id), id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<ProductCategory> getAll() {
        return (List<ProductCategory>) repository.findAll();
    }

    public static ProductCategory unwrap(Optional<ProductCategory> category , long id){
    
        if(category.isPresent())
            return category.get();
        else
            throw new EntityNotFoundException(id,"Category","ID");

    }
    
}
