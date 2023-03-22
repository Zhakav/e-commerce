package com.zhakav.ecommerce.web;

import com.zhakav.ecommerce.entity.AdminType;
import com.zhakav.ecommerce.entity.AdminUser;
import com.zhakav.ecommerce.service.AdminUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    AdminUserService service;

    @GetMapping("/{id}")
    public ResponseEntity<AdminUser> get(@PathVariable long id){

        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @GetMapping("/adminType/{typeId}")
    public ResponseEntity<List<AdminUser>> getByType(@PathVariable long typeId){

        return new ResponseEntity<>(service.getByType(typeId), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminUser>> getAll(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping("adminType/{typeId}")
    public ResponseEntity<AdminUser> save(@RequestBody AdminUser adminUser, @PathVariable long typeId){

        return new ResponseEntity<>(service.save(adminUser,typeId), HttpStatus.CREATED);

    }

    @PutMapping("adminType/{typeId}")
    public ResponseEntity<AdminUser> update(@RequestBody AdminUser adminUser, @PathVariable long typeId){

        return new ResponseEntity<>(service.update(adminUser,typeId ), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/adminType/{typeId}")
    public ResponseEntity<HttpStatus> deleteByType(@PathVariable long typeId){

        service.deleteByType(typeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/all")
    public ResponseEntity<HttpStatus> deleteAll(){
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
