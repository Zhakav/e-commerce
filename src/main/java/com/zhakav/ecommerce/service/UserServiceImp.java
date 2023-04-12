package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository repository;
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImp(UserRepository repository,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository=repository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public User save(@Valid User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User update(@Valid User user) {
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        return save(user);
    }

    @Override
    public User get(long id) {
        return unwrap(repository.findById(id), id);
    }

    @Override
    public User getByUsername(String username) {
        return unwrap(repository.findByUsername(username),username);
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
    public List<User> getAll() {
        return (List<User>)repository.findAll();
    }
    
    public static User unwrap(Optional<User> user , Object id){
    
        if(user.isPresent())
            return user.get();
        else
            throw new EntityNotFoundException(id,"User","ID");

    }
}
