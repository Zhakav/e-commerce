package com.zhakav.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.CartItem;

import jakarta.transaction.Transactional;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {
    
    public List<CartItem> getBySessionId(long sessionId);

    @Transactional
    public void deleteAllBySessionId(long sessionId);

}
