package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.ShoppingSession;

public interface OrderingService {
    
    public ShoppingSession order(List<CartItem> cartItems, long userId);
    public ShoppingSession getByUser(long userId);
    public void deleteByUser(long userId);
    public void saveCartItemsToSession(List<CartItem> cartItems, ShoppingSession session);

}
