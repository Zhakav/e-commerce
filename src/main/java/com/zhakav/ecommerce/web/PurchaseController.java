package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.service.PurchasingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/purchase")
@PreAuthorize("hasAnyRole ('SUPER_ADMIN' , 'STORE_ADMIN' , 'SHIPPING_ADMIN' , 'PAYMENT_GATEWAY_ADMIN') ")
public class PurchaseController {

    PurchasingService service;

    @PostMapping("/user/{userId}/start")
    public ResponseEntity<HttpStatus> startPurchase(@PathVariable long userId){

        service.startPurchasing(userId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/order/{orderId}/{status}/end")
    public ResponseEntity<HttpStatus> startPurchase(@PathVariable long orderId, @PathVariable String status){

        service.endPurchasing(status, orderId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
