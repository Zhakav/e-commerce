package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.CartItem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.zhakav.ecommerce.entity.ShoppingSession;
import com.zhakav.ecommerce.service.OrderingService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@PreAuthorize("hasAnyRole('SUPER_ADMIN' , 'STORE_ADMIN' , 'SHIPPING_ADMIN' , 'PAYMENT_GATEWAY_ADMIN') ")
public class OrderController {

    OrderingService service;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<ShoppingSession> getSession(@PathVariable Long userId){

        return new ResponseEntity<>(service.getByUser(userId), HttpStatus.OK);

    }

    @PostMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN' , 'STORE_ADMIN')")
    public ResponseEntity<ShoppingSession> submitOrder(@Valid @RequestBody List<CartItem> cartItems, @PathVariable long userId){

        return new ResponseEntity<>(service.order(cartItems,userId), HttpStatus.OK);

    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN' , 'STORE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable long userId){
        service.deleteByUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    
}
