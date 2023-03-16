package com.zhakav.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.ShoppingSession;

import jakarta.transaction.Transactional;

public interface ShoppingSessionRepository extends CrudRepository<ShoppingSession,Long> {
    
    @Transactional
    public void deleteByUserId(long userId);

    public Optional<ShoppingSession> findByUserId(long userId);

}
