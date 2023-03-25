package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.CartItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zhakav.ecommerce.entity.ShoppingSession;
import com.zhakav.ecommerce.service.OrderingService;

import lombok.AllArgsConstructor;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    OrderingService service;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<ShoppingSession> getSession(@PathVariable Long userId){

        return new ResponseEntity<>(service.getByUser(userId), HttpStatus.OK);

    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ShoppingSession> submitOrder(@RequestBody Set<CartItem> cartItems, @PathVariable long userId){

        return new ResponseEntity<>(service.order(cartItems,userId), HttpStatus.OK);

    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable long userId){
        service.deleteByUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    
}
