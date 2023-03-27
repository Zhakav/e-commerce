package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Set;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.ShoppingSession;
import jakarta.websocket.Session;

public interface OrderingService {
    
    public ShoppingSession order(List<CartItem> cartItems, long userId);
    public ShoppingSession getByUser(long userId);
    public void deleteByUser(long userId);
    public long calculateTotal(List<CartItem> cartItems);
    public ShoppingSession saveCartItemsToSession(List<CartItem> cartItems, ShoppingSession session);



}
