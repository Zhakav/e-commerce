package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.UserPayment;

public interface UserPaymentService {
    
    public UserPayment save(UserPayment userPayment, long userId);
    public UserPayment update(UserPayment userPayment, long userId);
    public UserPayment get(long id);
    public void delete(long id);
    public List<UserPayment> getByUser(long userId);
    public List<UserPayment> getAll();

}
