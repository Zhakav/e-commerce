package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zhakav.ecommerce.entity.*;
import org.springframework.stereotype.Service;

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
        
        for (CartItem item : cartItems) {

            item.setSession(session);
            
        }

        cartItemRepository.saveAll(cartItems);

    }

    private long calculateTotal(Set<CartItem> cartItems){

        long total=0;
        Product product;
        Discount discount;

        for (CartItem cartItem : cartItems) {
         
            product=cartItem.getProduct();
            discount=product.getDiscount();

            if(discount==null)
                total+=(cartItem.getQuantity()*product.getPrice());
            else
                total+=(cartItem.getQuantity()*
                        (product.getPrice()*(1-discount.getDiscountPercent())));
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
