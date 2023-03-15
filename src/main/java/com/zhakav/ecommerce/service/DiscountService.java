package com.zhakav.ecommerce.service;

import java.util.List;

import com.zhakav.ecommerce.entity.Discount;

public interface DiscountService {
    
    public Discount save(Discount discount);
    public Discount update(Discount discount);
    public Discount get(long id);
    public void delete(long id);
    public List<Discount> getAll();

}
