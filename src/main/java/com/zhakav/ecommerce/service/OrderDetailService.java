package com.zhakav.ecommerce.service;

import com.zhakav.ecommerce.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    public List<OrderDetail> getByUser(long userId);

}
