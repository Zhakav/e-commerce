package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.User;

public interface UserService {
    
    public User save(User user);
    public User update(User user);
    public User get(long id);
    public List<User> getAll();
    public void delete(long id);
    public void deleteAll();


}
