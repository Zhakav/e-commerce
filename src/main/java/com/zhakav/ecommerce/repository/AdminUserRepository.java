package com.zhakav.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser,Long> {

    public List<AdminUser> findByAdminTypeId(long adminTypeId);
    
}
