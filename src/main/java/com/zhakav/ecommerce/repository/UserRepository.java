package com.zhakav.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.User;

public interface UserRepository extends CrudRepository<User,Long> {
    
}
