package com.zhakav.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zhakav.ecommerce.entity.ShoppingSession;

import jakarta.transaction.Transactional;

public interface ShoppingSessionRepository extends CrudRepository<ShoppingSession,Long> {
    
    @Transactional
    public void deleteByUserId(long userId);

    public Optional<ShoppingSession> findByUserId(long userId);

    @Query(value = "SELECT DISTINCT s FROM shopping_sessions s JOIN FETCH s.cartItems c", nativeQuery = true)
    public Optional<ShoppingSession> findByUserIdWithCartItems(@Param("user_id") long userId);

}
