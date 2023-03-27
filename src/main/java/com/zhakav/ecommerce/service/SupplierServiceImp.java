package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.Supplier;
import com.zhakav.ecommerce.repository.SupplierRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SupplierServiceImp implements SupplierService {

    SupplierRepository repository;

    @Override
    public Supplier save(Supplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public Supplier get(long id) {
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
    public List<Supplier> getAll() {
        return (List<Supplier>) repository.findAll();
    }

    public static Supplier unwrap(Optional<Supplier> supplier , long id){
    
        if(supplier.isPresent())
            return supplier.get();
        else
            throw new EntityNotFoundException(id,"Supplier","ID");

    }
    
}
