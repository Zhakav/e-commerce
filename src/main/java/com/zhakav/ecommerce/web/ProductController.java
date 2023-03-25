package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.Product;
import com.zhakav.ecommerce.entity.UserAddress;
import com.zhakav.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/inventory/{inventoryId}")
    public ResponseEntity<Product> getByInventory(@PathVariable long inventoryId){

        return new ResponseEntity<>(service.getByInventory(inventoryId), HttpStatus.OK);

    }

    @GetMapping("/discount/{discountId}")
    public ResponseEntity<List<Product>> getByDiscount(@PathVariable long discountId){

        return new ResponseEntity<>(service.getByDiscount(discountId), HttpStatus.OK);

    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable long categoryId){

        return new ResponseEntity<>(service.getByCategory(categoryId), HttpStatus.OK);

    }

    @GetMapping("/supplier/{userId}")
    public ResponseEntity<List<Product>> getBySupplier(@PathVariable long supplierId){

        return new ResponseEntity<>(service.getBySupplier(supplierId), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping("supplier/{supplierId}/quantity/{quantity}/category/{categoryId}")
    public ResponseEntity<Product> save(@RequestBody Product product, @PathVariable long supplierId,
                                        @PathVariable int quantity, @PathVariable long categoryId ){

        return new ResponseEntity<>(service.save(product,categoryId,supplierId,quantity), HttpStatus.CREATED);

    }

    @PutMapping("supplier/{supplierId}/quantity/{quantity}/category/{categoryId}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable long supplierId,
                                              @PathVariable int quantity, @PathVariable long categoryId ){

        return new ResponseEntity<>(service.update(product,categoryId,supplierId,quantity), HttpStatus.OK);

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
