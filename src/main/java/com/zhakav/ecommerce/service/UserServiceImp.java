package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return save(user);
    }

    @Override
    public User get(long id) {
        return unwrap(repository.findById(id), id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>)repository.findAll();
    }
    
    public static User unwrap(Optional<User> user , long id){
    
        if(user.isPresent())
            return user.get();
        else
            throw new RuntimeException("Cannot find user with id : " + id);

    }
}
