package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.AdminUser;

public interface AdminUserService {
    
    public AdminUser save(AdminUser adminUser, long adminTypeId);
    public AdminUser update(AdminUser adminUser, long adminTypeId);
    public AdminUser get(long id);
    public AdminUser getByUsername(String username);
    public void delete(long id);
    public void deleteByType(long typeId);
    public void deleteAll();
    public List<AdminUser> getByType(long adminTypeId);
    public List<AdminUser> getAll();

}
