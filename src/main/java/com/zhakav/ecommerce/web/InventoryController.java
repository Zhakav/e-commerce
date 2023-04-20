package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.ProductCategory;
import com.zhakav.ecommerce.entity.ProductInventory;
import com.zhakav.ecommerce.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
@PreAuthorize("hasAnyRole ('SUPER_ADMIN' , 'STORE_ADMIN' , 'SHIPPING_ADMIN')")
public class InventoryController {

    ProductInventoryService service;
    @PutMapping("/product/{productId}/quantity/{quantity}")
    public ResponseEntity<ProductInventory> update(@PathVariable long productId, @PathVariable int quantity){

        return new ResponseEntity<>(service.update(quantity,productId), HttpStatus.OK);

    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductInventory> getByProduct(@PathVariable long productId){
        return new ResponseEntity<>(service.getByProduct(productId),HttpStatus.OK);
    }

}
