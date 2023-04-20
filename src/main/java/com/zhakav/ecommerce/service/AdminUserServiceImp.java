package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.AdminType;
import com.zhakav.ecommerce.entity.AdminUser;
import com.zhakav.ecommerce.repository.AdminTypeRepository;
import com.zhakav.ecommerce.repository.AdminUserRepository;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminUserServiceImp implements AdminUserService{

    AdminUserRepository repository;
    AdminTypeRepository adminTypeRepository;

    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AdminUser save(AdminUser adminUser, long adminTypeId) {

        AdminType adminType=AdminTypeServiceImp.unwrap(adminTypeRepository.findById(adminTypeId), adminTypeId);
        adminUser.setAdminType(adminType);

        adminUser.setPassword(bCryptPasswordEncoder.encode(adminUser.getPassword()));

        return repository.save(adminUser);
    }

    @Override
    public AdminUser update(AdminUser adminUser, long adminType) {

        adminUser.setPassword(bCryptPasswordEncoder.encode(adminUser.getPassword()));

        return save(adminUser,adminType);

    }

    @Override
    public AdminUser get(long id) {
        return unwrap(repository.findById(id),id);
    }

    @Override
    public AdminUser getByUsername(String username) {
        return unwrap(repository.findByUsername(username),404);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByType(long typeId) {
        repository.deleteByAdminTypeId(typeId);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<AdminUser> getByType(long adminTypeId) {
        return repository.findByAdminTypeId(adminTypeId);
    }

    @Override
    public List<AdminUser> getAll() {
        return (List<AdminUser> )repository.findAll();
    }

    public static AdminUser unwrap(Optional<AdminUser> adminUser , long id){
    
        if(adminUser.isPresent())
            return adminUser.get();
        else
            throw new EntityNotFoundException(id,"Admin","ID");

    }
    
}
