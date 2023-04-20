package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.OrderDetail;
import com.zhakav.ecommerce.entity.UserAddress;
import com.zhakav.ecommerce.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orderDetail")
@PreAuthorize("hasAnyRole ('SUPER_ADMIN' , 'STORE_ADMIN' , 'SHIPPING_ADMIN' , 'PAYMENT_GATEWAY_ADMIN') ")
public class OrderDetailController {

    OrderDetailService service;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDetail>> getByUser(@PathVariable long userId){

        return new ResponseEntity<>(service.getByUser(userId), HttpStatus.OK);

    }

}
