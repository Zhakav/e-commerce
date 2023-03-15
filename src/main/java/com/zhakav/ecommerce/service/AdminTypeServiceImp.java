package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.AdminType;
import com.zhakav.ecommerce.repository.AdminTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminTypeServiceImp implements AdminTypeService {

    AdminTypeRepository repository;

    @Override
    public AdminType save(AdminType adminType) {
        return repository.save(adminType);
    }

    @Override
    public AdminType update(AdminType adminType) {
        return repository.save(adminType);
    }

    @Override
    public AdminType get(long id) {
        return unwrap(repository.findById(id), id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AdminType> getAll() {
        return (List<AdminType>)repository.findAll();
    }

    public static AdminType unwrap(Optional<AdminType> adminType , long id){
    
        if(adminType.isPresent())
            return adminType.get();
        else
            throw new RuntimeException("Cannot find admin type with id : " + id);

    }
    
}
