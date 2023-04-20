package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.UserAddress;
import com.zhakav.ecommerce.service.UserAddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/userAddress")
@PreAuthorize("hasAnyRole ('SUPER-ADMIN' , 'STORE-ADMIN' , 'SHIPPING-ADMIN' , 'PAYMENT-GATEWAY-ADMIN') ")
public class UserAddressController {

    UserAddressService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAddress>> getByUser(@PathVariable long userId){

        return new ResponseEntity<>(service.getByUser(userId), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserAddress>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('SUPER-ADMIN' , 'STORE-ADMIN' , 'CONTENT-ADMIN')")
    @PostMapping("user/{userId}")
    public ResponseEntity<UserAddress> save(@Valid @RequestBody UserAddress userAddress, @PathVariable long userId){

        return new ResponseEntity<>(service.save(userAddress,userId), HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyRole('SUPER-ADMIN' , 'STORE-ADMIN' , 'CONTENT-ADMIN')")
    @PutMapping("user/{userId}")
    public ResponseEntity<UserAddress> update(@Valid @RequestBody UserAddress userAddress, @PathVariable long userId){

        return new ResponseEntity<>(service.update(userAddress,userId), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('SUPER-ADMIN' , 'STORE-ADMIN' , 'CONTENT-ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PreAuthorize("hasAnyRole('SUPER-ADMIN' , 'STORE-ADMIN' , 'CONTENT-ADMIN')")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> deleteByType(@PathVariable long userId){

        service.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN' , 'STORE_ADMIN' , 'CONTENT_ADMIN')")
    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAll(){
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
