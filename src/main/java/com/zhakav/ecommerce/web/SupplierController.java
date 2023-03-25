package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.Supplier;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {

    SupplierService service;

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Supplier>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Supplier> save(@RequestBody Supplier supplier){

        return new ResponseEntity<>(service.save(supplier), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Supplier> update(@RequestBody Supplier supplier){

        return new ResponseEntity<>(service.update(supplier), HttpStatus.OK);

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
