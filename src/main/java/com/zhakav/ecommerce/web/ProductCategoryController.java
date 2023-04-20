package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.ProductCategory;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
@PreAuthorize("hasAnyRole('SUPER_ADMIN' , 'STORE_ADMIN' , 'CONTENT_ADMIN')")
public class ProductCategoryController {

    ProductCategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategory>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ProductCategory> save(@Valid @RequestBody ProductCategory category){

        return new ResponseEntity<>(service.save(category), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<ProductCategory> update(@Valid @RequestBody ProductCategory category){

        return new ResponseEntity<>(service.update(category), HttpStatus.OK);

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
