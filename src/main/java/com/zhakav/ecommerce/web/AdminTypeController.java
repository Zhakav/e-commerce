package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.AdminType;
import com.zhakav.ecommerce.service.AdminTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/adminType")
public class AdminTypeController {
    AdminTypeService service;
    @GetMapping("/{id}")
    public ResponseEntity<AdminType> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminType>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<AdminType> save(@RequestBody AdminType adminType){

        return new ResponseEntity<>(service.save(adminType), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<AdminType> update(@RequestBody AdminType adminType){

        return new ResponseEntity<>(service.update(adminType), HttpStatus.OK);

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
