package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.entity.UserAddress;
import com.zhakav.ecommerce.repository.UserAddressRepository;
import com.zhakav.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAddressServiceImp implements UserAddressService {

    UserAddressRepository repository;
    UserRepository userRepository;

    @Override
    public UserAddress save(UserAddress userAddress, long userId) {

        User user=UserServiceImp.unwrap(userRepository.findById(userId), userId);
        userAddress.setUser(user);

        return repository.save(userAddress);
    }

    @Override
    public UserAddress update(UserAddress userAddress, long userId) {
        return save(userAddress, userId);
    }

    @Override
    public UserAddress get(long id) {
        return unwrap(repository.findById(id),id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByUserId(long userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<UserAddress> getByUser(long userId) {
        return (List<UserAddress>)repository.findByUserId(userId);
    }

    @Override
    public List<UserAddress> getAll() {
        return (List<UserAddress>) repository.findAll();
    }

    public static UserAddress unwrap(Optional<UserAddress> userAddress , long id){
    
        if(userAddress.isPresent())
            return userAddress.get();
        else
            throw new EntityNotFoundException(id, "User Address","ID");

    }
    
}
