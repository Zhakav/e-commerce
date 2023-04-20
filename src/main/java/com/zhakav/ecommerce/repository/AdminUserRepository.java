package com.zhakav.ecommerce.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser,Long> {

    public Optional<AdminUser> findByUsername(String username);
    public List<AdminUser> findByAdminTypeId(long adminTypeId);

    @Transactional
    public void deleteByAdminTypeId(long adminTypeId);
    
}
