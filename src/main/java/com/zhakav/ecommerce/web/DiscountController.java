package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.Discount;
import com.zhakav.ecommerce.entity.ProductCategory;
import com.zhakav.ecommerce.service.DiscountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/discount")
@PreAuthorize("hasAnyRole ('SUPER_ADMIN' , 'STORE_ADMIN' , 'CONTENT_ADMIN')")
public class DiscountController {

    DiscountService service;

    @GetMapping("/{id}")
    public ResponseEntity<Discount> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Discount>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Discount> save(@Valid @RequestBody Discount discount){

        return new ResponseEntity<>(service.save(discount), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Discount> update(@Valid @RequestBody Discount discount){
        return new ResponseEntity<>(service.update(discount), HttpStatus.OK);

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
