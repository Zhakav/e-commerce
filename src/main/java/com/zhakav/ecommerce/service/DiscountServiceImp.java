package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.Discount;
import com.zhakav.ecommerce.repository.DiscountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiscountServiceImp implements DiscountService {

    DiscountRepository repository;

    @Override
    public Discount save(Discount discount) {
        return repository.save(discount);
    }

    @Override
    public Discount update(Discount discount) {
        return repository.save(discount);
    }

    @Override
    public Discount get(long id) {
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
    public List<Discount> getAll() {
        return (List<Discount>) repository.findAll();
    }

    public static Discount unwrap(Optional<Discount> discount , long id){
    
        if(discount.isPresent())
            return discount.get();
        else
            throw new EntityNotFoundException(id,"Discount","ID");

    }
    
}
