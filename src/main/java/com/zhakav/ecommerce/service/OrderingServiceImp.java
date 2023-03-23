package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.Product;
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
    public ShoppingSession order(Set<CartItem> cartItems, long userId) {

        ShoppingSession session=new ShoppingSession();
        User user=UserServiceImp.unwrap(userRepository.findById(userId), userId);

        session.setUser(user);
        saveCartItemsToSession(cartItems, session);
        session.setTotal(calculateTotal(cartItems));

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

    private void saveCartItemsToSession(Set<CartItem> cartItems, ShoppingSession session) {

        session.setCartItems(cartItems);

        cartItemRepository.saveAll(cartItems);
        
        for (CartItem item : cartItems) {

            item.setSession(session);
            
        }
    }

    private long calculateTotal(Set<CartItem> cartItems){

        long total=0;
        Product product;

        for (CartItem cartItem : cartItems) {
         
            product=cartItem.getProduct();

            total+=(cartItem.getQuantity()*product.getPrice());

        }

        return total;

    }

    public static ShoppingSession unwrapSession(Optional<ShoppingSession> session , long id){
    
        if(session.isPresent())
            return session.get();
        else
            throw new RuntimeException("Cannot find shopping session with id : " + id);

    }

    public static CartItem unwrapCartItem(Optional<CartItem> cartItem, long id){

        if(cartItem.isPresent())
            return cartItem.get();
        else
            throw new RuntimeException("Cannot find cart item with id : " + id);

    }
    
}
