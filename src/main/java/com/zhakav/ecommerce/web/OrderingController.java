package com.zhakav.ecommerce.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhakav.ecommerce.entity.ShoppingSession;
import com.zhakav.ecommerce.service.OrderingService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderingController {

    OrderingService service;
    
    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingSession> getSession(@PathVariable Long userId){

        return new ResponseEntity<>(service.getByUser(userId), HttpStatus.OK);

    } 
    
}
