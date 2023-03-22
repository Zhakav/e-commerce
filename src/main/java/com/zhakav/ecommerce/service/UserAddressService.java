package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.UserAddress;

public interface UserAddressService {
    
    public UserAddress save(UserAddress userAddress, long userId);
    public UserAddress update(UserAddress userAddress, long userId);
    public UserAddress get(long id);
    public List<UserAddress> getByUser(long userId);
    public List<UserAddress> getAll();
    public void delete(long id);
    public void deleteByUserId(long userId);
    public void deleteAll();


}
