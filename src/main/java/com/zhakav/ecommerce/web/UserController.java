package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.AdminType;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        service.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody User user){
        service.update(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAll(){
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
