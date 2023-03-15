package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.entity.UserPayment;
import com.zhakav.ecommerce.repository.UserPaymentRepository;
import com.zhakav.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserPaymentServiceImp implements UserPaymentService {

    UserPaymentRepository repository;
    UserRepository userRepository;

    @Override
    public UserPayment save(UserPayment userPayment, long userId) {

        User user=UserServiceImp.unwrap(userRepository.findById(userId), userId);
        userPayment.setUser(user);

        return repository.save(userPayment);
    }

    @Override
    public UserPayment update(UserPayment userPayment, long userId) {

        return save(userPayment, userId);

    }

    @Override
    public UserPayment get(long id) {

        return unwrap(repository.findById(id), id);

    }

    @Override
    public void delete(long id) {
        
        repository.deleteById(id);

    }

    @Override
    public List<UserPayment> getByUser(long userId) {

        return (List<UserPayment>) repository.findByUserId(userId);

    }

    @Override
    public List<UserPayment> getAll() {
        return (List<UserPayment>) repository.findAll();
    }

    public static UserPayment unwrap(Optional<UserPayment> userPayment , long id){
    
        if(userPayment.isPresent())
            return userPayment.get();
        else
            throw new RuntimeException("Cannot find user payment with id : " + id);

    }
    
}
