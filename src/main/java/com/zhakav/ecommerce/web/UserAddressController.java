package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.UserAddress;
import com.zhakav.ecommerce.service.UserAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/userAddress")
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

    @PostMapping("user/{userId}")
    public ResponseEntity<UserAddress> save(@RequestBody UserAddress userAddress, @PathVariable long userId){

        return new ResponseEntity<>(service.save(userAddress,userId), HttpStatus.CREATED);

    }

    @PutMapping("user/{userId}")
    public ResponseEntity<UserAddress> update(@RequestBody UserAddress userAddress, @PathVariable long userId){

        return new ResponseEntity<>(service.update(userAddress,userId), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> deleteByType(@PathVariable long userId){

        service.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAll(){
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
