package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Set;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.ShoppingSession;

public interface OrderingService {
    
    public ShoppingSession order(Set<CartItem> cartItems, long userId);
    public ShoppingSession getByUser(long userId);
    public void deleteByUser(long userId);

}
