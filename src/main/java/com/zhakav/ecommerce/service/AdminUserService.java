package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.AdminUser;

public interface AdminUserService {
    
    public AdminUser save(AdminUser adminUser, long adminTypeId);
    public AdminUser update(AdminUser adminUser, long adminType);
    public AdminUser get(long id);
    public void delete(long id);
    public List<AdminUser> getByType(long adminTypeId);
    public List<AdminUser> getAll();

}
