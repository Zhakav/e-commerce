package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.ShoppingSession;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.repository.CartItemRepository;
import com.zhakav.ecommerce.repository.ShoppingSessionRepository;
import com.zhakav.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderingServiceImp implements OrderingService {

    CartItemRepository cartItemRepository;
    ShoppingSessionRepository sessionRepository;
    UserRepository userRepository;

    @Override
    public ShoppingSession order(List<CartItem> cartItems, long userId) {

        ShoppingSession session=new ShoppingSession();
        User user=UserServiceImp.unwrap(userRepository.findById(userId), userId);

        session.setUser(user);
        saveCartItemsToSession(cartItems, session);

        sessionRepository.save(session);

        return session;
    }

    @Override
    public ShoppingSession getByUser(long userId) {

        return unwrapSession(sessionRepository.findByUserId(userId),userId);

    }

    @Override
    public void deleteByUser(long userId) {

        cartItemRepository.deleteAllBySessionId(getByUser(userId).getId());
        sessionRepository.deleteByUserId(userId);
        
    }

    @Override
    public void saveCartItemsToSession(List<CartItem> cartItems, ShoppingSession session) {

        session.setCartItems(cartItems);

        cartItemRepository.saveAll(cartItems);
        
        for (CartItem item : cartItems) {

            item.setSession(session);
            
        }
    }

    public static ShoppingSession unwrapSession(Optional<ShoppingSession> session , long id){
    
        if(session.isPresent())
            return session.get();
        else
            throw new RuntimeException("Cannot find shopping session with id : " + id);

    }

    public static CartItem unwrapCartItem(Optional<CartItem> cartItem , long id){
    
        if(cartItem.isPresent())
            return cartItem.get();
        else
            throw new RuntimeException("Cannot find shopping session with user id : " + id);

    }
    
}
